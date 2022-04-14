package com.example.desafio_android_accenture.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafio_android_accenture.data.imageloader.ImageLoader
import com.example.desafio_android_accenture.data.imageloader.ImageLoaderService
import com.example.desafio_android_accenture.data.model.RepositoryModel
import com.example.desafio_android_accenture.ui.adapters.PullRequestAdapter
import com.example.desafio_android_accenture.databinding.FragmentPullRequestBinding
import com.example.desafio_android_accenture.ui.adapters.RepositoryAdapter
import com.example.desafio_android_accenture.ui.view.MainActivity
import com.example.desafio_android_accenture.ui.viewmodel.MainViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [PullRequestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PullRequestFragment : Fragment() {

    private lateinit var binding: FragmentPullRequestBinding
    private lateinit var viewModel: MainViewModel
    private val imageLoader: ImageLoader = ImageLoaderService()
    private val pullRequestAdapter = PullRequestAdapter(manager = PullRequestManager())
    private val args: PullRequestFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.let { ViewModelProvider(it)[MainViewModel::class.java] }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPullRequestBinding.inflate(inflater, container, false)

        val title = args.repoTitle
        val user = args.repoUser
        val issues = args.issuesOpened

        initRecyclerHeader(issues)
        initRecyclerView(title, user)
        (activity as MainActivity).supportActionBar?.title = title
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun initRecyclerHeader(issues: String) {
        binding.idOpenIssues.text = "$issues opened"
    }

    private fun initRecyclerView(title: String, user: String) {
        with(binding.idPullRequestRecyclerView) {
            adapter = pullRequestAdapter
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }

        with(viewModel) {
            getPullRequests(user, title).observe(viewLifecycleOwner) {
                pullRequestAdapter.addPullRequests(it)
            }
            isLoading.observe(viewLifecycleOwner) { binding.idEmptyListText.isVisible = it }
        }

    }

    inner class PullRequestManager : PullRequestAdapter.AdapterManager {
        override fun provideImageLoader(): ImageLoader = imageLoader
    }
}
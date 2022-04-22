package com.example.desafio_android_accenture.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafio_android_accenture.data.imageloader.ImageLoader
import com.example.desafio_android_accenture.data.imageloader.ImageLoaderService
import com.example.desafio_android_accenture.data.model.PullRequestModel
import com.example.desafio_android_accenture.ui.adapters.PullRequestAdapter
import com.example.desafio_android_accenture.databinding.FragmentPullRequestBinding
import com.example.desafio_android_accenture.ui.view.MainActivity
import com.example.desafio_android_accenture.presentation.viewmodel.ListState
import com.example.desafio_android_accenture.presentation.viewmodel.PullRequestViewModel
import com.example.desafio_android_accenture.utils.mappers.toPullRequestItem

/**
 * A simple [Fragment] subclass.
 * Use the [PullRequestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PullRequestFragment : Fragment() {

    private lateinit var binding: FragmentPullRequestBinding
    private lateinit var viewModel: PullRequestViewModel
    private val imageLoader: ImageLoader = ImageLoaderService()
    private val pullRequestAdapter = PullRequestAdapter(manager = PullRequestManager())
    private val args: PullRequestFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = activity?.let { ViewModelProvider(it)[PullRequestViewModel::class.java] }!!
        binding = FragmentPullRequestBinding.inflate(inflater, container, false)
        (activity as MainActivity).supportActionBar?.title = args.repoTitle
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.idOpenIssues.text = "${args.issuesOpened} open"

        with(binding.rvPullRequest) {
            adapter = pullRequestAdapter
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }

        with(viewModel) {
            pullRequestListState.observe(viewLifecycleOwner) {
                renderViewState(it)
            }
            getPullRequests(args.repoUser, args.repoTitle)
        }
    }

    private fun renderViewState(state: ListState) {
        when (state) {
            is ListState.Loading -> binding.pBarPullRequest.visibility = View.VISIBLE
            is ListState.Success -> {
                val list = state.list as List<PullRequestModel>
                binding.pBarPullRequest.visibility = View.GONE
                val items = list.map { it.toPullRequestItem() }
                pullRequestAdapter.addPullRequests(items)
            }
            is ListState.NoData -> {
                binding.pBarPullRequest.visibility = View.GONE
                binding.tvEmptyPullRequest.visibility = View.VISIBLE
            }
            is ListState.Error -> {
                binding.pBarPullRequest.visibility = View.GONE
            }
        }
    }

    inner class PullRequestManager : PullRequestAdapter.AdapterManager {
        override fun provideImageLoader(): ImageLoader = imageLoader
    }
}
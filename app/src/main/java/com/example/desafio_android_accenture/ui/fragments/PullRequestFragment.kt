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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafio_android_accenture.ui.adapters.PullRequestAdapter
import com.example.desafio_android_accenture.databinding.FragmentPullRequestBinding
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
    private val args: PullRequestFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.let {
            ViewModelProvider(it)[MainViewModel::class.java]
        }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPullRequestBinding.inflate(inflater, container, false)
        initListHeader()
        initRecyclerView()
        (activity as MainActivity).supportActionBar?.title = args.repoTitle
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun initListHeader() {
        val issues = args.issuesOpened
        binding.idOpenIssues.text = "$issues opened"
    }

    private fun initRecyclerView() {
        val recyclerView = binding.idPullRequestRecyclerView
        val adapter = PullRequestAdapter()
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        )
        val title = args.repoTitle
        val user = args.repoUser
        val pullRequests = viewModel.getPullRequests(user, title)
        pullRequests.observe(viewLifecycleOwner, Observer {
            adapter.addPullRequests(it)
        })

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.idEmptyListText.isVisible = it
        }
    }
}
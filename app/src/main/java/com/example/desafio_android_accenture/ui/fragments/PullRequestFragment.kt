package com.example.desafio_android_accenture.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
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
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [PullRequestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class PullRequestFragment : Fragment() {

    private val viewModel: PullRequestViewModel by viewModels()
    private val args: PullRequestFragmentArgs by navArgs()
    private val imageLoader: ImageLoader = ImageLoaderService()
    private val pullRequestAdapter = PullRequestAdapter(manager = PullRequestManager())
    private lateinit var binding: FragmentPullRequestBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPullRequestBinding.inflate(inflater, container, false)
        //(activity as MainActivity).supportActionBar?.title = args.repoTitle
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        idOpenIssues.text = "${args.issuesOpened} open"

        with(rvPullRequest) {
            adapter = pullRequestAdapter
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }

        with(viewModel) {
            pullRequestListState.observe(viewLifecycleOwner) { renderViewState(it) }
            getPullRequests(args.repoUser, args.repoTitle)
        }
    }

    private fun renderViewState(state: ListState<*>) = with(binding) {
        when (state) {
            is ListState.Loading -> pBarPullRequest.visibility = View.VISIBLE
            is ListState.Success -> {
                val list = state.list as List<PullRequestModel>
                pBarPullRequest.visibility = View.GONE
                if (list.isNotEmpty()) {
                    val items = list.map { it.toPullRequestItem() }
                    pullRequestAdapter.addPullRequests(items)
                } else tvEmptyPullRequest.visibility = View.VISIBLE
            }
            is ListState.Error -> pBarPullRequest.visibility = View.GONE
        }
    }

    inner class PullRequestManager : PullRequestAdapter.AdapterManager {
        override fun provideImageLoader(): ImageLoader = imageLoader
    }
}
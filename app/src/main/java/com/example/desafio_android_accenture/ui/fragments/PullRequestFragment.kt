package com.example.desafio_android_accenture.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.desafio_android_accenture.data.imageloader.ImageLoader
import com.example.desafio_android_accenture.data.imageloader.ImageLoaderService
import com.example.desafio_android_accenture.data.model.PullRequestModel
import com.example.desafio_android_accenture.ui.adapters.PullRequestAdapter
import com.example.desafio_android_accenture.databinding.FragmentPullRequestBinding
import com.example.desafio_android_accenture.presentation.viewmodel.ListState
import com.example.desafio_android_accenture.presentation.viewmodel.PullRequestViewModel
import com.example.desafio_android_accenture.utils.extensions.addVerticalDivider
import com.example.desafio_android_accenture.utils.extensions.observe
import com.example.desafio_android_accenture.utils.mappers.toPullRequestItem
import dagger.hilt.android.AndroidEntryPoint

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
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPullRequestBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) = with(binding) {

        super.onViewCreated(view, savedInstanceState)

        idOpenIssues.text = "${args.issuesOpened} open"

        with(rvPullRequest) {
            adapter = pullRequestAdapter
            addVerticalDivider()
        }

        with(viewModel) {
            observe(pullRequestListState, ::renderViewState)
            getPullRequests(args.repoUser, args.repoTitle)
        }
    }

    private fun renderViewState(state: ListState<PullRequestModel>) = with(binding) {
        when (state) {
            is ListState.Loading -> pBarPullRequest.visibility = View.VISIBLE
            is ListState.Error -> pBarPullRequest.visibility = View.GONE
            is ListState.Success -> onSuccess(state.list)
        }
    }

    private fun onSuccess(result: List<PullRequestModel>) = with(binding) {
        pBarPullRequest.visibility = View.GONE
        if (result.isNotEmpty()) {
            val items = result.map { it.toPullRequestItem() }
            pullRequestAdapter.addItems(items)
        } else {
            tvEmptyPullRequest.visibility = View.VISIBLE
        }
    }

    inner class PullRequestManager : PullRequestAdapter.AdapterManager {
        override fun provideImageLoader(): ImageLoader = imageLoader
    }
}
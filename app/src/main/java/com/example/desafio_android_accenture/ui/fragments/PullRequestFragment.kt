package com.example.desafio_android_accenture.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import androidx.navigation.fragment.navArgs
import com.example.desafio_android_accenture.R
import com.example.desafio_android_accenture.data.imageloader.ImageLoader
import com.example.desafio_android_accenture.data.imageloader.ImageLoaderImpl
import com.example.desafio_android_accenture.data.model.PullRequestModel
import com.example.desafio_android_accenture.ui.adapters.PullRequestAdapter
import com.example.desafio_android_accenture.databinding.FragmentPullRequestBinding
import com.example.desafio_android_accenture.presentation.viewmodel.PullRequestViewModel
import com.example.desafio_android_accenture.presentation.viewmodel.UiState
import com.example.desafio_android_accenture.utils.extensions.addVerticalDivider
import com.example.desafio_android_accenture.utils.extensions.observeFlow
import com.example.desafio_android_accenture.utils.extensions.shortToast
import com.example.desafio_android_accenture.utils.mappers.toPullRequestItem
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 * Use the [PullRequestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class PullRequestFragment : Fragment() {

    private val viewModel by viewModel<PullRequestViewModel>() // Lazy load
    private val imageLoader by inject<ImageLoader>()
    private val args: PullRequestFragmentArgs by navArgs()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        with(binding){
            idOpenIssues.text = getString(R.string.open_issues, args.issuesOpened)

            with(rvPullRequest) {
                adapter = pullRequestAdapter
                addVerticalDivider()
            }
        }

        with(viewModel) {
            observeFlow(pullRequestListState, ::renderViewState)
            getPullRequests(args.repoUser, args.repoTitle)
        }
    }

    private fun renderViewState(state: UiState) = with(binding) {
        when (state) {
            is UiState.Loading -> {
                pBarPullRequest.visibility = View.VISIBLE
                Log.d("GG2", "View Cargando")
            }
            is UiState.Error -> {
                pBarPullRequest.visibility = View.GONE
            }
            is UiState.Success -> {
                onSuccess(state.list as List<PullRequestModel>)
                Log.d("GG2", "Recibido lista")
            }
            is UiState.Empty -> {
                print("hello")
            }
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
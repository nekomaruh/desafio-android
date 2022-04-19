package com.example.desafio_android_accenture.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafio_android_accenture.data.imageloader.ImageLoader
import com.example.desafio_android_accenture.data.imageloader.ImageLoaderService
import com.example.desafio_android_accenture.data.model.RepositoryModel
import com.example.desafio_android_accenture.databinding.FragmentRepositoryBinding
import com.example.desafio_android_accenture.presentation.viewmodel.MainViewModel
import com.example.desafio_android_accenture.presentation.viewmodel.MyListState
import com.example.desafio_android_accenture.ui.adapters.RepositoryAdapter

private const val TAG = "TAG"

class RepositoryFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private var _binding: FragmentRepositoryBinding? = null
    private val binding get() = _binding!!
    private val imageLoader: ImageLoader = ImageLoaderService()
    private val repositoryAdapter = RepositoryAdapter(manager = RepositoryManager())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRepositoryBinding.inflate(inflater, container, false)
        Log.i(TAG,"ON CREATE VIEW")
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        Log.i(TAG,"ON VIEW CREATED")

        viewModel = activity?.let { ViewModelProvider(it)[MainViewModel::class.java] }!!

        with(binding.rvRepository) {
            adapter = repositoryAdapter
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }

        with(viewModel) {
            activity?.let { ViewModelProvider(it)[MainViewModel::class.java] }!!
            repositoryList.observe(viewLifecycleOwner, repositoryAdapter::addRepositories)
            isLoading.observe(viewLifecycleOwner) { binding.pBarRepository.isVisible = it }
            repositoryListState.observe(viewLifecycleOwner) { renderViewState(it) }
            getRepositories(1)
            Toast.makeText(context, "LOAD", Toast.LENGTH_SHORT).show()
        }
    }

    private fun renderViewState(state: MyListState) {
        when (state) {
            is MyListState.Loading -> binding.pBarRepository.isVisible = true
            is MyListState.Success -> binding.pBarRepository.isVisible = false
            is MyListState.Error -> {
                binding.pBarRepository.isVisible = false
                Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
            }
        }
    }

    inner class RepositoryManager : RepositoryAdapter.AdapterManager {
        override fun onRepositoryClick(item: RepositoryModel) {
            val action =
                RepositoryFragmentDirections.navToPullRequest(
                    repoTitle = item.name,
                    repoUser = item.user.login,
                    issuesOpened = item.issuesOpened
                )
            findNavController().navigate(action)
        }

        override fun provideImageLoader(): ImageLoader = imageLoader
    }

}
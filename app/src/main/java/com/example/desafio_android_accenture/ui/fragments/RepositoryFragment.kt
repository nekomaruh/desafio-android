package com.example.desafio_android_accenture.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafio_android_accenture.data.imageloader.ImageLoader
import com.example.desafio_android_accenture.data.imageloader.ImageLoaderService
import com.example.desafio_android_accenture.data.model.RepositoryModel
import com.example.desafio_android_accenture.databinding.FragmentRepositoryBinding
import com.example.desafio_android_accenture.presentation.model.RepositoryItem
import com.example.desafio_android_accenture.presentation.viewmodel.RepositoryViewModel
import com.example.desafio_android_accenture.presentation.viewmodel.ListState
import com.example.desafio_android_accenture.ui.adapters.RepositoryAdapter
import com.example.desafio_android_accenture.utils.mappers.toPullRequestItem
import com.example.desafio_android_accenture.utils.mappers.toRepositoryItem
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "TAG"

@AndroidEntryPoint
class RepositoryFragment : Fragment() {

    private val viewModel: RepositoryViewModel by viewModels()
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
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.rvRepository) {
            adapter = repositoryAdapter
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }

        with(viewModel) {
            repositoryListState.observe(viewLifecycleOwner, ::renderViewState)
            Toast.makeText(context, "LOAD", Toast.LENGTH_SHORT).show()
            getRepositories(1)
        }
    }

    private fun renderViewState(state: ListState<RepositoryModel>) = with(binding) {
        when (state) {
            is ListState.Loading -> {
                rvRepository.visibility = View.INVISIBLE
                pBarRepository.visibility = View.VISIBLE
            }
            is ListState.Success -> {
                val list = state.list
                pBarRepository.visibility = View.GONE
                rvRepository.visibility = View.VISIBLE
                if (list.isNotEmpty()) {
                    val items = list.map { it.toRepositoryItem() }
                    repositoryAdapter.addRepositories(items)
                }else{
                    Toast.makeText(context, "Empty", Toast.LENGTH_LONG).show()
                }
            }
            is ListState.Error -> {
                pBarRepository.visibility = View.GONE
                Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
            }
        }
    }

    inner class RepositoryManager : RepositoryAdapter.AdapterManager {
        override fun onRepositoryClick(item: RepositoryItem) {
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
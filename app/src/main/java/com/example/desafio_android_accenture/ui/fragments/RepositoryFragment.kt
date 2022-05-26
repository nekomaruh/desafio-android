package com.example.desafio_android_accenture.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android_accenture.data.imageloader.ImageLoader
import com.example.desafio_android_accenture.data.imageloader.ImageLoaderService
import com.example.desafio_android_accenture.data.model.RepositoryModel
import com.example.desafio_android_accenture.databinding.FragmentRepositoryBinding
import com.example.desafio_android_accenture.presentation.model.RepositoryItem
import com.example.desafio_android_accenture.presentation.viewmodel.RepositoryViewModel
import com.example.desafio_android_accenture.presentation.viewmodel.ListState
import com.example.desafio_android_accenture.ui.adapters.RepositoryAdapter
import com.example.desafio_android_accenture.utils.extensions.addVerticalDivider
import com.example.desafio_android_accenture.utils.extensions.longToast
import com.example.desafio_android_accenture.utils.extensions.observe
import com.example.desafio_android_accenture.utils.extensions.shortToast
import com.example.desafio_android_accenture.utils.mappers.toRepositoryItem
import dagger.hilt.android.AndroidEntryPoint

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

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        with(rvRepository) {
            addVerticalDivider()
            val manager = LinearLayoutManager(context)
            adapter = repositoryAdapter
            layoutManager = manager
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val currentItems = manager.childCount
                    val scrollOutItems = manager.findFirstVisibleItemPosition()
                    val totalItems = manager.itemCount

                    with(viewModel) {
                        if (repositoryListState.value is ListState.Loading) return
                        if (currentItems + scrollOutItems >= totalItems) {
                            pBarRepository.visibility = View.VISIBLE
                            getNextRepositoriesPage()
                        }
                    }

                    super.onScrolled(recyclerView, dx, dy)
                }
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(viewModel) {
            observe(repositoryListState, ::renderViewState)
            getRepositories(index = 0)
        }
    }

    private fun renderViewState(state: ListState<RepositoryModel>) = with(binding) {
        when (state) {
            is ListState.Loading -> {
                pBarRepository.visibility = View.VISIBLE
            }
            is ListState.Success -> {
                val list = state.list
                pBarRepository.visibility = View.GONE
                rvRepository.visibility = View.VISIBLE
                if (list.isNotEmpty()) {
                    val items = list.map { it.toRepositoryItem() }
                    repositoryAdapter.addItems(items)
                } else {
                    context?.longToast("Empty")
                }
            }
            is ListState.Error -> {
                pBarRepository.visibility = View.GONE
                context?.longToast("Error")
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
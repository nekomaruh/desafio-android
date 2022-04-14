package com.example.desafio_android_accenture.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafio_android_accenture.data.imageloader.ImageLoader
import com.example.desafio_android_accenture.data.imageloader.ImageLoaderService
import com.example.desafio_android_accenture.ui.adapters.RepositoryAdapter
import com.example.desafio_android_accenture.data.model.RepositoryModel
import com.example.desafio_android_accenture.databinding.FragmentRepositoryBinding
import com.example.desafio_android_accenture.ui.viewmodel.MainViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [RepositoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class RepositoryFragment : Fragment() {

    private lateinit var binding: FragmentRepositoryBinding
    private lateinit var viewModel: MainViewModel
    private val imageLoader: ImageLoader = ImageLoaderService()
    private val repositoryAdapter = RepositoryAdapter(manager = RepositoryManager())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.let { ViewModelProvider(it)[MainViewModel::class.java] }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepositoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(view.context)
    }

    private fun initRecyclerView(context: Context) {
        with(binding.idRepoRecyclerview) {
            adapter = repositoryAdapter
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
        with(viewModel) {
            repositoryList.observe(viewLifecycleOwner, repositoryAdapter::addRepositories)
        }
    }

    inner class RepositoryManager : RepositoryAdapter.AdapterManager {
        override fun onRepositoryClick(item: RepositoryModel) {
            val action =
                RepositoryFragmentDirections.actionRepositoryFragmentToPullRequestFragment(
                    item.name,
                    item.user.login,
                    item.issuesOpened
                )
            findNavController().navigate(action)
        }

        override fun provideImageLoader(): ImageLoader {
            return imageLoader
        }
    }


}
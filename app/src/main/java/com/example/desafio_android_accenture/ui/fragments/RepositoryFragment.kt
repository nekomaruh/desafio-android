package com.example.desafio_android_accenture.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafio_android_accenture.adapters.RepositoryAdapter
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
        binding = FragmentRepositoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(view.context)
    }

    private fun initRecyclerView(context: Context) {
        val recyclerView = binding.idRepoRecyclerview
        val adapter = RepositoryAdapter { repoModel -> onItemClick(repoModel) }
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        )
        viewModel.repositoryList.observe(viewLifecycleOwner, Observer {
            adapter.addRepositories(it)
        })
    }

    private fun onItemClick(repoModel: RepositoryModel) {
        val action =
            RepositoryFragmentDirections.actionRepositoryFragmentToPullRequestFragment(
                repoModel.name,
                repoModel.user.login,
                repoModel.issuesOpened
            )
        findNavController().navigate(action)
    }


}
package com.example.desafio_android_accenture.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafio_android_accenture.adapters.RepoAdapter
import com.example.desafio_android_accenture.data.model.RepositoryModel
import com.example.desafio_android_accenture.databinding.ActivityMainBinding
import com.example.desafio_android_accenture.ui.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initLoadingProgressBar()
    }

    private fun initLoadingProgressBar(){
        mainViewModel.isLoading.observe(this, Observer {
            binding.idProgressbarMain.isVisible = it
            }
        )
    }

    private fun initRecyclerView() {
        val recyclerView = binding.idRepoRecyclerview
        val adapter = RepoAdapter { repoModel -> onItemClick(repoModel) }
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )
        mainViewModel.repositoryList.observe(this, Observer {
            adapter.addRepositories(it)
        })
    }

    private fun onItemClick(repoModel: RepositoryModel){
        val intent = Intent(this, PullRequestActivity::class.java)
        intent.putExtra("repo_title", repoModel.name)
        intent.putExtra("repo_user", repoModel.user.login)
        intent.putExtra("issues_opened", repoModel.issuesOpened)
        //intent.putExtra("issues_closed", repoModel.issuesClosed)
        startActivity(intent)
    }
}
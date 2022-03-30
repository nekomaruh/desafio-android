package com.example.desafio_android_accenture.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafio_android_accenture.adapters.RepoAdapter
import com.example.desafio_android_accenture.data.models.RepoModel
import com.example.desafio_android_accenture.data.models.RepoProvider
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
    }

    private fun initRecyclerView() {
        binding.idRepoRecyclerview.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )
        binding.idRepoRecyclerview.adapter = RepoAdapter(RepoProvider.getRepositories()){
            repoModel -> onItemClick(repoModel)
        }
    }

    private fun onItemClick(repoModel: RepoModel){
        val intent = Intent(this, PullRequestActivity::class.java)
        intent.putExtra("repo_title", repoModel.title)
        intent.putExtra("issues_opened", repoModel.issuesOpened)
        intent.putExtra("issues_closed", repoModel.issuesClosed)
        startActivity(intent)
    }
}
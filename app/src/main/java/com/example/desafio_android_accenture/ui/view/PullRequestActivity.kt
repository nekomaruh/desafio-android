package com.example.desafio_android_accenture.ui.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafio_android_accenture.adapters.PullRequestAdapter
import com.example.desafio_android_accenture.databinding.ActivityPullRequestBinding
import com.example.desafio_android_accenture.ui.viewmodel.PullRequestViewModel

class PullRequestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPullRequestBinding
    private val pullRequestsViewModel: PullRequestViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPullRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initActionBar()
        initListHeader()
        initLoadingProgressBar()
        initRecyclerView()
        initViewModel()
    }

    private fun initLoadingProgressBar(){
        pullRequestsViewModel.isLoading.observe(this, Observer {
            binding.idProgressbarPulls.isVisible = it
            }
        )
    }

    private fun initViewModel() {
        val title = intent.getStringExtra("repo_title")
        val user = intent.getStringExtra("repo_user")
        if(title != null && user !=null){
            pullRequestsViewModel.onCreate(user,title)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initListHeader() {
        val opened = intent.getStringExtra("issues_opened")
        //val closed = intent.getStringExtra("issues_closed")
        binding.idOpenIssues.text = "$opened opened"
        binding.idClosedIssues.text = " / ? closed"
    }

    private fun initRecyclerView() {
        val recyclerView = binding.idPullRequestRecyclerView
        val adapter = PullRequestAdapter()
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )
        //recyclerView.adapter = PullRequestAdapter()
        pullRequestsViewModel.pullRequestList.observe(this, Observer {
            adapter.addPullRequests(it)
        })

    }

    private fun initActionBar(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = intent.getStringExtra("repo_title")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
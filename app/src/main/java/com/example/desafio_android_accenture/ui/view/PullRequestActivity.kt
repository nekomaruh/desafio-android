package com.example.desafio_android_accenture.ui.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafio_android_accenture.adapters.PullRequestAdapter
import com.example.desafio_android_accenture.data.models.PullRequestProvider
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
        initRecyclerView()
    }

    @SuppressLint("SetTextI18n")
    private fun initListHeader() {
        val opened = intent.getStringExtra("issues_opened")
        val closed = intent.getStringExtra("issues_closed")
        binding.idOpenIssues.text = "$opened opened"
        binding.idClosedIssues.text = " / $closed closed"
    }

    private fun initRecyclerView() {
        binding.idPullRequestRecyclerView.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )
        binding.idPullRequestRecyclerView.adapter = PullRequestAdapter(PullRequestProvider.getPullRequests())
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
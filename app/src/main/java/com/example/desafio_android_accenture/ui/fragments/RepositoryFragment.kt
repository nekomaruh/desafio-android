package com.example.desafio_android_accenture.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.desafio_android_accenture.R
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
    ): View? {
        //binding = FragmentRepositoryBinding.inflate(layoutInflater)
        return inflater.inflate(R.layout.fragment_repository, container, false)
    }


}
package com.example.comfynovel.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.comfynovel.base.BaseFragment
import com.example.comfynovel.base.ScreenState
import com.example.comfynovel.common.NovelListAdapter
import com.example.comfynovel.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var novelAdapter: NovelListAdapter

    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTrendingNovels()
        novelAdapter = NovelListAdapter()

        setupDataListener()
    }

    private fun setupDataListener() {
        viewModel.trendingNovels.observe(this) {
            when (it) {
                is ScreenState.Loading -> showToast("Loading")
                is ScreenState.Success -> novelAdapter.submitList(it.data)
                is ScreenState.Error -> showToast("Error")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
package com.example.comfynovel.feature.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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

    private lateinit var trendingNovelAdapter: NovelListAdapter
    private lateinit var updatesNovelAdapter: NovelListAdapter
    private lateinit var completedNovelAdapter: NovelListAdapter

    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupDataListener()
        setupUIListener()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupUIListener() = with(binding) {
        etSearch.setOnClickListener {
            showToast("Search")
        }
    }

    private fun setupUI() = with(binding) {
        trendingNovelAdapter = NovelListAdapter()
        rvTrending.adapter = trendingNovelAdapter

        updatesNovelAdapter = NovelListAdapter()
        rvUpdates.adapter = updatesNovelAdapter

        completedNovelAdapter = NovelListAdapter()
        rvCompleted.adapter = completedNovelAdapter
    }

    private fun setupDataListener() {
        viewModel.trendingNovels.observe(this) {
            when (it) {
                is ScreenState.Loading -> Log.i("Hasil", "Loading trending")
                is ScreenState.Success -> trendingNovelAdapter.submitList(it.data)
                is ScreenState.Error -> Log.i("Hasil", "Error trending")
            }
        }

        viewModel.updatesNovels.observe(this) {
            when (it) {
                is ScreenState.Loading -> Log.i("Hasil", "Loading updates")
                is ScreenState.Success -> updatesNovelAdapter.submitList(it.data)
                is ScreenState.Error -> Log.i("Hasil", "Error updates")
            }
        }

        viewModel.completedNovels.observe(this) {
            when (it) {
                is ScreenState.Loading -> Log.i("Hasil", "Loading completed")
                is ScreenState.Success -> completedNovelAdapter.submitList(it.data)
                is ScreenState.Error -> Log.i("Hasil", "Error completed")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
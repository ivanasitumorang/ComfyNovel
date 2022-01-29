package com.example.comfynovel.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.comfynovel.base.BaseFragment
import com.example.comfynovel.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {



    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
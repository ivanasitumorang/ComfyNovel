package com.example.comfynovel.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comfynovel.base.ScreenState
import com.example.comfynovel.common.DummyNovel
import com.example.comfynovel.common.Novel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _trendingNovels = MutableLiveData<ScreenState<List<Novel>>>(ScreenState.Loading)
    val trendingNovels: LiveData<ScreenState<List<Novel>>> get() = _trendingNovels
    fun getTrendingNovels() {
        viewModelScope.launch {
            delay(1000)
            _trendingNovels.postValue(ScreenState.Success(DummyNovel.trendingNovels))
        }
    }
}
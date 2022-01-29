package com.example.comfynovel.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comfynovel.base.ScreenState
import com.example.comfynovel.common.DummyNovel
import com.example.comfynovel.common.Novel
import com.example.comfynovel.common.NovelSection
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    init {
        getTrendingNovels()
        getUpdatesNovels()
        getCompletedNovels()
    }

    private val _trendingNovels = MutableLiveData<ScreenState<List<Novel>>>(ScreenState.Loading)
    val trendingNovels: LiveData<ScreenState<List<Novel>>> get() = _trendingNovels
    fun getTrendingNovels() {
        viewModelScope.launch {
            delay(1000)
            _trendingNovels.postValue(ScreenState.Success(DummyNovel.trendingNovels))
        }
    }

    private val _updatesNovels = MutableLiveData<ScreenState<List<Novel>>>(ScreenState.Loading)
    val updatesNovels: LiveData<ScreenState<List<Novel>>> get() = _updatesNovels
    fun getUpdatesNovels() {
        viewModelScope.launch {
            delay(1000)
            val updatesNovels = DummyNovel.trendingNovels.map {
                it.copy(section = NovelSection.RecentUpdate)
            }.shuffled()
            _updatesNovels.postValue(ScreenState.Success(updatesNovels))
        }
    }

    private val _completedNovels = MutableLiveData<ScreenState<List<Novel>>>(ScreenState.Loading)
    val completedNovels: LiveData<ScreenState<List<Novel>>> get() = _completedNovels
    fun getCompletedNovels() {
        viewModelScope.launch {
            delay(1000)
            val completedNovels = DummyNovel.trendingNovels
                .filter { it.completed }
                .map { it.copy(section = NovelSection.Completed) }
                .shuffled()
            _completedNovels.postValue(ScreenState.Success(completedNovels))
        }
    }
}
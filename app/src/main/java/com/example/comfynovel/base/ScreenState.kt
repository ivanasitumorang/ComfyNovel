package com.example.comfynovel.base

sealed class ScreenState<out T> {
    object Loading : ScreenState<Nothing>()
    data class Success<out S>(val data: S) : ScreenState<S>()
    data class Error(val exception: Exception) : ScreenState<Nothing>()
}
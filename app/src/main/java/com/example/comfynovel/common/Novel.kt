package com.example.comfynovel.common

data class Novel(
    val id: String,
    val title: String,
    val imageUrl: String = "",
    val lastChapter: String = "",
    val lastUpdate: String = "",
    val completed: Boolean = false,
    val section: NovelSection = NovelSection.Trending
)

enum class NovelSection {
    RecentUpdate,
    Trending,
    Completed,
    Continue
}
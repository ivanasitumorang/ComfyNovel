package com.example.comfynovel.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.comfynovel.databinding.ItemNovelBinding
import com.squareup.picasso.Picasso

class NovelListAdapter : ListAdapter<Novel, NovelListAdapter.NovelVH>(NovelDiffUtilCallback) {

    class NovelVH(private val binding: ItemNovelBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(novel: Novel, position: Int) {
            with(binding) {
                Picasso.get().load(novel.imageUrl).into(ivCover)
                tvTitle.text = novel.title
                tvRanking.text = position.toString()
            }
        }

        companion object {
            fun createVH(parent: ViewGroup): NovelVH {
                return NovelVH(
                    ItemNovelBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NovelVH {
        return NovelVH.createVH(parent)
    }

    override fun onBindViewHolder(holder: NovelVH, position: Int) {
        holder.bind(getItem(position), position + 1)
    }
}

object NovelDiffUtilCallback : DiffUtil.ItemCallback<Novel>() {
    override fun areItemsTheSame(oldItem: Novel, newItem: Novel): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Novel, newItem: Novel): Boolean =
        oldItem == newItem

}
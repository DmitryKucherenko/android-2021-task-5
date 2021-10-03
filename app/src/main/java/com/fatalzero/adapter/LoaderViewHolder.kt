package com.fatalzero.adapter

import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.fatalzero.databinding.ItemCatLoaderBinding

class LoaderViewHolder(binding: ItemCatLoaderBinding, var retry: () -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    private val motionLayout: MotionLayout = binding.mlLoader
    private val retryButton = binding.btnRetry

    fun bind(loadState: LoadState) {
        retryButton.setOnClickListener { retry() }
        if (loadState is LoadState.Loading) {
            motionLayout.transitionToEnd()
        } else {
            motionLayout.transitionToStart()
        }
    }
}

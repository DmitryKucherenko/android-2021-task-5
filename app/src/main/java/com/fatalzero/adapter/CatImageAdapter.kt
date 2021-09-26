package com.fatalzero.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.fatalzero.databinding.CatItemBinding
import com.fatalzero.model.Cat
import com.fatalzero.ui.ItemClickListener

class CatImageAdapter(private val itemClickListener: ItemClickListener?) : PagingDataAdapter<Cat, CatHolder>(DiffCallback) {




    override fun onBindViewHolder(holder: CatHolder, position: Int) {
        val cat = getItem(position)

            holder.bind(cat)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CatItemBinding.inflate(layoutInflater, parent, false)
        return CatHolder(itemClickListener, binding,parent.context)
    }
}
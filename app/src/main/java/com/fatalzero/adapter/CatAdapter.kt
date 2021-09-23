package com.fatalzero.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.fatalzero.databinding.CatItemBinding
import com.fatalzero.model.Cat
import com.fatalzero.ui.ItemClickListener

class CatAdapter(private val itemClickListener: ItemClickListener?) : ListAdapter<Cat, CatHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CatItemBinding.inflate(layoutInflater, parent, false)
        return CatHolder(itemClickListener, binding,parent.context)
    }

    override fun onBindViewHolder(holder: CatHolder, position: Int) {
        val car = currentList[position]
        holder.bind(car)
    }

    override fun getItemCount()=currentList.size
}
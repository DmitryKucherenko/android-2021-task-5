package com.fatalzero.adapter

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fatalzero.databinding.CatFragmentBinding
import com.fatalzero.model.Cat
import com.fatalzero.ui.ItemClickListener

class CatHolder(
    private val itemClickListener: ItemClickListener?,
    binding: CatFragmentBinding

) : RecyclerView.ViewHolder(binding.root) {
    private var cat: Cat? = null
    private val urlText: TextView = binding.urlText



    fun bind(cat: Cat) {
        this.cat = cat
        urlText.text = cat.url
        itemView.setOnClickListener{
            itemClickListener?.onItemClick(cat.id)
        }



    }

}
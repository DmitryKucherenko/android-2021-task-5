package com.fatalzero.adapter

import android.content.Context
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.fatalzero.databinding.CatItemBinding
import com.fatalzero.model.Cat
import com.fatalzero.ui.ItemClickListener

class CatHolder(
    private val itemClickListener: ItemClickListener?,
    binding: CatItemBinding, var context: Context

) : RecyclerView.ViewHolder(binding.root) {
    private var cat: Cat? = null
    private val urlText: TextView = binding.urlText
    private val catImageView = binding.imageView



    fun bind(cat: Cat) {
        this.cat = cat
        urlText.text = cat.url

        Glide.with(context)
            .load(cat.url)
            .optionalTransform(CenterCrop())
            .into(catImageView)

        itemView.setOnClickListener{
            itemClickListener?.onItemClick(cat.url)
        }



    }

}
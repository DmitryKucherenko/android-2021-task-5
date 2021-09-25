package com.fatalzero.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.fatalzero.R


import com.fatalzero.databinding.CatInfoBinding


class CatInfoFragment : Fragment() {

    private var url:String? = null
    private var _binding: CatInfoBinding? = null
    private val binding get() = _binding!!


    private val infoViewModel:CatInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CatInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        url = arguments?.getString("url")


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         val catInfoImageView:ImageView = binding.imageView
        Glide.with(requireActivity())
            .load(url)
            //.placeholder(R.drawable.ic_cat_empty)

            .into(catInfoImageView)



    }




}

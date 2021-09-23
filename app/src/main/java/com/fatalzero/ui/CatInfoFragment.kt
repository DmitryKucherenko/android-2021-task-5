package com.fatalzero.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.fatalzero.R
import com.fatalzero.databinding.CatInfoBinding
import com.fatalzero.databinding.CatsListFragmentBinding

class CatInfoFragment : Fragment() {

    private var url:String? = null
    private var _binding: CatInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CatViewModel

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
        Glide.with(requireActivity())
            .load(url)
            .optionalTransform(CenterCrop())
            .into(binding.imageView)

    }
}
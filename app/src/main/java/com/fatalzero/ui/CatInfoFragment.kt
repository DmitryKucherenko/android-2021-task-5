package com.fatalzero.ui

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.fatalzero.R
import com.fatalzero.databinding.CatInfoBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

private const val URL = "url"
private const val CODE = 100

class CatInfoFragment : Fragment() {
    private val viewModel: CatInfoViewModel by viewModels()
    private var _binding: CatInfoBinding? = null
    private val binding get() = _binding!!
    private var saveButton: FloatingActionButton? = null
    private var openListFragment: OpenListFragment? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        openListFragment = context as OpenListFragment
    }

    interface OpenListFragment {
        fun openListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CatInfoBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.url = arguments?.getString(URL)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val catInfoImageView: ImageView = binding.imageView
        saveButton = binding.floatingActionButton
        Glide.with(requireActivity())
            .load(viewModel.url)
            .placeholder(R.drawable.ic_cat_empty)
            .into(catInfoImageView)
        saveButton?.setOnClickListener {
            if (isPermissionGranted()) {
                viewModel.url?.let {
                    viewModel.downloadImage(it, requireContext())
                    openListFragment?.openListFragment()
                }
            } else {
                requestPermission()
            }
        }
    }

    private fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
            CODE
        )
    }
}

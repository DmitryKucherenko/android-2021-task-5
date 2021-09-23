package com.fatalzero.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fatalzero.adapter.CatAdapter
import com.fatalzero.databinding.CatsListFragmentBinding

class CatsListFragment : Fragment() {
    private var adapter: CatAdapter? = null
    private  var catsRecyclerView: RecyclerView? = null
     val viewModel:CatsListViewModel by viewModels()
    private var _binding: CatsListFragmentBinding? = null
    private val binding get() = _binding!!
    private var itemClickListener: ItemClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        itemClickListener = context as ItemClickListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CatsListFragmentBinding.inflate(inflater, container, false)
        catsRecyclerView = binding.catsRecyclerView
        catsRecyclerView?.layoutManager = LinearLayoutManager(context)
        adapter = CatAdapter(itemClickListener)
        catsRecyclerView?.adapter=adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.catsList.observe(viewLifecycleOwner,
            { cats ->
                cats?.let {
                    println("TO ADATER ${it.size}")
                    adapter?.submitList(it)

                }
            })
    }
    

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}
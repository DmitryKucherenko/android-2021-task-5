package com.fatalzero.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingSource
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fatalzero.adapter.LoaderStateAdapter
import com.fatalzero.adapter.CatImageAdapter
import com.fatalzero.data.CatImagesRepository
import com.fatalzero.databinding.CatsListFragmentBinding
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Error

private const val TAG = "CatList";

class CatsListFragment : Fragment() {
    private  var catsRecyclerView: RecyclerView? = null
     private val viewModel:CatsListViewModel by viewModels()
    private var _binding: CatsListFragmentBinding? = null
    private val binding get() = _binding!!
    private var itemClickListener: ItemClickListener? = null
    private var adapter:CatImageAdapter? = null



    override fun onAttach(context: Context) {
        super.onAttach(context)
        itemClickListener = context as ItemClickListener
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CatsListFragmentBinding.inflate(inflater, container, false)
        catsRecyclerView = binding.catsRecyclerView
        catsRecyclerView?.layoutManager = LinearLayoutManager(context)
        adapter = CatImageAdapter(itemClickListener)
        val loaderStateAdapter = LoaderStateAdapter{adapter?.retry()}
        catsRecyclerView?.adapter=adapter?.withLoadStateFooter(loaderStateAdapter)
        binding.refreshButton.setOnClickListener{


        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG,"onViewCreated")
        viewModel.catsLiveData.observe(viewLifecycleOwner,
                { cats ->

                    if(cats==null) Log.d(TAG,"NE PRILETELO")
                    cats?.let {

                        lifecycleScope.launch {
                            adapter?.submitData(it)

                        }
                    }

                })

        adapter?.addLoadStateListener {loadState ->
            Log.d(TAG, loadState.refresh::class.qualifiedName.toString())
     // if((loadState.refresh::class.qualifiedName!!)=="Error")
            if(loadState.refresh is LoadState.Error)
               if (adapter?.itemCount ?:0 < 1) {
                   binding.catsRecyclerView.visibility = GONE
                   binding.emptyView.visibility = VISIBLE
               } else {
                   binding.catsRecyclerView.visibility = VISIBLE
                   binding.emptyView.visibility = GONE
               }

        }


        Log.d(TAG,"after onViewCreated")


    }
    

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}
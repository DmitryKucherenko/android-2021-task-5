package com.fatalzero.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.fatalzero.R

private const val URL = "url"

class MainActivity : AppCompatActivity(), ItemClickListener, CatInfoFragment.OpenListFragment {
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = this.findNavController(R.id.fragment_container)
    }

    override fun onItemClick(url: String?) {
        navController?.navigate(
            R.id.action_catsListFragment_to_catInfoFragment,
            bundleOf(URL to url)
        )
    }

    override fun openListFragment() {
        navController?.popBackStack()
    }
}

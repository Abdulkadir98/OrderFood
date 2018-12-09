package com.example.admin.orderfood.ui.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.admin.orderfood.R
import com.example.admin.orderfood.adapters.FoodListAdapter
import com.example.admin.orderfood.ui.view.FoodViewModel

class FoodFragment: Fragment() {
    private lateinit var foodViewModel: FoodViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        foodViewModel = ViewModelProviders.of(this).get(FoodViewModel::class.java)
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)

        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = FoodListAdapter(this.context!!) {}
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context!!)

        foodViewModel.allFoodItems.observe(this, Observer {
            foodItems -> foodItems?.let { adapter.setFoodItems(it) }
        })

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        fun newInstance(sectionNumber: Int): FoodFragment {
            val fragment = FoodFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }
}
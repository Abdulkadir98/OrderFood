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
import com.example.admin.orderfood.model.Food
import com.example.admin.orderfood.ui.activities.DescriptionActivity
import com.example.admin.orderfood.ui.view.FoodViewModel
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class FoodFragment: Fragment() {
    private lateinit var foodViewModel: FoodViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        foodViewModel = ViewModelProviders.of(this).get(FoodViewModel::class.java)
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)

        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = FoodListAdapter(activity!!) { food: Food, view: View ->

            when(view.id) {
                R.id.container -> startActivity<DescriptionActivity>(DescriptionActivity.TITLE to food.title,
                    DescriptionActivity.DESCRIPTION to food.description,
                    DescriptionActivity.PRICE to food.price,
                    DescriptionActivity.QUANTITY to food.quantity)

                R.id.plusBtn -> {foodViewModel.increase(food.id)
                                this.toast("Added to Cart!") }
                R.id.minusBtn ->  {
                    foodViewModel.decrease(food.id)
                    this.toast("Removed from Cart!")
                }
            }

        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context!!)

        val sectionNumber = arguments!![ARG_SECTION_NUMBER].toString().toInt()

        foodViewModel.allFoodItems.observe(this, Observer {
            foodItems -> foodItems?.let {
            adapter.setFoodItems(it, sectionNumber)
        }
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
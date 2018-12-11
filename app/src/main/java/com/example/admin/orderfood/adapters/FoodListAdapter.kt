package com.example.admin.orderfood.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.admin.orderfood.R
import com.example.admin.orderfood.model.Food
import kotlinx.android.synthetic.main.food_tem.view.*

class FoodListAdapter internal constructor(
     context: Context, val itemClick: (Food, View) -> Unit
) : RecyclerView.Adapter<FoodListAdapter.FoodViewHolder>() {



    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var foodItems = emptyList<Food>() // Cached copy of food items.

    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindFoodItem(food: Food) {
            with(food) {

                itemView.foodTitle.text = title
                itemView.qtyView.text = "${quantity}"
                itemView.plusBtn.setOnClickListener { itemClick(this, it) }
                itemView.minusBtn.setOnClickListener { itemClick(this, it) }
                itemView.setOnClickListener { itemClick(this, it) }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodListAdapter.FoodViewHolder {
        val itemView = inflater.inflate(R.layout.food_tem, parent, false)
        return FoodViewHolder(itemView)
    }

    internal fun setFoodItems(foodItems: List<Food>, sectionNumber: Int) {
        when(sectionNumber) {
            1 -> this.foodItems = foodItems.filter { it.cuisine == "Indian" }
            2 -> this.foodItems = foodItems.filter { it.cuisine == "Chinese" }
            3 -> this.foodItems = foodItems.filter { it.cuisine == "Italian" }
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int  = foodItems.size

    override fun onBindViewHolder(holder: FoodListAdapter.FoodViewHolder, position: Int) {
        holder.bindFoodItem(foodItems[position])
    }
}
package com.example.admin.orderfood.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.admin.orderfood.R
import com.example.admin.orderfood.model.Food
import kotlinx.android.synthetic.main.cart_item.view.*

class CartListAdapter(context: Context) : RecyclerView.Adapter<CartViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var items = emptyList<Food>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val itemView = inflater.inflate(R.layout.cart_item, parent, false)
        return CartViewHolder(itemView)
    }

    override fun getItemCount(): Int  = items.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    internal fun setItems(items: List<Food>) {
        this.items = items
        notifyDataSetChanged()
    }

}

class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindItem(food: Food) {
        with(food) {
            itemView.foodTitle.text = title
            itemView.price.text = "Price (1 qty): ${price}"
            val totalPrice = price * quantity
            itemView.totalPrice.text = "Total Price: ${totalPrice}"

        }
    }
}
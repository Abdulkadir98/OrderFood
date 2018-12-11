package com.example.admin.orderfood.ui.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import com.example.admin.orderfood.R
import com.example.admin.orderfood.adapters.CartListAdapter
import com.example.admin.orderfood.ui.view.FoodViewModel
import kotlinx.android.synthetic.main.activity_cart.*
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

class CartActivity : AppCompatActivity() {

    private lateinit var foodViewModel: FoodViewModel
    private var grandTotal: Float = 0f
    private var totalPrice: Float = 0f

    private lateinit var editText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        title = "Cart"
        editText = find(R.id.editText)


        foodViewModel = ViewModelProviders.of(this).get(FoodViewModel::class.java)
        val recyclerView = find<RecyclerView>(R.id.recycler_view)
        val adapter = CartListAdapter(this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        foodViewModel.cartFoodItems.observe(this, Observer {
            cartItems -> cartItems?.let {

            if(cartItems.size > 0) {
                emptyView.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                couponContainer.visibility = View.VISIBLE
                grandTotalContainer.visibility = View.VISIBLE
            }


            adapter.setItems(it)
            it.forEach {
                totalPrice = it.price * it.quantity
                grandTotal += totalPrice
            }

            grandTotalText.text = "Grand Total: ${grandTotal}"
        }
        })

        applyBtn.setOnClickListener {
            if(TextUtils.isEmpty(editText.text)) {
                toast("Enter a valid coupon code!")
            }
            else {
                if( editText.text.toString() == "F22LABS" && grandTotal > 400f) {
                    grandTotalText.text = "Grand Total: ${grandTotal * 0.8}"
                    toast("coupon applied!")

                }
                else if (editText.text.toString() == "F22LABS" && grandTotal <= 400f) {
                    toast("Coupon valid only for orders greater than 400!")
                }
            }
        }

    }
}

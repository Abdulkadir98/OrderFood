package com.example.admin.orderfood.ui.activities

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageButton
import com.example.admin.orderfood.R
import com.example.admin.orderfood.ui.view.FoodViewModel
import kotlinx.android.synthetic.main.activity_description.*
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

class DescriptionActivity : AppCompatActivity() {

    private lateinit var plusBtn: ImageButton
    private lateinit var minusBtn: ImageButton

    private lateinit var foodViewModel: FoodViewModel


    companion object {
        val TITLE = "DescriptionActivity:title"
        val DESCRIPTION = "DescriptionActivity:description"
        val PRICE = "DescriptionActivity:price"
        val QUANTITY = "DescriptionActivity:quantity"
        val ID = "DescriptionActivity:id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        foodViewModel = ViewModelProviders.of(this).get(FoodViewModel::class.java)


        plusBtn = find(R.id.plus_btn)
        minusBtn = find(R.id.minus_btn)

       title = intent.getStringExtra(TITLE)
       description.text = intent.getStringExtra(DESCRIPTION)
        val price = intent.getFloatExtra(PRICE, -1f)
        val qty = intent.getIntExtra(QUANTITY, -1)
        val id = intent.getIntExtra(ID, -1)

        plusBtn.setOnClickListener { foodViewModel.increase(id)
                                    toast("Added to cart!") }

        minusBtn.setOnClickListener { foodViewModel.decrease(id)
                                        toast("removed from cart!") }


    }
}

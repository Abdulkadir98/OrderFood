package com.example.admin.orderfood.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.admin.orderfood.R
import kotlinx.android.synthetic.main.activity_description.*

class DescriptionActivity : AppCompatActivity() {

    companion object {
        val TITLE = "DescriptionActivity:title"
        val DESCRIPTION = "DescriptionActivity:description"
        val PRICE = "DescriptionActivity:price"
        val QUANTITY = "DescriptionActivity:quantity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)



       title = intent.getStringExtra(TITLE)
       description.text = intent.getStringExtra(DESCRIPTION)
        val price = intent.getFloatExtra(PRICE, -1f)
        val qty = intent.getIntExtra(QUANTITY, -1)





    }
}

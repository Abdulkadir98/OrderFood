package com.example.admin.orderfood.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.admin.orderfood.ui.fragments.FoodFragment

class CuisinePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {

        return FoodFragment.newInstance(position + 1)
    }

    override fun getCount(): Int {
        return 3;
    }

    override fun getPageTitle(position: Int): CharSequence?  = when(position) {
        0 -> "Indian"
        1 -> " Chinese"
        2 -> "Italian"
        else -> null
    }
}
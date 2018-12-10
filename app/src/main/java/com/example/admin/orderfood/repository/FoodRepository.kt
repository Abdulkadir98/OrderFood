package com.example.admin.orderfood.repository

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import com.example.admin.orderfood.model.Food
import com.example.admin.orderfood.model.FoodDao

class FoodRepository(private val foodDao: FoodDao) {

    val allFoodItems: LiveData<List<Food>> = foodDao.getAllFood()
    val cartItems: LiveData<List<Food>> = foodDao.getFoodItemsInCart()

    @WorkerThread
    suspend fun insert(food: Food){
        foodDao.insert(food)
    }

    @WorkerThread
    suspend fun increase(id: Int) {
        foodDao.increaseQuantityOfFood(id)
    }

    @WorkerThread
    suspend fun decrease(id: Int) {
        foodDao.decreaseQuantityOfFood(id)
    }


}
package com.example.admin.orderfood.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface FoodDao {
    @Insert
    fun insert(food: Food)

    @Query("DELETE FROM food_table")
    fun deleteAll()

    @Query("SELECT * FROM food_table WHERE cuisine LIKE :name")
    fun getAllFoodByCuisine(name: String) : LiveData<List<Food>>

    @Query("SELECT * FROM food_table")
    fun getAllFood() : LiveData<List<Food>>
    @Query("UPDATE food_table SET quantity = quantity + 1 WHERE id = :id")
    fun increaseQuantityOfFood(id: Int)

    @Query("UPDATE food_table SET quantity = quantity - 1 WHERE quantity > 0 AND id = :id")
    fun decreaseQuantityOfFood(id: Int)

    @Query("SELECT * FROM food_table WHERE quantity > 0")
    fun getFoodItemsInCart() : LiveData<List<Food>>
}
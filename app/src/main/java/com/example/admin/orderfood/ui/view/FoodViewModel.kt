package com.example.admin.orderfood.ui.view

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.admin.orderfood.model.Food
import com.example.admin.orderfood.model.FoodRoomDatabase
import com.example.admin.orderfood.repository.FoodRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class FoodViewModel(application: Application) : AndroidViewModel(application) {
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: FoodRepository
    val allFoodItems: LiveData<List<Food>>

    init {
        val foodDao = FoodRoomDatabase.getDatabase(application, scope).foodDao()
        repository = FoodRepository(foodDao)
        allFoodItems = repository.allFoodItems
    }

    fun increase (id: Int) = scope.launch(Dispatchers.IO) {
        repository.increase(id)
    }
    fun decrease (id: Int) = scope.launch(Dispatchers.IO) {
        repository.decrease(id)
    }


    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }


}
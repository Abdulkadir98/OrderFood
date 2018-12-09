package com.example.admin.orderfood.model

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Food::class], version = 1)
public abstract class FoodRoomDatabase: RoomDatabase() {
    abstract fun foodDao(): FoodDao

    companion object {
        @Volatile
        private var INSTANCE: FoodRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): FoodRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FoodRoomDatabase::class.java,
                    "Word_database"
                ).addCallback(FoodDatabaseCallback(scope)).build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class FoodDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.foodDao())
                }
            }
        }

        fun populateDatabase(foodDao: FoodDao) {
            foodDao.deleteAll()

            var food = Food(
                title = "Biriyani", description = "Mutton rice with masala!", price = 100f,
                cuisine = "Indian"
            )
            foodDao.insert(food)
            food = Food(title = "Pasta", description = "Baked bread with toppings", price = 100f, cuisine = "Italian")
            foodDao.insert(food)
            food = Food(title = "Dumplings", description = "A chinese dish", price = 60f, cuisine = "Chinese")
            foodDao.insert(food)
        }

    }
}


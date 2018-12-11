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
                title = "Biriyani", description = " is a mixed rice dish with its origins among the Muslims of the " +
                        "Indian subcontinent.  It is made with Indian spices, rice, meat (chicken, goat, beef, prawn, or" +
                        " fish), vegetables or eggs", price = 200f,
                cuisine = "Indian", quantity = 0
            )
            foodDao.insert(food)
            food = Food(title = "Dosa", description = "Dosa is a type of pancake originating from the Indian subcontinent" +
                    ", made from a fermented batter. It is somewhat similar to a crepe in appearance. Its main " +
                    "ingredients are rice and black gram. Dosa is a typical part of the Southern Indian and Sri Lankan " +
                    "diets but is now popular all over the Indian subcontinent.", price = 100f,
                cuisine = "Indian", quantity = 0)
            foodDao.insert(food)
            food = Food(title = "Chapati", description = " also known as roti, safati, shabaati, phulka and " +
                    "(in the Maldives) roshi,[1] is an unleavened flatbread from the Indian Subcontinent and staple in " +
                    "India, Nepal, Bangladesh, Pakistan, Sri Lanka, East Africa and the Caribbean.[2] Chapatis are made" +
                    " of whole wheat flour known as atta, mixed into dough with water and optional salt in a mixing " +
                    "utensil called a parat, and is cooked on a tava", price = 100f,
                cuisine = "Indian", quantity = 0)
            foodDao.insert(food)
            food = Food(title = "Pasta", description = " It is a staple food[1] of traditional Italian cuisine, with the " +
                    "first reference dating to 1154 in Sicily.[2] Also commonly used to refer to the variety dishes made" +
                    " with it, pasta is typically made from an unleavened dough of a durum wheat flour mixed with water" +
                    " or eggs, and formed into sheets or various shapes, then cooked by boiling or baking", price = 100f,
                cuisine = "Italian",
                quantity = 0)
            foodDao.insert(food)
            food = Food(title = "Panzenella", description = "Baked bread with toppings", price = 200f, cuisine = "Italian",
                quantity = 0)
            foodDao.insert(food)
            food = Food(title = "Margherita Pizza", description = "Pizza Margherita is a typical Neapolitan pizza, made " +
                    "with San Marzano tomatoes, mozzarella fior di latte, fresh basil, salt and extra-virgin olive oil",
                price = 200f, cuisine = "Italian",
                quantity = 0)
            foodDao.insert(food)
            food = Food(title = "Dumplings", description = "Dumpling is a broad classification for a dish that consists" +
                    " of pieces of dough (made from a variety of starch sources) wrapped around a filling or of dough " +
                    "with no filling. The dough can be based on bread, flour, or potatoes, and may be filled with meat, " +
                    "fish, cheese, vegetables, fruits, or sweets", price = 60f, cuisine = "Chinese",
                quantity = 0)
            foodDao.insert(food)
            food = Food(title = "Spinach Noodles", description = "Noodles are a staple food in many cultures. They are " +
                    "made from unleavened dough which is stretched, extruded, or rolled flat and cut into one of a " +
                    "variety of shapes. While long, thin strips may be the most common, many varieties of noodles are " +
                    "cut into waves, helices, tubes, strings, or shells, or folded over, or cut into other shapes. " +
                    "Noodles are usually cooked in boiling water, sometimes with cooking oil or salt added",
                price = 150f, cuisine = "Chinese",
                quantity = 0)
            foodDao.insert(food)
            food = Food(title = "Fried Mashi", description = "Shaanxi Pasta (mashi or cat's ear noodles), a kind of" +
                    " pasta favored by northwestern Chinese, can be either braised (like Braised Shaanxi Pasta) or " +
                    "stir-fried." + "It's rare to eat it at the local people's home especially at the youth's nowadays," +
                    " since the way of" +
                    " making Shaanxi Pasta does need skills", price = 90f, cuisine = "Chinese",
                quantity = 0)
            foodDao.insert(food)
        }

    }
}


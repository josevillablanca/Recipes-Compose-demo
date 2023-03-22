package com.cotemustis.myrecipes.data.database

import androidx.room.*
import com.cotemustis.myrecipes.data.database.model.RecipeDatabaseModel

@Dao
internal interface RecipesDao {

    @Transaction
    @Query("SELECT * FROM recipes")
    suspend fun getRecipes(): List<RecipeDatabaseModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipes: List<RecipeDatabaseModel>)

    //TODO Search by Name or Ingredients
}
package com.cotemustis.myrecipes.data.database

import androidx.room.*
import com.cotemustis.myrecipes.data.database.model.RecipeDatabaseModel

@Dao
internal interface RecipesDao {

    @Transaction
    @Query("SELECT * FROM recipes")
    suspend fun getRecipes(): List<RecipeDatabaseModel>

    @Transaction
    @Query("DELETE FROM recipes")
    suspend fun deleteAllRecipes()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipes: List<RecipeDatabaseModel>)

    @Transaction
    suspend fun updateOrInsertRecipes(recipes: List<RecipeDatabaseModel>) {
        deleteAllRecipes()
        insertRecipes(recipes)
    }

    //TODO Search by Name or Ingredients
}
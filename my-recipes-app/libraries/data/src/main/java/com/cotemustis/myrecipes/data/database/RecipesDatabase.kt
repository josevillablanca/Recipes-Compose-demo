package com.cotemustis.myrecipes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cotemustis.myrecipes.data.database.model.RecipeDatabaseModel

@Database(
    entities = [RecipeDatabaseModel::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
internal abstract class RecipesDatabase : RoomDatabase() {
    abstract fun recipesDao(): RecipesDao
}
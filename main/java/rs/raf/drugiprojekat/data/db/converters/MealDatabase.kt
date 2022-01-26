package rs.raf.drugiprojekat.data.db.converters

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import rs.raf.drugiprojekat.data.datasources.MealDao
import rs.raf.drugiprojekat.data.models.entities.MealEntity

@Database(
    entities = [MealEntity::class],
    version = 1,
    exportSchema = false)
@TypeConverters()
abstract class MealDatabase :RoomDatabase(){

    abstract fun getMealDao(): MealDao
}
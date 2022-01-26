package rs.raf.drugiprojekat.data.datasources

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import io.reactivex.Completable
import rs.raf.drugiprojekat.data.models.entities.MealEntity

@Dao
abstract class MealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMeal(mealEntity: MealEntity): Completable

}
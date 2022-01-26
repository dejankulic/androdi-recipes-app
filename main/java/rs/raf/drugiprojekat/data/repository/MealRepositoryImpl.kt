package rs.raf.drugiprojekat.data.repository

import io.reactivex.Completable
import rs.raf.drugiprojekat.data.datasources.MealDao
import rs.raf.drugiprojekat.data.models.entities.MealEntity

class MealRepositoryImpl (private val mealDao: MealDao):MealRepository{
    override fun insert(mealEntity: MealEntity): Completable {
        return mealDao.insertMeal(mealEntity)
    }

}
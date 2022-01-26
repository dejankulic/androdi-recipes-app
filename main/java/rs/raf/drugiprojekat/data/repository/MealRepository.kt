package rs.raf.drugiprojekat.data.repository

import io.reactivex.Completable
import rs.raf.drugiprojekat.data.models.entities.MealEntity

interface MealRepository {
    fun insert(mealEntity: MealEntity): Completable

}
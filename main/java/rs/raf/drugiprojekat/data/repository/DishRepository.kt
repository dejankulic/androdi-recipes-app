package rs.raf.drugiprojekat.data.repository

import io.reactivex.Observable
import rs.raf.drugiprojekat.data.models.domain.CategoryItem
import rs.raf.drugiprojekat.data.models.domain.Dish
import rs.raf.drugiprojekat.data.models.domain.Meal
import java.util.*

interface DishRepository {
    fun getDishes(): Observable<List<Dish>>

    fun getCategory(id: String): Observable<List<CategoryItem>>

    fun getMeal(id: String): Observable<Meal>
}
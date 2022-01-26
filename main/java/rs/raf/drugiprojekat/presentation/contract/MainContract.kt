package rs.raf.drugiprojekat.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.drugiprojekat.data.models.domain.CategoryItem
import rs.raf.drugiprojekat.data.models.domain.Dish
import rs.raf.drugiprojekat.data.models.domain.Meal
import rs.raf.drugiprojekat.data.models.entities.MealEntity

interface MainContract {

    interface ViewModel{
        val dishes: LiveData<List<Dish>>
        val category: LiveData<List<CategoryItem>>
        val meal: LiveData<Meal>

        fun getDishes()
        fun getCategory(id: String)
        fun getMeal(id: String)

        fun getMealItem(): LiveData<Meal>


        //baza
        fun insertMeal(mealEntity: MealEntity)
    }
}
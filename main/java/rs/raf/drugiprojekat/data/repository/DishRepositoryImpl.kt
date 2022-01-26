package rs.raf.drugiprojekat.data.repository

import io.reactivex.Observable
import rs.raf.drugiprojekat.data.datasources.DishDataSource
import rs.raf.drugiprojekat.data.models.domain.CategoryItem
import rs.raf.drugiprojekat.data.models.domain.Dish
import rs.raf.drugiprojekat.data.models.domain.Meal

class DishRepositoryImpl(private val dishDataSource: DishDataSource) : DishRepository {


    override fun getDishes(): Observable<List<Dish>> {
        return dishDataSource
            .getAll()
            .map {
                it.categories.map {
                    Dish(
                        it.imageUrl,
                        it.title
                    )
                }
            }
    }
    override fun getCategory(id: String): Observable<List<CategoryItem>> {
        return dishDataSource
                .getById(id)
                .map{
                    it.recipes.map{
                        CategoryItem(

                            it.image_url,
                            it.social_rank,
                            it._id,
                            it.publisher,
                            it.source_url,
                            it.recipe_id,
                            it.publisher_url,
                            it.title
                        )
                    }
                }
    }

    override fun getMeal(id: String): Observable<Meal> {
        return dishDataSource
            .getSingleMeal(id)
            .map {
                val mealResponse = it.recipe
                    Meal(
                        mealResponse.image_url,
                        mealResponse.social_rank,
                        mealResponse._id,
                        mealResponse.publisher,
                        mealResponse.source_url,
                        mealResponse.recipe_id,
                        mealResponse.publisher_url,
                        mealResponse.title,
                        mealResponse.ingredients
                    )

            }
    }

}
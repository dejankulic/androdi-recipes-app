package rs.raf.drugiprojekat.data.datasources

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import rs.raf.drugiprojekat.data.models.api.CategoriesResponse
import rs.raf.drugiprojekat.data.models.api.DishesResponse
import rs.raf.drugiprojekat.data.models.api.SingleDishResponse

interface DishDataSource {

    @GET("v2/categories")
    fun getAll() : Observable<DishesResponse>

    @GET("search?&page=1")
    fun getById(@Query("q") id: String): Observable<CategoriesResponse>

    @GET("get?")
    fun getSingleMeal(@Query("rId") id: String): Observable<SingleDishResponse>


//    @GET("search?q={id}&page=1")
//    fun getById(@Path("id") id: String): Observable<CategoriesResponse>

}

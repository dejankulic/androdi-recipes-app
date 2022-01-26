package rs.raf.drugiprojekat.data.models.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DishesResponse(
    val categories: List<DishResponse>

)
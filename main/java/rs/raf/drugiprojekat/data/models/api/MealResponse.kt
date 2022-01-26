package rs.raf.drugiprojekat.data.models.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class MealResponse(
    @Json(name = "image_url") val image_url: String,
    @Json(name = "title") val title: String,
    @Json(name = "publisher_url") val publisher_url: String,
    @Json(name = "recipe_id") val recipe_id: String,
    @Json(name = "source_url") val source_url: String,
    @Json(name = "publisher") val publisher: String,
    @Json(name = "_id") val _id: String,
    @Json(name = "social_rank") val social_rank: Float,
    @Json(name = "ingredients") val ingredients: List<String>

)
package rs.raf.drugiprojekat.data.models.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class DishResponse(
    @Json(name = "imageUrl") val imageUrl: String,
    @Json(name = "title") val title: String
)
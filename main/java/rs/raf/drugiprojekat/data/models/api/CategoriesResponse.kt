package rs.raf.drugiprojekat.data.models.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoriesResponse(
        val recipes: List<CategoryResponse>
)
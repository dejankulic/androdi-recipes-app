package rs.raf.drugiprojekat.data.models.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SingleDishResponse(
        val recipe: MealResponse

)
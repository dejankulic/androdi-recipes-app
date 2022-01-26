package rs.raf.drugiprojekat.data.models.domain


data class Meal (

    val image_url: String,
    val social_rank: Float,
    val _id: String,
    val publisher: String,
    val source_url: String,
    val recipe_id: String,
    val publisher_url: String,
    val title: String,
    val ingredients: List<String>
)
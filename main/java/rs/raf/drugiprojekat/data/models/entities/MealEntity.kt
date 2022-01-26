package rs.raf.drugiprojekat.data.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName="meals")
class MealEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var mealName: String,
    var category: String,
    var imageLink: String
//    var ingredients : String,
//    var date: String

)
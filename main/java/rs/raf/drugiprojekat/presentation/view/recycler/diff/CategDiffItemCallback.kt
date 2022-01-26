package rs.raf.drugiprojekat.presentation.view.recycler.diff
import androidx.recyclerview.widget.DiffUtil
import rs.raf.drugiprojekat.data.models.domain.CategoryItem
import rs.raf.drugiprojekat.data.models.domain.Dish

class CategDiffItemCallback  : DiffUtil.ItemCallback<CategoryItem>(){
    override fun areItemsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
        return oldItem.image_url == newItem.image_url
                && oldItem.title == newItem.title
                && oldItem._id == newItem._id
                && oldItem.recipe_id == newItem.recipe_id
    }


}
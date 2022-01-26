package rs.raf.drugiprojekat.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.drugiprojekat.data.models.domain.Dish

class DishDiffItemCallback  : DiffUtil.ItemCallback<Dish>(){
    override fun areItemsTheSame(oldItem: Dish, newItem: Dish): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Dish, newItem: Dish): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
                && oldItem.title == newItem.title
    }


}
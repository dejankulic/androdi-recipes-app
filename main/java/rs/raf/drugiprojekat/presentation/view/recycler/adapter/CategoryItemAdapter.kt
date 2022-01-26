package rs.raf.drugiprojekat.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.drugiprojekat.R
import rs.raf.drugiprojekat.data.models.domain.CategoryItem
import rs.raf.drugiprojekat.data.models.domain.Dish
import rs.raf.drugiprojekat.presentation.view.recycler.diff.CategDiffItemCallback
import rs.raf.drugiprojekat.presentation.view.recycler.diff.DishDiffItemCallback
import rs.raf.drugiprojekat.presentation.view.recycler.viewholder.CategoryItemViewHolder
import rs.raf.drugiprojekat.presentation.view.recycler.viewholder.DishViewHolder

class CategoryItemAdapter(
    categDiffItemCallback: CategDiffItemCallback,
    private val onCategoryItemClicked: (CategoryItem) -> Unit) : ListAdapter<CategoryItem, CategoryItemViewHolder>(categDiffItemCallback) {

    override fun onCreateViewHolder(parent:ViewGroup, viewType: Int): CategoryItemViewHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.layout_meal_list_item, parent,false)
        return CategoryItemViewHolder(containerView){
            val categoryItem = getItem(it)
            onCategoryItemClicked.invoke(categoryItem)
        }
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        val categoryItem = getItem(position)
        holder.bind(categoryItem)
    }

}
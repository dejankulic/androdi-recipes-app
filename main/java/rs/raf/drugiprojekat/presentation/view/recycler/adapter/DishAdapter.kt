package rs.raf.drugiprojekat.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.drugiprojekat.R
import rs.raf.drugiprojekat.data.models.domain.Dish
import rs.raf.drugiprojekat.presentation.view.recycler.diff.DishDiffItemCallback
import rs.raf.drugiprojekat.presentation.view.recycler.viewholder.DishViewHolder

class DishAdapter(
    dishDiffItemCallback: DishDiffItemCallback,
    private val onDishClicked: (Dish) -> Unit) : ListAdapter<Dish, DishViewHolder>(dishDiffItemCallback) {

    override fun onCreateViewHolder(parent:ViewGroup, viewType: Int): DishViewHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.layout_dish_list_item, parent,false)
        return DishViewHolder(containerView){
            val dish = getItem(it)
            onDishClicked.invoke(dish)
        }
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val dish = getItem(position)
        holder.bind(dish)
    }

}
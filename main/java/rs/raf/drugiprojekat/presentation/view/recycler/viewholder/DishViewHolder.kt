package rs.raf.drugiprojekat.presentation.view.recycler.viewholder

import android.app.Activity
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_dish_list_item.*
import rs.raf.drugiprojekat.data.models.domain.Dish
import timber.log.Timber

class DishViewHolder (
    override val containerView: View,
    onItemClicked: (Int) -> Unit) : RecyclerView.ViewHolder(containerView), LayoutContainer
    {

        init {
            // Kada dodamo click listener na containerView to znaci da ce biti registrovan klik
            // na bilo koji deo itema u listi
//        containerView.setOnClickListener {
//            onItemClicked.invoke(adapterPosition)
//        }
            // Kada hocemo da registrujemo klik samo na odredjenoj komponenti u itemu,
            // dodajemo click listener bas na tu komponentu
            containerView.setOnClickListener {
                onItemClicked.invoke(adapterPosition)
            }
        }
        fun bind(dish: Dish) {
            Glide
                    .with(containerView)
                    .load(dish.imageUrl)
                    .into(dishPictureIv)
//            Picasso
//                .get()
//                .load(dish.imageUrl)
//                .into(dishPictureIv)
            titleTv.text = dish.title
        }
    }
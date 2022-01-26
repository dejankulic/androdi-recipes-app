package rs.raf.drugiprojekat.presentation.view.recycler.viewholder


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_dish_list_item.*
import kotlinx.android.synthetic.main.layout_meal_list_item.*
import rs.raf.drugiprojekat.data.models.domain.CategoryItem
import rs.raf.drugiprojekat.data.models.domain.Dish
import timber.log.Timber

class CategoryItemViewHolder (
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
    fun bind(categoryItem: CategoryItem) {
        Glide
            .with(containerView)
            .load(categoryItem.image_url)
            .into(mealPictureIv)
//        Picasso
//            .get()
//            .load(categoryItem.image_url)
//            .into(mealPictureIv)
        imeJela.text = categoryItem.title
        opisJela.text = categoryItem.publisher
        cenaJela.text = categoryItem.social_rank.toString()
    }
}
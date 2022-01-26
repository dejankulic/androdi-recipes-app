package rs.raf.drugiprojekat.presentation.view.activity


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_dish_list_item.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.drugiprojekat.R
import rs.raf.drugiprojekat.data.models.entities.MealEntity
import rs.raf.drugiprojekat.presentation.contract.MainContract
import rs.raf.drugiprojekat.presentation.view.recycler.adapter.CategoryItemAdapter
import rs.raf.drugiprojekat.presentation.view.recycler.adapter.DishAdapter
import rs.raf.drugiprojekat.presentation.view.recycler.diff.CategDiffItemCallback
import rs.raf.drugiprojekat.presentation.view.recycler.diff.DishDiffItemCallback
import rs.raf.drugiprojekat.presentation.viewmodel.MainViewModel
import timber.log.Timber

class MainActivity : AppCompatActivity(R.layout.activity_main) {


    private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>()

    private lateinit var dishAdapter: DishAdapter
    private lateinit var categoryItemAdapter: CategoryItemAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        mainViewModel.insertMeal(
            MealEntity(
                1,"Test","Test","Tets,"//, "TEST", "TEST"
            )
        )
        super.onCreate(savedInstanceState)
        init()

    }
    private fun init(){
        initUi()
        initObservers()
    }
    private fun initUi(){
        initListeners()

        initRecycler()
    }
    private fun initListeners(){

    searchButton.setOnClickListener(){

        if(searchEt.text != null) {
            mainViewModel.getCategory("${searchEt.text}")
        }
        listRv.visibility = View.GONE
        categItemRv.visibility = View.VISIBLE
        backButton.visibility = View.VISIBLE
        linear111.visibility = View.VISIBLE
    }
    saveButton.setOnClickListener(){

            intent = Intent(this, SaveRecepieActivity::class.java)
            intent.putExtra("nazivJela",mealName.text)
            intent.putExtra("spisak",sastojci.text.toString())

            Timber.e("456456${sastojci.text}")
            intent.putExtra("urlSlike",singleMealPicture.drawable.toString())
            startActivityForResult(intent, 1)
        }
        backFromMealButton.setOnClickListener(){

            backFromMealButton.visibility = View.GONE
            singleMealPicture.visibility = View.GONE
            mealName.visibility = View.GONE
            ingredients.visibility = View.GONE
            cenaJednogJela.visibility = View.GONE
            sastojci.visibility = View.GONE
            saveButton.visibility = View.GONE
            scroll.visibility = View.GONE
            linear.visibility = View.GONE

            searchButton.visibility = View.VISIBLE
            searchEt.visibility = View.VISIBLE
            linear111.visibility = View.VISIBLE
            backButton.visibility = View.VISIBLE
            categItemRv.visibility = View.VISIBLE
        }
        backButton.setOnClickListener(){
//            MainVieww.setBackgroundColor(Color.GRAY)

            shimmerLayout1.visibility = View.VISIBLE
            shimmerLayout1.startShimmer()


            listRv.visibility = View.VISIBLE
            categItemRv.visibility = View.GONE

            shimmerLayout1.stopShimmer()
            shimmerLayout1.visibility = View.GONE

            backButton.visibility = View.GONE
            linear111.visibility = View.GONE

        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // If you have multiple activities returning results then you should include unique request codes for each
        if (requestCode == 100) {

            // The result code from the activity started using startActivityForResults
            if (resultCode == Activity.RESULT_OK) {
            }
        }
    }
    private fun showMeal(){
        categItemRv.visibility = View.GONE
        //searchBar.visibility = View.GONE
        searchButton.visibility = View.GONE
        searchEt.visibility = View.GONE

        backFromMealButton.visibility = View.VISIBLE
        singleMealPicture.visibility = View.VISIBLE
        mealName.visibility = View.VISIBLE
        ingredients.visibility = View.VISIBLE
        cenaJednogJela.visibility = View.VISIBLE
        sastojci.visibility = View.VISIBLE
        saveButton.visibility = View.VISIBLE
        scroll.visibility = View.VISIBLE
        linear.visibility = View.VISIBLE
//        MainVieww.setBackgroundColor(Color.WHITE)

        backButton.visibility = View.GONE
        linear111.visibility = View.GONE
    }
    ///lateinit var meal: Meal
    private fun initRecycler(){
        listRv.layoutManager = LinearLayoutManager(this)
        categItemRv.layoutManager = LinearLayoutManager(this)
        dishAdapter = DishAdapter(DishDiffItemCallback()){
            listRv.visibility = View.GONE
            categItemRv.visibility = View.VISIBLE
            backButton.visibility = View.VISIBLE
            linear111.visibility = View.VISIBLE
            Timber.e("${it.title}")


            mainViewModel.getCategory("${it.title}")

        }
        categoryItemAdapter = CategoryItemAdapter(CategDiffItemCallback()){


            mainViewModel.meal.observe(this, Observer {
                Timber.e("!!!!!!!!!!${it.ingredients}")

                val builder = StringBuilder()
                for (details in it.ingredients) {
                    builder.append(details + "\n")
                }

                sastojci.setText(builder.toString())
                //      /      sastojci = it.ingredients
                // Kada dobijemo listu svih korisnika sa servera, hocemo da dohvatimo posebno
                // podatke o prvom zaposlenom
            })

            mainViewModel.getMeal("${it.recipe_id}")
            showMeal()
            val pref = applicationContext.getSharedPreferences("PREF_0", 0)
            val editor = pref.edit()
            editor.putString("trenutnaSlika", it.image_url)
            editor.apply()

            Glide
                .with(this)
                .load(it.image_url)
                .into(singleMealPicture)
            mealName.text = it.title
            ingredients.text = "ingredients"
            cenaJednogJela.text = it.social_rank.toString()

        }
        listRv.adapter = dishAdapter
        categItemRv.adapter = categoryItemAdapter
    }
    private fun initObservers(){
        mainViewModel.dishes.observe(this, Observer {
            Timber.e("Got employees $it")
            // Kada dobijemo listu svih korisnika sa servera, hocemo da dohvatimo posebno
            // podatke o prvom zaposlenom

            shimmerLayout1.visibility = View.VISIBLE
            shimmerLayout1.startShimmer()
            dishAdapter.submitList(it)
            shimmerLayout1.stopShimmer()
            shimmerLayout1.visibility = View.GONE
        })
        mainViewModel.getDishes()


        mainViewModel.category.observe(this, Observer {
            Timber.e("!!!!!!!!!!!!! $it")

            shimmerLayout12.startShimmer()
            shimmerLayout12.visibility = View.VISIBLE

            categoryItemAdapter.submitList(it)


            shimmerLayout12.stopShimmer()
            shimmerLayout12.visibility = View.GONE
        })



    }
}
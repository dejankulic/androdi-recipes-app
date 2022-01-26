package rs.raf.drugiprojekat.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.drugiprojekat.data.models.domain.CategoryItem
import rs.raf.drugiprojekat.data.models.domain.Dish
import rs.raf.drugiprojekat.data.models.domain.Meal
import rs.raf.drugiprojekat.data.models.entities.MealEntity
import rs.raf.drugiprojekat.data.repository.DishRepository
import rs.raf.drugiprojekat.data.repository.MealRepository
import rs.raf.drugiprojekat.presentation.contract.MainContract
import timber.log.Timber

class MainViewModel (
    private val dishRepository: DishRepository,
    private val mealRepository: MealRepository

) : ViewModel(), MainContract.ViewModel {

    override val dishes: MutableLiveData<List<Dish>> = MutableLiveData()
    override var meal: MutableLiveData<Meal> = MutableLiveData()
    override val category: MutableLiveData<List<CategoryItem>> = MutableLiveData()


    //baza

    private val subscriptions = CompositeDisposable()


    override fun getMeal(id: String) {
        val subscription1 = dishRepository
            .getMeal(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    meal.value = it
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription1)
    }

    override fun getCategory(id: String) {
        val subscription1 = dishRepository
                .getCategory(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            category.value = it
                        },
                        {
                            Timber.e(it)
                        }
                )
        subscriptions.add(subscription1)
    }
    override fun getDishes() {
        val subscription = dishRepository
            .getDishes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    dishes.value = it
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }
    fun getCars() :LiveData<List<Dish>>{
        return dishes
    }
    fun getCategoryItem() :LiveData<List<CategoryItem>>{
        return category
    }
    override fun getMealItem() :LiveData<Meal>{
        return meal
    }

    override fun insertMeal(mealEntity: MealEntity) {
        val subscription = mealRepository
            .insert(mealEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("COMPLETE")
                },{
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }


}
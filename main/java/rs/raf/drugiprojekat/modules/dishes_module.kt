package rs.raf.drugiprojekat.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.drugiprojekat.data.datasources.DishDataSource
import rs.raf.drugiprojekat.data.db.converters.MealDatabase
import rs.raf.drugiprojekat.data.repository.DishRepository
import rs.raf.drugiprojekat.data.repository.DishRepositoryImpl
import rs.raf.drugiprojekat.data.repository.MealRepository
import rs.raf.drugiprojekat.data.repository.MealRepositoryImpl
import rs.raf.drugiprojekat.presentation.viewmodel.MainViewModel

val dishesModule = module {

    viewModel { MainViewModel(get(),get()) }

    single<DishRepository> { DishRepositoryImpl(get()) }
    single<DishDataSource> { create(get()) }


    single<MealRepository>{MealRepositoryImpl(get())}
    single{get<MealDatabase>().getMealDao()}

}
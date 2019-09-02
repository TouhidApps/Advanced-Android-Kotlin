package com.touhidapps.retrofitwithkotlin.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.touhidapps.retrofitwithkotlin.adapter.dataSource.FlowerDataSource
import com.touhidapps.retrofitwithkotlin.adapter.dataSource.FlowerDataSourceFactory
import com.touhidapps.retrofitwithkotlin.model.MovieModel
import com.touhidapps.retrofitwithkotlin.myEnum.LoadingState
import com.touhidapps.retrofitwithkotlin.networkService.RetrofitClient
import io.reactivex.disposables.CompositeDisposable

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

//    var movieRepository = MovieRepository(application)
//
//    fun getAllMovies(pageNo: Int) {
//        movieRepository.loadData(pageNo)
//    }


    private val retrofitClient = RetrofitClient.instance()!!

    var dataList: LiveData<PagedList<MovieModel>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 10
    private val flowerDataSourceFactory: FlowerDataSourceFactory

    init {
        flowerDataSourceFactory = FlowerDataSourceFactory(retrofitClient, application, compositeDisposable)
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        dataList = LivePagedListBuilder(flowerDataSourceFactory, config).build()
    }

    fun getState(): LiveData<LoadingState> = Transformations.switchMap<FlowerDataSource, LoadingState>(
        flowerDataSourceFactory.flowerDataSourceLiveData, FlowerDataSource::state
    )

    fun refresh() {
        flowerDataSourceFactory.flowerDataSourceLiveData.value?.invalidate()
    }

    fun retry() {
        flowerDataSourceFactory.flowerDataSourceLiveData.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return dataList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }



}




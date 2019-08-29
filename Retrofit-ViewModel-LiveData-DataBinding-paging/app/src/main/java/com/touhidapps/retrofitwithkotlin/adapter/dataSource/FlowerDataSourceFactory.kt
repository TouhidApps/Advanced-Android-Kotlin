package com.touhidapps.retrofitwithkotlin.adapter.dataSource

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.touhidapps.retrofitwithkotlin.model.MovieModel
import com.touhidapps.retrofitwithkotlin.networkService.RetrofitInterface
import io.reactivex.disposables.CompositeDisposable

class FlowerDataSourceFactory(private val retrofitInterface: RetrofitInterface,
                              private val application: Application,
                              private val compositeDisposable: CompositeDisposable) : DataSource.Factory<Int, MovieModel>() {

    val flowerDataSourceLiveData = MutableLiveData<FlowerDataSource>()

    override fun create(): DataSource<Int, MovieModel> {
        val newsDataSource = FlowerDataSource(retrofitInterface, application, compositeDisposable)
        flowerDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
    
}



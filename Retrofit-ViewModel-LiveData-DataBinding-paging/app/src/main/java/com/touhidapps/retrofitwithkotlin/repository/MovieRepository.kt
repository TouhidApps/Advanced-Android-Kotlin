package com.touhidapps.retrofitwithkotlin.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.touhidapps.retrofitwithkotlin.model.MovieModel
import io.reactivex.disposables.CompositeDisposable

class MovieRepository(var application: Application, private val compositeDisposable: CompositeDisposable) {

    var mutableLiveData = MutableLiveData<List<MovieModel>>()


    // not used because of paging library
    fun loadData(page: Int,
                 callbackInitial: PageKeyedDataSource.LoadInitialCallback<Int, MovieModel>,
                 callbackAfter: PageKeyedDataSource.LoadCallback<Int, MovieModel>
    ) {

        val rBody = HashMap<String, Any>()
        rBody["CatCode"] = "MV"
        rBody["PageTotal"] = page

        println("Page111: $page")



//        RetrofitClient.instance()?.getMovieList(rBody)?.enqueue(object : Callback<List<MovieModel>> {
//
//            override fun onResponse(call: Call<List<MovieModel>>?, response: Response<List<MovieModel>>?) {
//
//                response ?: return
//                val a = response.body() ?: return // return if null
//
//                mutableLiveData.value = a
//
//            }
//
//            override fun onFailure(call: Call<List<MovieModel>>?, t: Throwable?) {
//                Toast.makeText(application,"Data load error.", Toast.LENGTH_LONG).show()
//            }
//
//        })

    } // loadData




}
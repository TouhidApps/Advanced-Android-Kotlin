package com.touhidapps.retrofitwithkotlin.adapter.dataSource

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.touhidapps.retrofitwithkotlin.model.MovieModel
import com.touhidapps.retrofitwithkotlin.myEnum.LoadingState
import com.touhidapps.retrofitwithkotlin.networkService.RetrofitInterface
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers


class FlowerDataSource(private val retrofitInterface: RetrofitInterface, private val application: Application, private val compositeDisposable: CompositeDisposable) : PageKeyedDataSource<Int, MovieModel>() {

    var state: MutableLiveData<LoadingState> = MutableLiveData()
    private var retryCompletable: Completable? = null
//    var movieRepository = MovieRepository(application)


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, MovieModel>) {
        updateState(LoadingState.LOADING)

        val rBody = HashMap<String, Any>()
        rBody["CatCode"] = "MV"
        rBody["PageTotal"] = "1"

        println("Sending0: $rBody")
//        params.requestedLoadSize

        compositeDisposable.add(
            retrofitInterface.getMovieList(rBody).subscribe(
                { response ->
                    updateState(LoadingState.DONE)
                    callback.onResult(response, null, 2)
                },
                {
                    updateState(LoadingState.ERROR)
                    setRetry(Action { loadInitial(params, callback) })
                }
            )
        )

    } // loadInitial

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieModel>) {
        updateState(LoadingState.LOADING)

//        params.key,  // page no
//        params.requestedLoadSize // how much item will load // page size

        val rBody = HashMap<String, Any>()
        rBody["CatCode"] = "MV"
        rBody["PageTotal"] = params.key

        println("Sending1: $rBody")

        compositeDisposable.add(
            retrofitInterface.getMovieList(rBody)
                        .subscribe(
                                { response ->
                                    println("ERRRR 0: ")
                                    updateState(LoadingState.DONE)
                                    callback.onResult(response, params.key + 1)
                                },
                                {
                                    println("ERRRR 2: ")
                                    updateState(LoadingState.ERROR)
                                    setRetry(Action { loadAfter(params, callback) })
                                }
                        )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieModel>) {

    }

    private fun updateState(state: LoadingState) {
        this.state.postValue(state)
    }

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe())
        }
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }

}













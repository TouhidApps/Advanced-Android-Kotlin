package com.touhidapps.retrofitwithkotlin.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.touhidapps.retrofitwithkotlin.model.MovieModel
import com.touhidapps.retrofitwithkotlin.networkService.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(var application: Application) {

    var mutableLiveData = MutableLiveData<List<MovieModel>>()

    fun loadData(page: Int) {

        val rBody = HashMap<String, Any>()
        rBody["CatCode"] = "MV"
        rBody["PageTotal"] = page

        println("Page111: $page")

        RetrofitClient.instance()?.getMovieList(rBody)?.enqueue(object : Callback<List<MovieModel>> {

            override fun onResponse(call: Call<List<MovieModel>>?, response: Response<List<MovieModel>>?) {

                response ?: return
                val a = response.body() ?: return // return if null

                mutableLiveData.value = a


            }

            override fun onFailure(call: Call<List<MovieModel>>?, t: Throwable?) {
                Toast.makeText(application,"Data load error.", Toast.LENGTH_LONG).show()
            }

        })

    } // loadData



}
package com.touhidapps.retrofitwithkotlin.service

import com.touhidapps.retrofitwithkotlin.model.MovieModel
import retrofit2.Call
import retrofit2.http.*

interface RetrofitInterface {

    @POST(MyApiUrl.MOVIE_LIST)
    fun getMovieList(@Body body: HashMap<String, Any>): Call<List<MovieModel>>

}



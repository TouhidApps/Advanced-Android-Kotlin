package com.touhidapps.retrofitwithkotlin.networkService

import com.touhidapps.retrofitwithkotlin.model.MovieModel
import com.touhidapps.retrofitwithkotlin.model.ServiceModel
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitInterface {

    @POST(MyApiUrl.MOVIE_LIST)
    fun getMovieList(@Body body: HashMap<String, Any>): Single<List<MovieModel>>

    @POST(MyApiUrl.SERVICE_DETAIL_API)
    fun getServiceDetail(@Query("id") id: String): Single<ServiceModel>


//    @GET("everything?q=sports&apiKey=aa67d8d98c8e4ad1b4f16dbd5f3be348")
//    fun getNews(@Query("page") page: Int, @Query("pageSize") pageSize: Int): Single<Response>


}



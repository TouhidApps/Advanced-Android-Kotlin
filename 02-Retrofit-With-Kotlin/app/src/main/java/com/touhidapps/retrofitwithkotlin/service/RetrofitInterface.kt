package com.touhidapps.retrofitwithkotlin.service

import com.touhidapps.retrofitwithkotlin.model.MovieModel
import retrofit2.Call
import retrofit2.http.*

interface RetrofitInterface {

    /**
     * Dynamic user data send using Body annotation when using POST or PUT method
     * Also possible to send direct data class without using HashMap
     */
    @POST(MyApiUrl.MOVIE_LIST)
    fun getMovieList(@Body body: HashMap<String, Any>): Call<List<MovieModel>>


    // More example code
    /**
     * Dynamic url path with GET method
     * https://abc.com/movie/1
     */

    @GET("${MyApiUrl.MOVIE_LIST}/{pageNo}")
    fun getMovieList2(@Path("pageNo") pageNo: String): Call<List<MovieModel>>

    /**
     * Dynamic url query with GET method
     * https://abc.com/movie?pageNo=1
     */

    @GET(MyApiUrl.MOVIE_LIST)
    fun getMovieList3(@Query("pageNo") pageNo: String): Call<List<MovieModel>>


    /**
     * Dynamic multiple url query with GET method if want to avoid multiple param separated by ,
     * https://abc.com/movie?pageNo=1&language=BN
     *
     * Below map can be send when calling method
     * val myMap = HashMap<String, Any>()
     * myMap["pageNo"] = "1"
     * myMap["language"] = "BN"
     *
     */

    @GET(MyApiUrl.MOVIE_LIST)
    fun getMovieList4(@QueryMap myQueryMap: Map<String, String>): Call<List<MovieModel>>


    /**
     * Headers annotation to send static headers with request
     */

    @Headers(
        "Accept: application/json",
        "User-Agent: MyDemoApp",
        "Cache-Control: max-age=640000"
    )
    @POST(MyApiUrl.MOVIE_LIST)
    fun getMovieList4(): Call<List<MovieModel>>

    /**
     * Header annotation to send dynamic header with request
     */

    @POST(MyApiUrl.MOVIE_LIST)
    fun getMovieList5(@Header("User-Agent") myAgent: String, @Query("pageNo") pageNo: String): Call<List<MovieModel>>


    /**
     * Header annotation to send dynamic header with request
     *
     * Below map can be send when calling method
     *
     * val myMap = HashMap<String, Any>()
     * myMap["Accept"] = "application/json"
     * myMap["User-Agent"] = "MyDemoApp"
     * myMap["Cache-Control"] = "max-age=640000"
     */

    @POST(MyApiUrl.MOVIE_LIST)
    fun getMovieList6(@HeaderMap headers: Map<String, String>, @Query("pageNo") pageNo: String): Call<List<MovieModel>>


    /**
     * Form Url encoded with Field annotation
     */

    @POST(MyApiUrl.MOVIE_LIST)
    @FormUrlEncoded
    fun getAccessToken(
        @Field("clientId") clientId: String,
        @Field("clientSecret") clientSecret: String
    ): Call<String>


}



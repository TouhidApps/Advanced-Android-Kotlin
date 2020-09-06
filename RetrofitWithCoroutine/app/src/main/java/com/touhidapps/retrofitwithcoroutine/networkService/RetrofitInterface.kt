package com.touhidapps.retrofitwithcoroutine.networkService


import com.touhidapps.retrofitwithcoroutine.model.FlowerModel
import retrofit2.Response
import retrofit2.http.*


interface RetrofitInterface {

    @GET(MyApiUrl.FLOWER_LIST)
    suspend fun getFlowerList(@Query("option") option: Int): Response<List<FlowerModel>>

//    @GET(MyApiUrl.COUNTRY_LIST)
//    fun getDataList(): Single<List<CountryListItem>>

//    @GET(MyApiUrl.USER_EXISTS)
//    fun checkUserExist(
//        @Query("mobile_number") mobileNo: String
//    ): Single<UserExistModel>

//    @Multipart
//    @POST(MyApiUrl.REGISTRATION)
//    fun registration(@Part requestBody: List<MultipartBody.Part>): Single<UserRegisterResponse>

//    @GET(MyApiUrl.OCCUPATION_LIST)
//    fun occupationList(
//        @HeaderMap headers: Map<String, String>
//    ): Single<List<Occupation>>

//    @GET(MyApiUrl.ID_TYPE_LIST)
//    fun getIdTypeList(): Single<List<IDTypeList>>
//
//    @POST(MyApiUrl.DOCUMENT_LIST)
//    fun documentList(
//        @Body body: HashMap<String, Any>
//    ): Single<List<DocumentType>> // DocumentType from user package
//
//    @GET(MyApiUrl.GET_USER_PROFILE + "/{userId}")
//    fun getUserProfile(
//        @HeaderMap headers: Map<String, String>,
//        @Path("userId") id: Int
//    ): Single<UserModel>
//
//    @POST(MyApiUrl.SERVICE_TYPE_LIST)
//    fun getServiceTypeList(
//        @HeaderMap headers: Map<String, String>,
//        @Body body: HashMap<String, Any>
//    ): Single<ServiceTypeListModel>












}














package com.touhidapps.retrofitwithkotlin.model

import com.google.gson.annotations.SerializedName

data class ServiceModel (
    @SerializedName("servicedetails")    val servicedetails : List<Servicedetails> = arrayListOf()
)

data class Servicedetails (
    @SerializedName("TodaysContent" )    val todaysContent   : String,
    @SerializedName("ServiceTitle"  )    val serviceTitle    : String,
    @SerializedName("imageurl"      )    val imageurl        : String,
    @SerializedName("LAST_UPDATE"   )    val lAST_UPDATE     : String
)



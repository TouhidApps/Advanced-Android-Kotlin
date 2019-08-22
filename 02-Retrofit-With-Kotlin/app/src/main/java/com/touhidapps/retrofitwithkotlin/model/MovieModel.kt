package com.touhidapps.retrofitwithkotlin.model

import com.google.gson.annotations.SerializedName


data class MovieModel (

    @SerializedName("ContentCode")          val contentCode         : String,
    @SerializedName("ContentCategoryCode")  val contentCategoryCode : String,
    @SerializedName("ContentTitle")         val contentTitle        : String,
    @SerializedName("BigPreview")           val bigPreview          : String,
    @SerializedName("ContentType")          val contentType         : String,
    @SerializedName("ContentZedCode")       val contentZedCode      : String,
    @SerializedName("Value")                val value               : Int,
    @SerializedName("CommentCount")         val commentCount        : String,
    @SerializedName("Artist")               val artist              : String,
    @SerializedName("imageUrl")             val imageUrl            : String,
    @SerializedName("LiveDate")             val liveDate            : String,
    @SerializedName("TimeStamp")            val timeStamp           : String,
    @SerializedName("totalLike")            val totalLike           : Int,
    @SerializedName("totalView")            val totalView           : Int,
    @SerializedName("PhysicalFileName")     val physicalFileName    : String,
    @SerializedName("duration")             val duration            : String,
    @SerializedName("Info")                 val info                : String,
    @SerializedName("Genre")                val genre               : String,
    @SerializedName("isHd")                 val isHd                : Int,
    @SerializedName("R")                    val r                   : Int

)
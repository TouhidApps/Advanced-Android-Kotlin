package com.touhidapps.retrofitwithcoroutine.networkService

import com.touhidapps.retrofitwithcoroutine.toBase64Decode


object MyApiUrl {

    init {
        System.loadLibrary("native-lib")
    }

    private external fun baseUrlFromJNI() : String

    val BASE_URL          = baseUrlFromJNI().toBase64Decode()

    const val V1 = "api/demo/"

    const val FLOWER_LIST = V1 + "jsondemoapi.php" // ?option=3




}









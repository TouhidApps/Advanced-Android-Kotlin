package com.touhidapps.retrofitwithkotlin.networkService

import android.annotation.TargetApi
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build

object NetworkUtil {

    fun isNetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var isAvailable = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network: Network = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            isAvailable = capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
        }

        return isAvailable
    }

    @TargetApi(Build.VERSION_CODES.N)
    fun observeNet(context: Context, observe: (isAvailable: Boolean) -> Unit) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager?.let {
            it.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    //take action when network connection is gained
                    observe.invoke(true)
                }

                override fun onLost(network: Network?) {
                    //take action when network connection is lost
                    observe.invoke(false)
                }
            })
        }
    }

// in java use
//    NetworkUtil.INSTANCE.observeNet(this, new Function1<Boolean, Unit>() {
//        @Override
//        public Unit invoke(Boolean aBoolean) {
//
//            System.out.println("ddd"+aBoolean);
//
//            return null;
//        }
//    });


}
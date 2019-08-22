package com.touhidapps.workmanager

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(mContext: Context, wParams: WorkerParameters) : Worker(mContext, wParams) {

    companion object {
        const val KEY_SUCCESS = "key_success"
    }

    override fun doWork(): Result {

        val mValue = inputData.getInt(MainActivity.KEY_MY_VALUE, 0)


        for (i in 0..mValue) {
            Thread.sleep(500)
            println("MY_VALUE: $i") // 0,1,2,3,4,5   --> up to 5
        }

        var dataSend = Data.Builder().putString(KEY_SUCCESS, "Task complete").build()

        return Result.success(dataSend)
    }

}
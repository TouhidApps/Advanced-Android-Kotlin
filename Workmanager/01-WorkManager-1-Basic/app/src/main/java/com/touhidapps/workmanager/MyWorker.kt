package com.touhidapps.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(mContext: Context, wParams: WorkerParameters) : Worker(mContext, wParams) {

    override fun doWork(): Result {

        for (i in 0..50) {
            Thread.sleep(1000)
            println("MY_VALUE: $i") // 0,1,2,3,4,5   --> up to 5
        }

        return Result.success()
    }

}
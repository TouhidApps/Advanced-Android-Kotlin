package com.touhidapps.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(mContext: Context, wParams: WorkerParameters) : Worker(mContext, wParams) {

    override fun doWork(): Result {

        for (i in 0..10) {
            Thread.sleep(500)
            println("MY_VALUE from Worker 1: $i") // 0,1,2,3,4,5   --> up to 5
        }

        return Result.success()
    }

}
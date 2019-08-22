package com.touhidapps.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyAnotherWorker(mContext: Context, wParams: WorkerParameters) : Worker(mContext, wParams) {

    override fun doWork(): Result {

        for (i in 11..20) {
            Thread.sleep(500)
            println("MY_VALUE from Worker 2: $i") // 0,1,2,3,4,5   --> up to 5
        }

        return Result.success()

    }

}
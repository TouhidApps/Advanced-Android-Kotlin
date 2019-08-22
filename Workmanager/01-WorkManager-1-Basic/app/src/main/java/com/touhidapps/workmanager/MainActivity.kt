package com.touhidapps.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val oneTimeWorkRequest= OneTimeWorkRequest.Builder(MyWorker::class.java).build() // JAVA in kotlin

        val oneTimeWorkRequest = OneTimeWorkRequestBuilder<MyWorker>().build() // KOTLIN - need java 8

        // Start work manager
        WorkManager.getInstance(this).enqueue(oneTimeWorkRequest)


    }



}

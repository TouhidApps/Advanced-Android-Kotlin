package com.touhidapps.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.work.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * When charger connected status will show SUCCEEDED and will start Worker
         */

        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        val oneTimeWorkRequest = OneTimeWorkRequestBuilder<MyWorker>()
            .setConstraints(constraints)
            .build() // KOTLIN - need java 8

//        val oneTimeWorkRequest = OneTimeWorkRequest.Builder(MyWorker::class.java).setInputData(data).setConstraints(constraints).build() // JAVA in kotlin

        // Start work manager by button click
        findViewById<Button>(R.id.button).setOnClickListener {
            WorkManager.getInstance(this).enqueue(oneTimeWorkRequest)
        }

        /**
         * Live status
         * Get live state of WorkManager (ENQUEUED, RUNNING, SUCCEEDED, FAILED, BLOCKED, CANCELLED)
         */
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(oneTimeWorkRequest.id).observe(this, Observer {
            findViewById<TextView>(R.id.textView).text = it.state.name
        })


    }


}













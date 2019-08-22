package com.touhidapps.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val oneTimeWorkRequest= OneTimeWorkRequest.Builder(MyWorker::class.java).build() // JAVA in kotlin

        val oneTimeWorkRequest = OneTimeWorkRequestBuilder<MyWorker>().build() // KOTLIN - need java 8

        // Get live state of WorkManager (ENQUEUED, RUNNING, SUCCEEDED, FAILED, BLOCKED, CANCELLED)
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(oneTimeWorkRequest.id).observe(this, Observer {
                findViewById<TextView>(R.id.textView).text = it.state.name
        })


        findViewById<Button>(R.id.button).setOnClickListener {
            // Start work manager
            WorkManager.getInstance(this).enqueue(oneTimeWorkRequest)
        }


    }


}

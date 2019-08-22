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
         *
         * Execute one after another
         *
         * WorkContinuation continuation = workManager.beginWith(A);
         * continuation.then(B).then(D, E).enqueue();  // A is implicitly enqueued here
         * continuation.then(C).enqueue();
         */

        val oneTimeWorkRequest = OneTimeWorkRequestBuilder<MyWorker>()
            .build()

        val oneTimeWorkRequest2 = OneTimeWorkRequestBuilder<MyAnotherWorker>()
            .build()

        // Start work manager by button click
        findViewById<Button>(R.id.button).setOnClickListener {
//            WorkManager.getInstance(this).enqueue(oneTimeWorkRequest)
            WorkManager.getInstance(this).beginWith(oneTimeWorkRequest).then(oneTimeWorkRequest2).enqueue()
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













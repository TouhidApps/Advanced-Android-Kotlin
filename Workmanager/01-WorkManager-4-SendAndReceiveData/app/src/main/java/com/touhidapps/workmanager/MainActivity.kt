package com.touhidapps.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.*


class MainActivity : AppCompatActivity() {

    companion object {
        const val KEY_MY_VALUE = "key_my_value"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Constraint with data
         * When charger connected status will show SUCCEEDED
         */

        val data = Data.Builder()
            .putInt(KEY_MY_VALUE, 20)
            .build()

        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        val oneTimeWorkRequest = OneTimeWorkRequestBuilder<MyWorker>()
            .setInputData(data)
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

            if (it.state.isFinished) {
                val d = it.outputData
                val myMessage = d.getString(MyWorker.KEY_SUCCESS)
                Toast.makeText(this, myMessage, Toast.LENGTH_LONG).show()
            }

        })


    }


}













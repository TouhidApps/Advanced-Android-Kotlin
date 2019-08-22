package com.touhidapps.retrofitwithkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.touhidapps.retrofitwithkotlin.R
import com.touhidapps.retrofitwithkotlin.model.MovieModel
import com.touhidapps.retrofitwithkotlin.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData(1)



    } // onCreate



    private fun loadData(page: Int) {

        val rBody = HashMap<String, Any>()
        rBody["CatCode"] = "MV"
        rBody["PageTotal"] = page

        RetrofitClient.instance()?.getMovieList(rBody)?.enqueue(object : Callback<List<MovieModel>> {

            override fun onResponse(call: Call<List<MovieModel>>?, response: Response<List<MovieModel>>?) {

                response ?: return
                val a = response.body() ?: return // return if null

                /**
                 * Elvis operator ?: is a binary operator that returns its first operand if that operand is true,
                 * and otherwise evaluates and returns its second operand. It is a variant of the ternary conditional
                 * operator, ? :, found in those languages (and many others): the Elvis operator is the ternary
                 * operator with its second operand omitted.
                 */

                for ((index, value) in a.withIndex()) {
                    println("$index: ${value.contentTitle}")
                }

            }

            override fun onFailure(call: Call<List<MovieModel>>?, t: Throwable?) {
                   Toast.makeText(applicationContext,"Data load error.",Toast.LENGTH_LONG).show()
            }

        })

    } // loadData


}

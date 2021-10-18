package com.example.animals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.animals.Cats.CatResponce
import com.example.animals.Cats.ICatApi
import com.example.animals.Dogs.DogResponce
import com.example.animals.Dogs.IDogApi
import com.example.animals.Ducks.DuckResponce
import com.example.animals.Ducks.IDuckApi
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val DUCK_URL = "https://random-d.uk/api/v2/"
const val DOG_URL = "https://dog.ceo/api/"
const val CAT_URL = "https://aws.random.cat/"
const val ERROR_MSG = "Something is wrong, check your internet connection"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imgView: ImageView = findViewById(R.id.imgView)
        val duckButton: Button = findViewById(R.id.duckButton)
        val dogButton: Button = findViewById(R.id.dogButton)
        val catButton: Button = findViewById(R.id.catButton)

        duckButton.setOnClickListener {
            val retrofitBuilder = retrofitRequest<IDuckApi>(DUCK_URL)
            val retrofitData = retrofitBuilder.getData()

            retrofitData.enqueue(object : Callback<DuckResponce?> {
                override fun onResponse( call: Call<DuckResponce?>,
                    response: Response<DuckResponce?>
                ) {
                    val responceBody = response.body()!!
                    Picasso.get().load(responceBody.imgUrl).into(imgView);
                }

                override fun onFailure(call: Call<DuckResponce?>, t: Throwable) {
                    Toast.makeText(applicationContext, ERROR_MSG, Toast.LENGTH_SHORT).show()
                }
            })
        }

        dogButton.setOnClickListener {
            val retrofitBuilder = retrofitRequest<IDogApi>(DOG_URL)
            val retrofitData = retrofitBuilder.getData()

            retrofitData.enqueue(object : Callback<DogResponce?> {
                override fun onResponse( call: Call<DogResponce?>,
                                         response: Response<DogResponce?>
                ) {
                    val responceBody = response.body()!!
                    Picasso.get().load(responceBody.imgUrl).into(imgView);
                }

                override fun onFailure(call: Call<DogResponce?>, t: Throwable) {
                    Toast.makeText(applicationContext, ERROR_MSG, Toast.LENGTH_SHORT).show()
                }
            })
        }

        catButton.setOnClickListener {
            val retrofitBuilder = retrofitRequest<ICatApi>(CAT_URL)
            val retrofitData = retrofitBuilder.getData()

            retrofitData.enqueue(object : Callback<CatResponce?> {
                override fun onResponse( call: Call<CatResponce?>,
                                         response: Response<CatResponce?>
                ) {
                    val responceBody = response.body()!!
                    Picasso.get().load(responceBody.imgUrl).into(imgView);
                }

                override fun onFailure(call: Call<CatResponce?>, t: Throwable) {
                    Toast.makeText(applicationContext, ERROR_MSG, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private inline fun<reified T> retrofitRequest(url: String): T {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .build()
            .create(T::class.java)
    }

}
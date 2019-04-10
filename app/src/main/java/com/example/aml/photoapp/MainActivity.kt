package com.example.aml.photoapp

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import com.example.aml.photoapp.api.PhotoReteriver
import com.example.aml.photoapp.models.Photo
import com.example.aml.photoapp.models.PhotoList

import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), View.OnClickListener {


    var photos :List<Photo>? = null
    var mainAdapter : MainAdapter? =null
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val reteriver = PhotoReteriver()
        val callback = object : Callback<PhotoList>{
            override fun onFailure(call: Call<PhotoList>, t: Throwable) {
                Log.e("Main Activity", "Problems calling API" , t)

            }

            override fun onResponse(call: Call<PhotoList>, response: Response<PhotoList>) {

                response?.isSuccessful.let {

                    this@MainActivity.photos = response?.body()?.hits
                    mainAdapter = MainAdapter(this@MainActivity.photos!!,this@MainActivity)
                    recyclerView.adapter = mainAdapter
                    Log.e("response", response.isSuccessful.toString())

                }
            }

        }

reteriver.getPhotos(callback)
    }

    override fun onClick(v: View?) {

        val intent : Intent = Intent(this , DetailsActivity::class.java)

        val holder = v?.tag as MainAdapter.PhotoViewHoldeer
        intent.putExtra(DetailsActivity.PHOTO ,
           mainAdapter?.getPhoto( holder.adapterPosition))
        startActivity(intent)
    }
}

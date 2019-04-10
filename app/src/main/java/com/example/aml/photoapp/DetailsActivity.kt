package com.example.aml.photoapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.aml.photoapp.models.Photo
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val toolbar :Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val imageView:ImageView = findViewById(R.id.imageView)
        val photo  = intent.getSerializableExtra(PHOTO) as Photo?

        photo?.webFormatURL.let {
            Glide.with(this)
                .load(photo?.previewURL)
                .into(imageView)
        }
        imageView.setOnClickListener{
            finish()
        }
    }

    companion object {
        val PHOTO = "PHOTO"
    }
}

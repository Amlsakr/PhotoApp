package com.example.aml.photoapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.aml.photoapp.models.Photo

class MainAdapter (var photos:List<Photo>,
                   var clicListener : View.OnClickListener) :
    RecyclerView.Adapter<MainAdapter.PhotoViewHoldeer>() {
    override fun getItemCount(): Int {
        return  photos.size

    }

    override fun onBindViewHolder(holder: PhotoViewHoldeer, position: Int) {
        val photo =photos[position]
        holder?.tags?.text = photo.tags
        holder?.likes?.text = photo.likes.toString()
        holder?.favorites?.text = photo.favorites.toString()
        if (photo.previewURL.isNotEmpty()){
            Glide.with(holder.tags?.context)
                .load(photo.webFormatURL)
                .into(holder.photoItem)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): PhotoViewHoldeer {

        return PhotoViewHoldeer(LayoutInflater.from(parent?.context)
            .inflate(R.layout.photo_item , parent ,false))
    }

    fun  getPhoto (adapterPosition : Int) : Photo
    {
        return photos[adapterPosition]
    }
    inner class PhotoViewHoldeer(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var tags :TextView
        var likes :TextView
        var favorites :TextView
        var photoItem :ImageView

        init {

            if (clicListener != null) {

                itemView.setOnClickListener(clicListener)
            }
            itemView.tag = this

            tags = itemView.findViewById(R.id.tags)
            likes = itemView.findViewById(R.id.likes)
            favorites = itemView.findViewById(R.id.favorites)
            photoItem = itemView.findViewById(R.id.photo_item)
        }

    }
}
package com.aurea.batikcam.ui.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.aurea.batikcam.R
import com.aurea.batikcam.data.model.Gallery
import com.bumptech.glide.Glide

class GalleryAdapter(private var itemList: List<Gallery>) :
    RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    // ViewHolder class that binds the views
    class ViewHolder(galleryView: View) : RecyclerView.ViewHolder(galleryView) {
        val imageView: ImageView = galleryView.findViewById(R.id.gallery_item_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate the item layout (not the fragment layout)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gallery_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]

        // Bind the image using Glide
        Glide.with(holder.itemView.context)
            .load("file:///android_asset/gallery/"+item.image)
            .placeholder(R.drawable.image_loading)  // Placeholder image while loading
            .error(R.drawable.no_image)  // Error image if loading fails
            .into(holder.imageView)

    }

    // Method to update data in the adapter
    fun updateData(newList: List<Gallery>) {
        itemList = newList
        notifyDataSetChanged()
    }
}

package com.aurea.batikcam.ui.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aurea.batikcam.R
import com.aurea.batikcam.data.model.Category
import com.bumptech.glide.Glide

class CategoryAdapter(private var itemList: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    // ViewHolder class that binds the views
    class ViewHolder(categoryView: View) : RecyclerView.ViewHolder(categoryView) {
        val imageView: ImageView = categoryView.findViewById(R.id.category_item_image)
        val title: TextView = categoryView.findViewById(R.id.category_item_title)
        val subtitle: TextView = categoryView.findViewById(R.id.category_item_subtitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate the item layout (not the fragment layout)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]

        // Bind the image using Glide
        Glide.with(holder.itemView.context)
            .load("file:///android_asset/location/"+item.image)
            .placeholder(R.drawable.image_loading)  // Placeholder image while loading
            .error(R.drawable.no_image)  // Error image if loading fails
            .into(holder.imageView)

        holder.title.text = item.title
        holder.subtitle.text = item.description

    }

    // Method to update data in the adapter
    fun updateData(newList: List<Category>) {
        itemList = newList
        notifyDataSetChanged()
    }
}
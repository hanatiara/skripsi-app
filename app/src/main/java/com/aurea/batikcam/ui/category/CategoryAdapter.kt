package com.aurea.batikcam.ui.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.aurea.batikcam.R
import com.aurea.batikcam.data.model.Category
import com.bumptech.glide.Glide

class CategoryAdapter(
    private var itemList: List<Category>,
    private val fragment: Fragment) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>()
{

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

        holder.itemView.setOnClickListener {
            println("Location Clicked: ${item.title}")
            val detailsFragment = CategoryDetailFragment()

            val bundle = Bundle()
            bundle.putString("idCategory", item.idCategory)
            detailsFragment.arguments = bundle

            fragment.parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_activity_main, detailsFragment)
                .addToBackStack(null) // Add to backstack to allow "back" navigation
                .commit()
        }
    }

    // Method to update data in the adapter
    fun updateData(newList: List<Category>) {
        itemList = newList
        notifyDataSetChanged()
    }
}
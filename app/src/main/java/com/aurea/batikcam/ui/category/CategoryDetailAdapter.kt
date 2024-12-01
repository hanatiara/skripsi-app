package com.aurea.batikcam.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.aurea.batikcam.R
import com.aurea.batikcam.data.model.Batik
import com.aurea.batikcam.data.model.Category
import com.bumptech.glide.Glide

class CategoryDetailAdapter(
    private var itemList: List<Category>,
    private var batikList: List<Batik>,
    private val fragment: Fragment
) : RecyclerView.Adapter<CategoryDetailAdapter.ViewHolder>() {
    class ViewHolder(categoryDetailView: View) : RecyclerView.ViewHolder(categoryDetailView) {
        val imageView: ImageView = categoryDetailView.findViewById(R.id.location_item_image)
        val title: TextView = categoryDetailView.findViewById(R.id.location_item_title)
        val subtitle: TextView = categoryDetailView.findViewById(R.id.location_item_subtitle)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryDetailAdapter.ViewHolder {
        // Inflate the item layout (not the fragment layout)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.location_item, parent, false)
        return CategoryDetailAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return batikList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = batikList[position]

        // Bind the image using Glide
        Glide.with(holder.itemView.context)
            .load("file:///android_asset/batik/"+item.image)
            .placeholder(R.drawable.image_loading)
            .error(R.drawable.no_image)
            .into(holder.imageView)

        holder.title.text = item.name
        holder.subtitle.text = item.description

        holder.itemView.setOnClickListener {
            println("Location Clicked: ${item.idBatik}")
            val batikFragment = BatikFragment()

            val bundle = Bundle()
            bundle.putString("idBatik", item.idBatik)
            batikFragment.arguments = bundle

            fragment.parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_activity_main, batikFragment)
                .addToBackStack(null) // Add to backstack to allow "back" navigation
                .commit()
        }


    }
    fun updateData(newList: List<Category>, newBatik: List<Batik>) {
        batikList = newBatik
        itemList = newList
        notifyDataSetChanged()
    }
}
package com.aurea.batikcam.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aurea.batikcam.R
import com.aurea.batikcam.databinding.FragmentCategoryDetailBinding
import com.bumptech.glide.Glide


class CategoryDetailFragment : Fragment() {

    private var _binding: FragmentCategoryDetailBinding? = null
    private lateinit var viewModel: CategoryViewModel
    private lateinit var adapter: CategoryDetailAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryDetailBinding.inflate(inflater, container, false)


        // Set up the RecyclerView LayoutManager
        val recyclerView = binding.categoryDetailContent
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Set up the adapter
        adapter = CategoryDetailAdapter(emptyList(), emptyList(), this)
        recyclerView.adapter = adapter

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val imageView : ImageView = binding.categoryDetailImage
        val title : TextView = binding.categoryDetailTitle
        val subtitle : TextView = binding.categoryDetailSubtitle
        val idCategory = arguments?.getString("idCategory")
        println(idCategory)

        viewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)


        // Observe LiveData for categoryList
        viewModel.categoryList.observe(viewLifecycleOwner, Observer { categoryList ->
            // Now observe the batikList based on the selected category
            if (idCategory   != null) {
                viewModel.getBatikListByCategoryId(idCategory).observe(viewLifecycleOwner, Observer { batikList ->
                    // Update the adapter with new data
                    adapter.updateData(categoryList, batikList)
                })

            }

        })

        if (idCategory != null) {
            viewModel.getCategoryById(idCategory).observe(viewLifecycleOwner, Observer { category ->
                if (category != null) {
                    print(category.image)
                    Glide.with(this)
                        .load("file:///android_asset/location/"+category.image)
                        .placeholder(R.drawable.image_loading)  // Placeholder image while loading
                        .error(R.drawable.no_image)  // Error image if loading fails
                        .into(imageView)

                    title.text = category.title
                    subtitle.text = category.description
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null  // Clean up binding to avoid memory leaks
    }
}
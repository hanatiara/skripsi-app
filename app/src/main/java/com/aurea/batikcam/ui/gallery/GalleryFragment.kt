package com.aurea.batikcam.ui.gallery

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aurea.batikcam.R
import com.aurea.batikcam.data.model.Gallery
import com.aurea.batikcam.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private lateinit var viewModel: GalleryViewModel
    private lateinit var adapter: GalleryAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)

        // Set up the RecyclerView LayoutManager
        val recyclerView = binding.galleryContent
        recyclerView.layoutManager = GridLayoutManager(context, 3)

        // Set up the adapter
        adapter = GalleryAdapter(emptyList())
        recyclerView.adapter = adapter

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)

        // Observe LiveData from ViewModel and update the adapter
        viewModel.galleryList.observe(viewLifecycleOwner, Observer { galleryList ->
            adapter.updateData(galleryList)  // Update the adapter with new data
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null  // Clean up binding to avoid memory leaks
    }
}

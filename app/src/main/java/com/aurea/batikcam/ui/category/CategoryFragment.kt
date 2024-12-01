package com.aurea.batikcam.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.aurea.batikcam.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private lateinit var viewModel: CategoryViewModel
    private lateinit var adapter: CategoryAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)

        // Set up the RecyclerView LayoutManager
        val recyclerView = binding.categoryContent
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Set up the adapter
        adapter = CategoryAdapter(emptyList(),this)
        recyclerView.adapter = adapter

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)

        // Observe LiveData from ViewModel and update the adapter
        viewModel.categoryList.observe(viewLifecycleOwner, Observer { categoryList ->
            adapter.updateData(categoryList)  // Update the adapter with new data
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null  // Clean up binding to avoid memory leaks
    }
}
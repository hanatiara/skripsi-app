package com.aurea.batikcam.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aurea.batikcam.ui.category.CategoryFragment
import com.aurea.batikcam.ui.gallery.GalleryFragment
import com.aurea.batikcam.R
import com.aurea.batikcam.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var imageView :ImageView
    private var context = getContext()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.text.observe(viewLifecycleOwner) {


        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonGallery.setOnClickListener {
            println("Button gallery clicked!")
            val galleryFragment = GalleryFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_activity_main, galleryFragment)
                .addToBackStack(null)
                .commit()
        }

        binding.buttonCategory.setOnClickListener {
            println("Button category clicked!")
            val historyFragment = CategoryFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_activity_main, historyFragment)
                .addToBackStack(null)
                .commit()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
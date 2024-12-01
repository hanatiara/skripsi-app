package com.aurea.batikcam.ui.gallery

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aurea.batikcam.R
import com.aurea.batikcam.databinding.FragmentCategoryDetailBinding
import com.aurea.batikcam.databinding.FragmentGalleryBinding
import com.aurea.batikcam.databinding.FragmentGalleryDetailBinding
import com.aurea.batikcam.ui.category.CategoryDetailFragment
import com.aurea.batikcam.ui.category.CategoryViewModel
import java.io.IOException

class GalleryDetailFragment : Fragment() {
    private var _binding : FragmentGalleryDetailBinding? = null
    private lateinit var viewModel: GalleryViewModel

//    private lateinit var adapter: GalleryDetailAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryDetailBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageView: ImageView = binding.galleryView
        val idGallery = arguments?.getString("idGallery")
        println(idGallery)

        viewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)

        if (idGallery != null) {
            viewModel.getGalleryById(idGallery).observe(viewLifecycleOwner, Observer { gallery ->
                try {
                    // Load the image from assets as a Drawable
                    if (gallery != null) {
                        val inputStream = requireContext().assets.open("gallery/"+gallery.image)
                        val drawable = Drawable.createFromStream(inputStream, null)

                        // Set the image to ZoomageView
                        imageView.setImageDrawable(drawable)
                    }

                } catch (e: IOException) {
                    e.printStackTrace()
                }
            })
        }
    }

    private fun zoomImageFromThumb(thumbView: View, imageResId: Int) {
    }

}
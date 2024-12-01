package com.aurea.batikcam.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aurea.batikcam.R
import com.aurea.batikcam.databinding.FragmentBatikBinding
import com.aurea.batikcam.databinding.FragmentCategoryDetailBinding
import com.bumptech.glide.Glide

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BatikFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BatikFragment : Fragment() {


    private var _binding: FragmentBatikBinding? = null
    private lateinit var viewModel: CategoryViewModel
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBatikBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        val title : TextView = binding.batikDetailTitle
        val description : TextView = binding.batikDescription
        val imageView : ImageView = binding.batikDetailImage
        val idBatik = arguments?.getString("idBatik")
        println(idBatik)

        if (idBatik != null) {
            viewModel.getBatikListById(idBatik).observe(viewLifecycleOwner, Observer { batik ->
                if (batik != null) {
                    Glide.with(this)
                        .load("file:///android_asset/batik/"+batik.image)
                        .placeholder(R.drawable.image_loading)  // Placeholder image while loading
                        .error(R.drawable.no_image)  // Error image if loading fails
                        .into(imageView)
                    title.text = batik.name
                    description.text = batik.description
                }
            })
        }

    }
}
package com.example.test_task_nwcode.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.test_task_nwcode.R
import com.example.test_task_nwcode.databinding.FragmentCategoriesBinding
import com.example.test_task_nwcode.databinding.FragmentImageListBinding
import com.example.test_task_nwcode.model.Hit
import com.example.test_task_nwcode.model.PixabayResponse
import com.example.test_task_nwcode.repository.RetrofitClient
import com.example.test_task_nwcode.ui.Adapters.ImageAdapter
import com.example.test_task_nwcode.viewmodel.PixabayViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ImageListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ImageListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentImageListBinding? = null
    private val binding get() = _binding
    private lateinit var imageAdapter: ImageAdapter

    private lateinit var viewModel: PixabayViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentImageListBinding.inflate(inflater, container, false)


        imageAdapter = ImageAdapter()
        binding?.recyclerView?.apply {
            layoutManager = GridLayoutManager(activity, 3)
            adapter = imageAdapter


        }

        viewModel = ViewModelProvider(this)[PixabayViewModel::class.java]
        //viewModel.getPixabayResponse("cat")
        arguments?.getString("category")?.let { viewModel.getPixabayResponse(it) }
        viewModel.observeMovieLiveData().observe(viewLifecycleOwner, Observer { imageList ->
            imageAdapter.setImageList(imageList)
            binding?.progressBarResponse?.visibility = View.INVISIBLE
        })




        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ImageListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ImageListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
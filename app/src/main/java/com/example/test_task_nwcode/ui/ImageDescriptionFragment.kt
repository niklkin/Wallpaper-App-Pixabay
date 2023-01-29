package com.example.test_task_nwcode.ui

import android.app.Application
import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Website.URL

import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.drawToBitmap
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.test_task_nwcode.R
import com.example.test_task_nwcode.databinding.FragmentImageDescriptionBinding
import com.example.test_task_nwcode.databinding.FragmentImageListBinding
import com.google.gson.internal.bind.TypeAdapters.URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream
import java.net.URL
import kotlin.concurrent.thread

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ImageDescriptionFragment : Fragment() {
    // TODO: Rename and change types of parameters


    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentImageDescriptionBinding? = null
    private val binding get() = _binding

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
        _binding = FragmentImageDescriptionBinding.inflate(inflater, container, false)

        Glide.with(this)
            .load(arguments?.getString("url"))
            .centerCrop()
            .into(binding?.imageViewDesc!!)

        Glide.with(this)
            .asBitmap()
            .load(arguments?.getString("url"))
            .centerCrop()
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    binding?.imageViewDesc!!.setImageBitmap(resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) {

                }
            })



        binding?.button?.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val bitmapDrawable = binding?.imageViewDesc!!.drawable
                WallpaperManager.getInstance(requireContext()).setBitmap(bitmapDrawable.toBitmap())
            }
        }

        //Inflate the layout for this fragment

        return binding?.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ImageDescriptionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
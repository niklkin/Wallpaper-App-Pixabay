package com.example.test_task_nwcode.ui.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test_task_nwcode.databinding.CardViewItemBinding
import com.example.test_task_nwcode.databinding.FragmentImageListBinding
import com.example.test_task_nwcode.model.Hit

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    private var imageList = ArrayList<Hit>()
    fun setImageList(imageList: List<Hit>) {
        this.imageList = imageList as ArrayList<Hit>
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: CardViewItemBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CardViewItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(imageList[position].largeImageURL)
            .override(500,500)
            .centerCrop()
            .into(holder.binding.imageButton)
        Log.d("View holder", imageList[position].largeImageURL)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}
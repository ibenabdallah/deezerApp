package com.smartdevservice.deezerapp.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.smartdevservice.deezerapp.R
import com.smartdevservice.deezerapp.common.onClick
import com.smartdevservice.deezerapp.databinding.ItemAlbumBinding
import com.smartdevservice.deezerapp.databinding.ItemTrackBinding
import com.smartdevservice.deezerapp.ui.ListListener
import com.smartdevservice.deezerapp.utils.EnumDensity
import com.smartdevservice.deezerapp.utils.Utils.convertDpToPixel
import com.smartdevservice.domain.model.Album
import com.smartdevservice.domain.model.Track

class TracksAdapter(private var list: List<Track>) : RecyclerView.Adapter<TracksAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemTrackBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)

    }

    fun setList(list: List<Track>){
        this.list = list
        notifyDataSetChanged()
    }

    inner class MyViewHolder(val binding: ItemTrackBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(track: Track) {

binding.tvTitre.text = track.title
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }
}
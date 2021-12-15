package com.smartdevservice.deezerapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.smartdevservice.deezerapp.R
import com.smartdevservice.deezerapp.common.onClick
import com.smartdevservice.deezerapp.databinding.ItemAlbumBinding
import com.smartdevservice.deezerapp.ui.ListListener
import com.smartdevservice.deezerapp.utils.EnumDensity
import com.smartdevservice.deezerapp.utils.Utils.convertDpToPixel
import com.smartdevservice.domain.model.Album

class AlbumAdapter(
    private var list: List<Album>,
    val enumDensity: EnumDensity,
    private val listener: ListListener
) : RecyclerView.Adapter<AlbumAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
        holder.binding.root.onClick {
            listener.onClickItem(item)
        }
    }

    fun setList(list: List<Album>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class MyViewHolder(val binding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(album: Album) {
            val urlCover = getUrlCover(album, enumDensity)
            Glide.with(binding.root.context)
                .load(urlCover)
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.drawable.ic_launcher_background)
                .apply(
                    RequestOptions().transform(
                        RoundedCorners(
                            convertDpToPixel(
                                8,
                                binding.root.context
                            )
                        )
                    )
                )
                .into(binding.ivPicture)
        }

        private fun getUrlCover(album: Album, enumDensity: EnumDensity): String {
            return when (enumDensity) {
                EnumDensity.DENSITY_MDPI -> album.cover_small
                EnumDensity.DENSITY_HDPI -> album.cover
                EnumDensity.DENSITY_XHDPI -> album.cover_medium
                EnumDensity.DENSITY_XXHDPI -> album.cover_big
                EnumDensity.DENSITY_XXXHDPI -> album.cover_xl
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
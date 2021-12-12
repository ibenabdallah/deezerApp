package com.smartdevservice.deezerapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.smartdevservice.deezerapp.R
import com.smartdevservice.deezerapp.base.BaseFragment
import com.smartdevservice.deezerapp.databinding.FragmentDetailsAlbumBinding
import com.smartdevservice.deezerapp.utils.EnumDensity
import com.smartdevservice.deezerapp.utils.Utils
import com.smartdevservice.deezerapp.utils.Utils.KEY_ALBUM
import com.smartdevservice.domain.model.Album

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailsAlbumFragment : BaseFragment() {

    private lateinit var _binding: FragmentDetailsAlbumBinding

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding

    private var album: Album? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            album = it.getParcelable(KEY_ALBUM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailsAlbumBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvTitle.text = album?.title
        binding.tvRealise.text = album?.release_date
        binding.tvArtist.text = album?.artist?.name
        binding.tvAvailable.text = if(album?.available == true) "Oui" else "Non"
        binding.tvTracks.text = album?.nb_tracks.toString()

        val enumDensity = Utils.getEnumDensityOfDevice(binding.root.context)
        val urlCover = getUrlCover(album, enumDensity)
        Glide.with(binding.root.context)
            .load(urlCover)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.ivCover)
    }

    private fun getUrlCover(album: Album?, enumDensity: EnumDensity): String? {
        return when (enumDensity) {
            EnumDensity.DENSITY_MDPI -> album?.cover_small
            EnumDensity.DENSITY_HDPI -> album?.cover
            EnumDensity.DENSITY_XHDPI -> album?.cover_medium
            EnumDensity.DENSITY_XXHDPI -> album?.cover_big
            EnumDensity.DENSITY_XXXHDPI -> album?.cover_xl
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
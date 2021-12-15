package com.smartdevservice.deezerapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.smartdevservice.data.di.EXTEND_URL
import com.smartdevservice.deezerapp.R
import com.smartdevservice.deezerapp.base.*
import com.smartdevservice.deezerapp.common.subscribe
import com.smartdevservice.deezerapp.databinding.FragmentDetailsAlbumBinding
import com.smartdevservice.deezerapp.ui.AlbumViewModel
import com.smartdevservice.deezerapp.utils.EnumDensity
import com.smartdevservice.deezerapp.utils.Utils
import com.smartdevservice.deezerapp.utils.Utils.KEY_ALBUM
import com.smartdevservice.domain.model.Album
import com.smartdevservice.domain.model.Track
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailsAlbumFragment : BaseFragment() {

    private lateinit var _binding: FragmentDetailsAlbumBinding
    private val albumViewModel: AlbumViewModel by sharedViewModel()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding

    private lateinit var tracksAdapter : TracksAdapter
    private var album: Album? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            album = it.getParcelable(KEY_ALBUM)
        }

        tracksAdapter = TracksAdapter(arrayListOf())

        albumViewModel.detailsState.subscribe(this, ::handleDetailsAlbumState)
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
        binding.tvAvailable.text = getString(if(album?.available == true) R.string.album_available_yes else R.string.album_available_no)
        binding.tvTracks.text = album?.nb_tracks.toString()

        val enumDensity = Utils.getEnumDensityOfDevice(binding.root.context)
        val urlCover = getUrlCover(album, enumDensity)
        Glide.with(binding.root.context)
            .load(urlCover)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.ivCover)

        binding.rvTracks.apply {
            adapter = tracksAdapter
        }

        val url = album?.tracklist?.substringAfter(EXTEND_URL)
        if(url != null) {
            Timber.i("url = $url")
            albumViewModel.loadingDetailsAlbum(url)
        }
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

    private fun handleDetailsAlbumState(viewState: ViewState<ArrayList<Track>>) {
        when (viewState) {
            is FailureState -> {
                Timber.i("FailureState")
                //TODO on peut ajouter un traitement pour dit à l'utilisateur qu'il y'a un probeleme
                // Comme affichage d'une Toast ou Snackbar par exemple...
            }
            is LoadingState -> Timber.i("LoadingState")
            is NoNetworkState -> {
                Timber.i("NoNetworkState")
                //TODO on peut ajouter un traitement pour dit à l'utilisateur qu'il n'a pas de connexion
                // Comme affichage d'une Toast ou Snackbar par exemple...
            }
            is SuccessState -> {
                Timber.i("SuccessState, data size = ${viewState.data?.size}")
                tracksAdapter.setList(viewState.data?.toList() ?: arrayListOf())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
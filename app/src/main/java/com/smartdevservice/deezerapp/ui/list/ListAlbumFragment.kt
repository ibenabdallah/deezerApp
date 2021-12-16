package com.smartdevservice.deezerapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.OrientationHelper
import com.smartdevservice.deezerapp.R
import com.smartdevservice.deezerapp.base.*
import com.smartdevservice.deezerapp.common.search
import com.smartdevservice.deezerapp.common.sortedAlbums
import com.smartdevservice.deezerapp.common.subscribe
import com.smartdevservice.deezerapp.databinding.FragmentListAlbumBinding
import com.smartdevservice.deezerapp.ui.AlbumViewModel
import com.smartdevservice.deezerapp.ui.ListListener
import com.smartdevservice.deezerapp.ui.list.VarColumnGridLayoutManager.ColumnCountProvider
import com.smartdevservice.deezerapp.ui.list.VarColumnGridLayoutManager.DefaultColumnCountProvider
import com.smartdevservice.deezerapp.ui.view.ViewSearchSortLayout
import com.smartdevservice.deezerapp.utils.Utils
import com.smartdevservice.deezerapp.utils.Utils.KEY_ALBUM
import com.smartdevservice.domain.model.Album
import com.smartdevservice.domain.model.EnumSortMethod
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListAlbumFragment : BaseFragment() {

    private lateinit var _binding: FragmentListAlbumBinding
    private val albumViewModel: AlbumViewModel by sharedViewModel()
    private lateinit var albumAdapter: AlbumAdapter
    private var listAlbum: List<Album> = arrayListOf()
    private var sortMethod = EnumSortMethod.PAR_DEFAULT

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        albumViewModel.albumsState.subscribe(this, ::handleAllAlbumState)

        val enumDensity = Utils.getEnumDensityOfDevice(requireContext())

        albumAdapter = AlbumAdapter(arrayListOf(), enumDensity, object : ListListener {
            override fun onClickItem(album: Album) {
                findNavController().navigate(
                    R.id.action_ListAlbumFragment_to_DetailsAlbumFragment,
                    Bundle().apply {
                        putParcelable(KEY_ALBUM, album)
                    })
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentListAlbumBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.srlList.setOnRefreshListener {
            albumViewModel.loadingAllAlbum()
        }

        val carColumnGridLayoutManager = VarColumnGridLayoutManager(requireContext(), OrientationHelper.VERTICAL, false)
        val columnProvider: ColumnCountProvider = DefaultColumnCountProvider(requireContext())
        carColumnGridLayoutManager.setColumnCountProvider(columnProvider)

        binding.rvList.apply {
            adapter = albumAdapter
            layoutManager = carColumnGridLayoutManager
        }

        binding.viewSearch.sortSelected = sortMethod
        binding.viewSearch.sortList = arrayListOf(
            EnumSortMethod.PAR_DEFAULT, EnumSortMethod.PAR_TITRE, EnumSortMethod.PAR_NOM_ARTIST,
            EnumSortMethod.PAR_DATE, EnumSortMethod.PAR_NB_TRACKS
        )

        // j'ai ajouter une bar de recherche pour facilite à trouve un album (recherche par "titre", "Nom artist", "date", "nb tracks")
        binding.viewSearch.listener = object : ViewSearchSortLayout.SearchSortListener {
            override fun afterTextChanged(text: String) {
                albumAdapter.setList(listAlbum.search(text))
            }

            override fun onSortedMethodChanged(sortedBy: EnumSortMethod) {
                sortMethod = sortedBy
                albumAdapter.setList(
                    if (sortMethod == EnumSortMethod.PAR_DEFAULT)
                        listAlbum
                    else
                        listAlbum.sortedAlbums(sortMethod)
                )
            }
        }
        albumViewModel.loadingAllAlbum()
    }


    private fun handleAllAlbumState(state: ViewState<ArrayList<Album>>) {
        binding.srlList.isRefreshing = state is LoadingState
        when (state) {
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
                Timber.i("SuccessState, data size = ${state.data?.size}")
                listAlbum = state.data?.toList() ?: arrayListOf()
                albumAdapter.setList(listAlbum)
            }
        }
    }
}
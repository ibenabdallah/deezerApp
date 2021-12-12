package com.smartdevservice.deezerapp.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.smartdevservice.deezerapp.R
import com.smartdevservice.deezerapp.base.BaseFragment
import com.smartdevservice.deezerapp.databinding.FragmentDetailsAlbumBinding
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

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
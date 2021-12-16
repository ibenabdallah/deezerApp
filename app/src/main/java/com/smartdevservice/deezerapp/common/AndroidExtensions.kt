package com.smartdevservice.deezerapp.common

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartdevservice.data.common.CoroutineContextProvider
import com.smartdevservice.domain.model.Album
import com.smartdevservice.domain.model.EnumSortMethod
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import kotlin.Comparator
import kotlin.coroutines.CoroutineContext

inline fun <T> LiveData<T>.subscribe(
    owner: LifecycleOwner,
    crossinline onDataReceived: (T) -> Unit
) =
    observe(owner, { onDataReceived(it) })

inline fun ViewModel.launch(
    coroutineContext: CoroutineContext = CoroutineContextProvider().main,
    crossinline block: suspend CoroutineScope.() -> Unit
): Job {
    return viewModelScope.launch(coroutineContext) {
        block()
    }
}

inline fun View.onClick(crossinline onClick: () -> Unit) {
    setOnClickListener { onClick() }
}

fun List<Album>.search(text: String): List<Album> {
    return this.filter {
        it.title.contains(text, true)
                || it.release_date.contains(text, true)
                || it.artist.name.contains(text, true)
                || it.nb_tracks.toString().contains(text, true)
    }
}

fun List<Album>.sortedAlbums(sortMethod: EnumSortMethod) : List<Album> {
    //list temporaire pour ne pas perdre le trie par défaut
    val listTemp = arrayListOf<Album>()
    listTemp.addAll(this)
    listTemp.sortWith { obj1, obj2 ->
        when (sortMethod) {
            EnumSortMethod.PAR_TITRE -> obj1.title.compareTo(obj2.title, true)
            EnumSortMethod.PAR_NOM_ARTIST -> obj1.artist.name.compareTo(obj2.artist.name)
            EnumSortMethod.PAR_DATE -> obj1.release_date.compareTo(obj2.release_date)
            EnumSortMethod.PAR_NB_TRACKS -> if (obj1.nb_tracks < obj2.nb_tracks) 1 else if (obj1.nb_tracks > obj2.nb_tracks) -1 else 0
            else -> 0
        }
    }
    return listTemp
}
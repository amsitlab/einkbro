package info.plateaukao.einkbro.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import info.plateaukao.einkbro.view.Album

class AlbumViewModel: ViewModel() {
    val albums = mutableStateOf(listOf<Album>())

    fun addAlbum(album: Album, index: Int) {
        albums.value = albums.value.toMutableList().apply {  add(index, album) }.toList()
    }

    fun removeAlbum(album: Album) {
        albums.value = albums.value.toMutableList().apply {
            remove(album)
        }
    }
}
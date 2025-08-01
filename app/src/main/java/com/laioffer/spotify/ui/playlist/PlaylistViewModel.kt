package com.laioffer.spotify.ui.playlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laioffer.spotify.datamodel.Album
import com.laioffer.spotify.datamodel.Song
import com.laioffer.spotify.repository.FavoriteAlbumRepository
import com.laioffer.spotify.repository.PlaylistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaylistViewModel @Inject constructor(
    private val playlistRepository: PlaylistRepository,
    private val favoriteAlbumRepository: FavoriteAlbumRepository
) : ViewModel() {
    // start with a dummy album
    private val _uiState: MutableStateFlow<PlaylistUiState> = MutableStateFlow(
        PlaylistUiState(
            album = Album(
                id = -1,
                name = "",
                cover = "",
                description = "",
                artists = "",
                year = "",
            ), //Album.empty()
        )
    )

    val uiState: StateFlow<PlaylistUiState> = _uiState.asStateFlow()

    fun fetchPlaylist(album: Album) {
        _uiState.value = _uiState.value.copy(album = album)

        // coroutine goes to model and retreives album from endpoint
        viewModelScope.launch {
            Log.d("PlaylistViewModel", "Fetching playlist with id = ${album.id}")
            val playlist = playlistRepository.getPlaylist(album.id)
            _uiState.value = _uiState.value.copy(
                playlist = playlist.songs
            )
            Log.d("PlaylistViewModel", _uiState.value.toString())
        }
        viewModelScope.launch {
            favoriteAlbumRepository.isFavoriteAlbum(album.id).collect{
                _uiState.value = _uiState.value.copy(
                    isFavorite = it
                )
            }
        }
    }
    fun toggleFavorite(isFavorite: Boolean) {
        val album = _uiState.value.album
        viewModelScope.launch {
            if (isFavorite) {
                favoriteAlbumRepository.favoriteAlbum(album)
            } else {
                favoriteAlbumRepository.unFavoriteAlbum(album)
            }
        }
    }


}

data class PlaylistUiState(
    val album: Album,
    val isFavorite: Boolean = false,
    val playlist: List<Song> = emptyList()
)
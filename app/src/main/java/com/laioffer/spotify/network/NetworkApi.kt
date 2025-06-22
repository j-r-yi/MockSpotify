package com.laioffer.spotify.network

import com.laioffer.spotify.datamodel.Playlist
import com.laioffer.spotify.datamodel.Section
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

// definition of spotify backend api call
interface NetworkApi {
    // http://0.0.0.0:8080/feed
    @GET("feed")
    fun getHomeFeed(): Call<List<Section>>

    // http://0.0.0.0:8080/playlist/{id}
    @GET("playlists/{id}")
    fun getPlaylist(@Path("id") id: Int): Call<Playlist>

}
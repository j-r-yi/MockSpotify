package com.laioffer.spotify.network

import com.laioffer.spotify.datamodel.Section
import retrofit2.Call
import retrofit2.http.GET

// definition of spotify backend api call

interface NetworkApi {
    @GET("feed")
    fun getHomeFeed(): Call<List<Section>>
}
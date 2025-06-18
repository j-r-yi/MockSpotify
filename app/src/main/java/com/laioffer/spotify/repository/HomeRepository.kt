package com.laioffer.spotify.repository

import com.laioffer.spotify.datamodel.Section
import com.laioffer.spotify.network.NetworkApi
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import javax.inject.Inject


// @Inject at constructor here
    // 1. defines a provide method
    // 2. inject networkApi
class HomeRepository @Inject constructor(
    private val networkApi: NetworkApi
) {

    // FIXM: main safe
    suspend fun getHomeSections(): List<Section> = withContext(Dispatchers.IO ){
        val response: Response<List<Section>> = networkApi.getHomeFeed().execute()
        val sections: List<Section>? = response.body()
        sections ?: listOf()
    }
}
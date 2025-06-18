package com.laioffer.spotify.repository

class HomeRepository  constructor(
    private val networkApi: NetworkApi
){

    fun getHomeSections(): List<Section>{
        val response: Response<List<Section>!> = networkApi.getHomeFeed().execute()
        val sections: List<section>? = response.body()
        // can be empty for fallback
        return sections ?: listOf()
    }
}
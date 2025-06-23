package com.laioffer.spotify.database

import androidx.room.*
import com.laioffer.spotify.datamodel.Album
import kotlinx.coroutines.flow.Flow

@Dao
interface DatabaseDao {

    // create and update
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun favoriteAlbum(album: Album)

    // read
    @Query("SELECT EXISTS(SELECT * FROM Album WHERE id = :id)")
    fun isFavoriteAlbum(id : Int) : Flow<Boolean>


    // delete
    @Delete
    fun unFavoriteAlbum(album: Album)

    @Query("select * from Album")
    fun fetchFavoriteAlbums(): Flow<List<Album>>
}
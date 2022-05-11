package com.example.moviesapp_v2.data.local

import androidx.room.*
import com.example.moviesapp_v2.data.model.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM MovieEntity")
    suspend fun getAllMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(movie: MovieEntity)
}
package com.example.moviesapp_v2.repository

import com.example.moviesapp_v2.data.model.MovieList


// INTERFACE
interface MovieRepository {
    suspend fun getUpcomingMovies(): MovieList
    suspend fun getTopRatedMovies(): MovieList
    suspend fun getPopularMovies(): MovieList
}
package com.example.moviesapp_v2.data.remote

import com.example.moviesapp_v2.application.AppConstants
import com.example.moviesapp_v2.data.model.MovieList
import com.example.moviesapp_v2.repository.WebService

class remoteMovieDataSource(private val webService: WebService) {

    suspend fun getUpcomingMovies(): MovieList {
        return webService.getUpcomingMovies(AppConstants.API_KEY)
    }

    suspend fun getPopularMovies(): MovieList {
        return webService.getPopularMovies(AppConstants.API_KEY)
    }

    suspend fun getTopRatedMovies(): MovieList {
        return webService.getTopRatedMovies(AppConstants.API_KEY)
    }
}
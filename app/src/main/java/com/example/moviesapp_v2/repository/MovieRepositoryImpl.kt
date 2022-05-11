package com.example.moviesapp_v2.repository

import com.example.moviesapp_v2.core.InternetCheck
import com.example.moviesapp_v2.data.local.localMovieDataSource
import com.example.moviesapp_v2.data.model.MovieList
import com.example.moviesapp_v2.data.model.toMovieEntity
import com.example.moviesapp_v2.data.remote.remoteMovieDataSource

// INTERFACE IMPLEMENTATION



class MovieRepositoryImpl(
    private val dataSourceRemote: remoteMovieDataSource,
    private val dataSourceLocal: localMovieDataSource
) : MovieRepository {

    override suspend fun getUpcomingMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getUpcomingMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("upcoming"))
            }
            dataSourceLocal.getUpcomingMovies()
        } else {
            dataSourceLocal.getUpcomingMovies()
        }
    }

    override suspend fun getPopularMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getPopularMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("popular"))
            }
            dataSourceLocal.getPopularMovies()
        } else {
            dataSourceLocal.getPopularMovies()
        }
    }

    override suspend fun getTopRatedMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getTopRatedMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("toprated"))
            }
            dataSourceLocal.getTopRatedMovies()
        } else {
            dataSourceLocal.getTopRatedMovies()
        }
    }
}
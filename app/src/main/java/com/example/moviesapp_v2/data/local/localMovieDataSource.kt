package com.example.moviesapp_v2.data.local
/*
import com.example.moviesapp_v2.data.model.MovieEntity
import com.example.moviesapp_v2.data.model.MovieList
import com.example.moviesapp_v2.data.model.toMovieList

class localMovieDataSource(private val movieDao: MovieDao) {
    suspend fun getUpcomingMovies(): MovieList {
        return movieDao.getAllMovies().filter{it.movie_type == "upcoming"}.toMovieList()
    }

    suspend fun getTopRatedMovies(): MovieList {
        return movieDao.getAllMovies().toMovieList()
        return movieDao.getAllMovies().filter{it.movie_type == "toprated"}.toMovieList()
    }

    suspend fun getPopularMovies(): MovieList {
        return movieDao.getAllMovies().toMovieList()
        return movieDao.getAllMovies().filter{it.movie_type == "popular"}.toMovieList()
    }

    suspend fun saveMovie(movie: MovieEntity){
        movieDao.saveMovie(movie)
    }
}*/
import com.example.moviesapp_v2.data.model.MovieEntity
import com.example.moviesapp_v2.data.model.MovieList
import com.example.moviesapp_v2.data.model.toMovieList

class localMovieDataSource(private val movieDao: MovieDao) {

    suspend fun getUpcomingMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "upcoming" }.toMovieList()
    }

    suspend fun getPopularMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "popular" }.toMovieList()
    }

    suspend fun getTopRatedMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "toprated" }.toMovieList()
    }

    suspend fun saveMovie(movie: MovieEntity) {
        movieDao.saveMovie(movie)
    }
}
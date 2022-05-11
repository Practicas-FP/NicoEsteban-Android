package com.example.moviesapp_v2.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.moviesapp_v2.core.Resource
import com.example.moviesapp_v2.repository.MovieRepository
import kotlinx.coroutines.Dispatchers


/* The ViewModel layer get the information obtained from the Server by the DATA layer and
   prepares that information for the UI layer.

   Repository > ViewModel > UI
*/
class MovieViewModel(private val repo: MovieRepository) : ViewModel() {

    fun fetchMainScreenMovies() = liveData(Dispatchers.IO) {
        //Emit info to the UI
        emit(Resource.Loading())

        try {
            emit(
                Resource.Success(Triple(repo.getUpcomingMovies(), repo.getPopularMovies(), repo.getTopRatedMovies())))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}

class MovieViewModelFactory(private val repo: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }
}

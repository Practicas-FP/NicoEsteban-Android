package com.example.moviesapp_v2.ui.movie


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.moviesapp_v2.R
import com.example.moviesapp_v2.core.Resource
import com.example.moviesapp_v2.data.local.AppDatabase
import com.example.moviesapp_v2.data.local.localMovieDataSource
import com.example.moviesapp_v2.data.model.Movie
import com.example.moviesapp_v2.data.remote.remoteMovieDataSource
import com.example.moviesapp_v2.databinding.FragmentMovieBinding
import com.example.moviesapp_v2.presentation.MovieViewModel
import com.example.moviesapp_v2.presentation.MovieViewModelFactory
import com.example.moviesapp_v2.repository.MovieRepositoryImpl
import com.example.moviesapp_v2.repository.RetrofitClient
import com.example.moviesapp_v2.ui.movie.adapters.MovieAdapter
import com.example.moviesapp_v2.ui.movie.adapters.concat.PopularConcatAdapter
import com.example.moviesapp_v2.ui.movie.adapters.concat.TopRatedConcatAdapter
import com.example.moviesapp_v2.ui.movie.adapters.concat.UpcomingConcatAdapter

class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.OnMovieClickListener {

    private lateinit var binding: FragmentMovieBinding
    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(
            MovieRepositoryImpl(
                remoteMovieDataSource(RetrofitClient.webservice),
                localMovieDataSource(AppDatabase.getDatabase(requireContext()).movieDao())
            )
        )
    }

    private lateinit var concatAdapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)

        concatAdapter = ConcatAdapter()

        // Obtaining Movies (3 sections)
        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.barraProgreso.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    Log.d("LiveDataAll", "Obteniendo data")
                    binding.barraProgreso.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(
                            0,
                            UpcomingConcatAdapter(
                                MovieAdapter(
                                    result.data.first.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            1,
                            PopularConcatAdapter(
                                MovieAdapter(
                                    result.data.second.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            2,
                            TopRatedConcatAdapter(
                                MovieAdapter(
                                    result.data.third.results,
                                    this@MovieFragment
                                )
                            )
                        )
                    }
                    binding.rvMovies.adapter = concatAdapter
                }
                is Resource.Failure -> {
                    binding.barraProgreso.visibility = View.GONE
                    Log.d("LiveDataAll", "${result.exception}")
                }
            }
        })


    }

    override fun onMovieClick(movie: Movie) {
        //Sending safe args to MovieDetails fragment
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            movie.poster_path,
            movie.backdrop_path,
            movie.vote_average.toFloat(),
            movie.vote_count,
            movie.overview,
            movie.title,
            movie.original_language,
            movie.release_date
        )
        findNavController().navigate(action)
    }
}
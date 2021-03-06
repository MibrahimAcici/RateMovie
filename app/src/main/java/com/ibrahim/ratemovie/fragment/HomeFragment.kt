package com.ibrahim.ratemovie.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahim.ratemovie.R
import com.ibrahim.ratemovie.adapter.MovieAdapter
import com.ibrahim.ratemovie.databinding.FragmentHomeBinding
import com.ibrahim.ratemovie.model.MoviesResponse
import com.ibrahim.ratemovie.service.MovieAPI
import com.ibrahim.ratemovie.viewmodel.HomeViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment:Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    lateinit var movieAdapter: MovieAdapter
    private val viewModel: HomeViewModel by viewModels()
/*
    private val viewModel: HomeViewModel by viewModels()


    lateinit var movieListRepo: MovieListRepo
*/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

    initAdapter()
    fetchMovie()
    observeMovieList()
    observeError()

    return view
}

    private fun observeError() {
        viewModel.errorLiveData.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
            binding.recyclerView.isVisible = true
            binding.progress.isVisible = false
        }
    }

    private fun observeMovieList() {
        viewModel.movieList.observe(viewLifecycleOwner) { movieList ->
            movieAdapter.setList(movieList)
            binding.recyclerView.isVisible = true
            binding.progress.isVisible = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAdapter() {
        movieAdapter = MovieAdapter { movie ->
            findNavController().navigate(
                R.id.action_homeFragment_to_movieDetailFragment,
                bundleOf("movie" to movie)
            )
        }
        binding.recyclerView.adapter = movieAdapter
        //Adapterin Ekrandaki G??r??n??m??
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = layoutManager
    }

    private fun fetchMovie() {
        binding.recyclerView.isVisible = false
        binding.progress.isVisible = true
        viewModel.fetchMovies()

    }
}
  /*  private fun fetchMovie() {
        binding.recyclerView.isVisible = false
        binding.progress.isVisible = true
        //Servise istek atma
        val request = MovieAPI().getMovieService().getPlaces("d8645a71bef876249378e16b83434d01")
        request.enqueue(object : Callback<MoviesResponse> {//Liste de??il [ ile ba??lam??yor Callback<List<??eklinde yazmad??k
        override fun onResponse(
            call: Call<MoviesResponse>,
            response: Response<MoviesResponse>
        ) {
            //Toast.makeText(applicationContext, response.body()?.result?.get(0)?.lokasyon,Toast.LENGTH_LONG).show()

            movieAdapter.setList(response.body()?.results ?: emptyList())
            binding.recyclerView.isVisible = true
            binding.progress.isVisible = false
        }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Toast.makeText(context,t.message.toString(), Toast.LENGTH_LONG).show()
                binding.recyclerView.isVisible = true
                binding.progress.isVisible = false
            }
        })
    }*/

/*
        initAdapter()
        fetchMovie()
        observeMovieList()
        observeError()

        return view
    }

    private fun observeError() {
        viewModel.errorLiveData.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
            binding.recyclerView.isVisible = true
            binding.progress.isVisible = false
        }
    }

    private fun observeMovieList() {
        viewModel.movieList.observe(viewLifecycleOwner) { movieList ->
            movieAdapter.setList(movieList)
            binding.recyclerView.isVisible = true
            binding.progress.isVisible = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
/*
    private fun initAdapter() {
        movieAdapter = MovieAdapter { movie ->
            findNavController().navigate(
                R.id.action_homeFragment_to_detailFragment,
                bundleOf("movie" to movie)
            )
        }
        binding.recyclerView.adapter = movieAdapter
    }*/

    private fun initAdapter() {
        //Adapter Tan??mlay??p ba??latma
        movieAdapter = MovieAdapter()
        binding.recyclerView.adapter = movieAdapter
        //Adapterin Ekrandaki G??r??n??m??
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = layoutManager
    }

    private fun fetchMovie() {
        binding.recyclerView.isVisible = false
        binding.progress.isVisible = true
        viewModel.fetchMovies()

    }
}
*/
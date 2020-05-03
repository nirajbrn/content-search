package com.niraj.contentsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.niraj.contentsearch.adapter.MoviesAdapter
import com.niraj.contentsearch.utils.SearchQueryTextListener
import com.niraj.presentation.factory.ViewModelFactory
import com.niraj.presentation.model.Movie
import com.niraj.presentation.model.Status
import com.niraj.presentation.viewmodels.SearchMovieVM
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {
    val TAG = "HomeActivity"

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var searchMovieVM: SearchMovieVM

    private var movie: Movie? = null

    private lateinit var movieAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movieAdapter = MoviesAdapter()
        movieRv.layoutManager = LinearLayoutManager(this)
        movieRv.adapter = movieAdapter

        searchMovieVM = ViewModelProviders.of(this, viewModelFactory).get(SearchMovieVM::class.java)

        searchKeyword("swan")

        searchView.setOnQueryTextListener(
            SearchQueryTextListener(
                this@HomeActivity
            ) { newText ->
                Log.d(TAG, "New Text: $newText")
                newText?.let {
                    if (it.isEmpty()) {
//                        searchMovieVM.resetSearch()
                        movieAdapter.reset()
                    } else {
                        searchKeyword(it)
                    }
                }
            }
        )
    }

    override fun onResume() {
        super.onResume()
        shimmerFrameLayout.startShimmerAnimation()
    }

    override fun onPause() {
        super.onPause()
        shimmerFrameLayout.stopShimmerAnimation()
    }

    private fun startShimmerAnimation() {
        shimmerFrameLayout.startShimmerAnimation()
        shimmerFrameLayout.visibility = View.VISIBLE
        movieRv.visibility = View.GONE
    }

    private fun stopShimmerAnimation() {
        shimmerFrameLayout.stopShimmerAnimation()
        shimmerFrameLayout.visibility = View.GONE
        movieRv.visibility = View.VISIBLE
    }

    private fun searchKeyword(keyword: String) {
        Log.d(TAG, "#niraj searchKeyword key: $keyword")
        startShimmerAnimation()
        searchMovieVM.searchMovies(keyword, 1)
            .observe(this, Observer { resource ->
                when (resource.status) {
                    Status.LOADING -> {
                        Log.d(TAG, "loading..")
                    }
                    Status.ERROR -> {
                        Log.d(TAG, "Error: ${resource.message}")
                        shimmerFrameLayout.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        stopShimmerAnimation()
                        resource.data?.let { movies ->
                            Log.d(TAG, "#niraj key: $keyword response: ${movies.size}")
                            if (movies.isEmpty()) {
                                Toast.makeText(this, R.string.error_message, Toast.LENGTH_SHORT)
                                    .show()
                            }
                            movieAdapter.populate(movies)
                        }
                    }
                }
            })
    }
}

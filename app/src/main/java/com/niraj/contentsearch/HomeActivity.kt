package com.niraj.contentsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
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

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchMovieVM = ViewModelProviders.of(this, viewModelFactory).get(SearchMovieVM::class.java)



        searchView.setOnQueryTextListener(
            SearchQueryTextListener(
                this@HomeActivity
            ) { newText ->
                Log.d(TAG, "New Text: $newText")
                newText?.let {
                    if (it.isEmpty()) {
                        //searchMovieVM.resetSearch()
                    } else {
                        searchMovieVM.searchMovies(it, 1)
                            .observe(this, Observer { resource ->
                                when (resource.status) {
                                    Status.LOADING -> {
                                        Log.d(TAG, "loading..")
                                    }
                                    Status.ERROR -> {
                                        Log.d(TAG, "Error: ${resource.message}")
                                    }
                                    Status.SUCCESS -> {
                                        Log.d(TAG, "Success Data: ${resource.message}")
                                    }
                                }
                            })
                    }
                }
            }
        )
    }
}

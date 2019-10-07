package mfr.example.movies_tvshow.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mfr.example.movies_tvshow.data.source.MovieTvShowsDataSource
import mfr.example.movies_tvshow.data.source.remote.response.MoviesItems
import mfr.example.movies_tvshow.utils.NetworkState

class MoviesViewModel(val remotes :  MovieTvShowsDataSource ) : ViewModel() {

    internal fun getMoviesData(): LiveData<ArrayList<MoviesItems>> {
        return remotes.getMovies()
    }

    fun getLoader() : MutableLiveData<NetworkState>{

        return  remotes.getLoader()

    }
}
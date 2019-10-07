package mfr.example.movies_tvshow.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mfr.example.movies_tvshow.data.source.remote.response.MoviesItems
import mfr.example.movies_tvshow.data.source.remote.response.TvShowsItems
import mfr.example.movies_tvshow.utils.NetworkState

interface MovieTvShowsDataSource {

    fun getMovies(): LiveData<ArrayList<MoviesItems>>
    fun getTvShows(): LiveData<ArrayList<TvShowsItems>>
    fun getLoader() : MutableLiveData<NetworkState>


}
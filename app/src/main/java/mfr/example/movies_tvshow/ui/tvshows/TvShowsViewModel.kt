package mfr.example.movies_tvshow.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mfr.example.movies_tvshow.data.source.MovieTvShowsDataSource
import mfr.example.movies_tvshow.data.source.remote.response.TvShowsItems
import mfr.example.movies_tvshow.utils.NetworkState

class TvShowsViewModel (val remotes : MovieTvShowsDataSource) : ViewModel() {

    internal fun getTvShowsData(): LiveData<ArrayList<TvShowsItems>> {
        return remotes.getTvShows()
    }

    fun getLoader() : MutableLiveData<NetworkState> {

        return  remotes.getLoader()

    }
}
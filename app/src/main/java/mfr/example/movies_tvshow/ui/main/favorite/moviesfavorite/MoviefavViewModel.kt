package mfr.example.movies_tvshow.ui.main.favorite.moviesfavorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import mfr.example.movies_tvshow.data.source.local.DataSourcelocal
import mfr.example.movies_tvshow.data.source.local.entity.MovieEntity.FavoriteEntity
import mfr.example.movies_tvshow.utils.NetworkState

class MoviefavViewModel(val local : DataSourcelocal) : ViewModel(){

    fun getdata() : LiveData<PagedList<FavoriteEntity>> {
        return LivePagedListBuilder(local.getAllFavs(),20).build()
    }
    fun getLoader() : MutableLiveData<NetworkState> {

        return  local.getLoader()

    }
}
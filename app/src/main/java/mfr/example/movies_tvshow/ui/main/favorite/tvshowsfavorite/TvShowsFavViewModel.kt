package mfr.example.movies_tvshow.ui.main.favorite.tvshowsfavorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import mfr.example.movies_tvshow.data.source.local.DataSourcelocal
import mfr.example.movies_tvshow.data.source.local.entity.TvShowFavorite.FvTvShowEntity
import mfr.example.movies_tvshow.utils.NetworkState

class TvShowsFavViewModel(val local : DataSourcelocal) :ViewModel() {

    fun getdata() : LiveData<PagedList<FvTvShowEntity>> {
        return LivePagedListBuilder(local.getAllFvTvShow(),20).build()
    }
    fun getLoader() : MutableLiveData<NetworkState> {

        return  local.getLoader()

    }
}
package mfr.example.movies_tvshow.data.source.local

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import mfr.example.movies_tvshow.data.source.local.entity.MovieEntity.FavoriteEntity
import mfr.example.movies_tvshow.data.source.local.entity.TvShowFavorite.FvTvShowEntity
import mfr.example.movies_tvshow.utils.NetworkState


interface DataSourcelocal {

    fun insertFavorites(fav : ArrayList<FavoriteEntity>)
    fun getAllFav(id : Int):  DataSource.Factory<Integer, FavoriteEntity>
    fun getAllFavs():  DataSource.Factory<Integer, FavoriteEntity>
    fun deleteAll(idfav : Int)
    fun insertFvTvShow(fav : ArrayList<FvTvShowEntity>)
    fun getFvTvShow(id : Int):  DataSource.Factory<Integer, FvTvShowEntity>
    fun getAllFvTvShow():  DataSource.Factory<Integer, FvTvShowEntity>
    fun deleteAllFvTvShow(idfav : Int)
    fun getLoader() : MutableLiveData<NetworkState>
}
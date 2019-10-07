package mfr.example.movies_tvshow.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import mfr.example.movies_tvshow.data.source.local.DataSourcelocal
import mfr.example.movies_tvshow.data.source.local.entity.MovieEntity.FavoriteEntity
import mfr.example.movies_tvshow.data.source.local.entity.TvShowFavorite.FvTvShowEntity


class DetailsViewModel(val local : DataSourcelocal) : ViewModel() {

    fun insert(arr : ArrayList<FavoriteEntity>){
            local.insertFavorites(arr)
    }

    fun getdata( id :Int) : LiveData<PagedList<FavoriteEntity>> {
        return LivePagedListBuilder(local.getAllFav(id),20).build()
    }

    fun deletedata(idfav : Int){
        return local.deleteAll(idfav)
    }

    fun inserttvshow(arr : ArrayList<FvTvShowEntity>){
        local.insertFvTvShow(arr)
    }

    fun getdatatvshow( id :Int) : LiveData<PagedList<FvTvShowEntity>> {
        return LivePagedListBuilder(local.getFvTvShow(id),20).build()
    }

    fun deletedatatvshow(idfav : Int){
        return local.deleteAllFvTvShow(idfav)
    }




}
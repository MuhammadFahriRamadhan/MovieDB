package mfr.example.movies_tvshow.data.source.local

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import mfr.example.movies_tvshow.data.source.local.entity.MovieEntity.FavoriteDao
import mfr.example.movies_tvshow.data.source.local.entity.MovieEntity.FavoriteEntity
import mfr.example.movies_tvshow.data.source.local.entity.TvShowFavorite.FvTvShowDao
import mfr.example.movies_tvshow.data.source.local.entity.TvShowFavorite.FvTvShowEntity
import mfr.example.movies_tvshow.utils.NetworkState
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class LocalRepository(val mFavDao: FavoriteDao, val mFvTvshowDao: FvTvShowDao) : DataSourcelocal{



    private var executorService: ExecutorService? = null
    val networkState = MutableLiveData<NetworkState>()


    init {
    executorService = Executors.newSingleThreadExecutor()

    }


    override fun insertFavorites(fav : ArrayList<FavoriteEntity>) {
        executorService?.execute(object : Runnable{
            override fun run() {
                mFavDao.insert(fav)
            }

        })
    }

   override fun getAllFav(ids : Int): DataSource.Factory<Integer, FavoriteEntity> {
       networkState.postValue(NetworkState.LOADED)
        return mFavDao.getAll(ids)

    }

    override fun getAllFavs(): DataSource.Factory<Integer, FavoriteEntity> {
        networkState.postValue(NetworkState.LOADED)
        return mFavDao.getAllFavs()

    }



    override fun deleteAll(idfav: Int) {
        executorService?.execute(object : Runnable{
            override fun run() {
                mFavDao.delete(idfav)
            }

        })
    }


    override fun insertFvTvShow(fav: ArrayList<FvTvShowEntity>) {
        executorService?.execute(object : Runnable{
            override fun run() {
                mFvTvshowDao.insert(fav)
            }

        })
    }

    override fun getFvTvShow(id: Int): DataSource.Factory<Integer, FvTvShowEntity> {
        return mFvTvshowDao.getAll(id)
    }

    override fun getAllFvTvShow(): DataSource.Factory<Integer, FvTvShowEntity> {
        return mFvTvshowDao.getAllFavss()
    }

    override fun deleteAllFvTvShow(idfav: Int) {
        executorService?.execute(object : Runnable{
            override fun run() {
                mFvTvshowDao.delete(idfav)
            }

        })
    }

    override fun getLoader(): MutableLiveData<NetworkState> = networkState


}
package mfr.example.movies_tvshow.data.source

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import mfr.example.movies_tvshow.data.source.remote.RemoteRepository
import mfr.example.movies_tvshow.data.source.remote.response.MoviesItems
import mfr.example.movies_tvshow.data.source.remote.response.TvShowsItems
import mfr.example.movies_tvshow.utils.EspressoIdlingResource
import mfr.example.movies_tvshow.utils.NetworkState

class MovieTvShowsRepository : MovieTvShowsDataSource{
    private val moviesLiveData = MutableLiveData<ArrayList<MoviesItems>>()
    private val TvShowsLiveData = MutableLiveData<ArrayList<TvShowsItems>>()
    val remostes = RemoteRepository()
    val networkState = MutableLiveData<NetworkState>()



    @SuppressLint("CheckResult")
    override fun getMovies(): LiveData<ArrayList<MoviesItems>> {
        networkState.postValue(NetworkState.LOADING)
        EspressoIdlingResource.increment()

        remostes.getMoviesApiCall()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                {
                    this.moviesLiveData.postValue(it.results)
                    networkState.postValue(NetworkState.LOADED)
                    if (!EspressoIdlingResource.espressoIdlingResource.isIdleNow) {
                        EspressoIdlingResource.decrement()
                    }



                },
                {
                    it.printStackTrace()
                    networkState.postValue(NetworkState.LOADED)
                    if (!EspressoIdlingResource.espressoIdlingResource.isIdleNow) {
                        EspressoIdlingResource.decrement()
                    }
                }
            )

        return moviesLiveData
    }


    @SuppressLint("CheckResult")
    override fun getTvShows(): LiveData<ArrayList<TvShowsItems>> {
        networkState.postValue(NetworkState.LOADING)
        EspressoIdlingResource.increment()
        remostes.getPupolarTvShowsApiCall()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                this.TvShowsLiveData.postValue(it.results)
                networkState.postValue(NetworkState.LOADED)
                if (!EspressoIdlingResource.espressoIdlingResource.isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            },{
                it.printStackTrace()
                networkState.postValue(NetworkState.LOADED)
                if (!EspressoIdlingResource.espressoIdlingResource.isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            })

        return TvShowsLiveData
    }

    override fun getLoader()  = networkState


}
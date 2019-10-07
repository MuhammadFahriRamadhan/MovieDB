package mfr.example.movies_tvshow.data.source.remote

import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Observable
import mfr.example.movies_tvshow.data.source.remote.response.MovieResponse
import mfr.example.movies_tvshow.data.source.remote.response.ObjectConstant.API_KEY
import mfr.example.movies_tvshow.data.source.remote.response.ObjectConstant.API_MOVIES
import mfr.example.movies_tvshow.data.source.remote.response.ObjectConstant.API_TVSHOWS
import mfr.example.movies_tvshow.data.source.remote.response.TvShowsResponse

class RemoteRepository {

    fun getMoviesApiCall(): Observable<MovieResponse>? {
        return Rx2AndroidNetworking.get(API_MOVIES)
            .addQueryParameter("api_key", API_KEY)
            .addQueryParameter("language", "en-US")
            .build()
            .getObjectObservable(MovieResponse::class.java)
    }

    fun getPupolarTvShowsApiCall(): Observable<TvShowsResponse>? {
        return Rx2AndroidNetworking.get(API_TVSHOWS)
            .addQueryParameter("api_key", API_KEY)
            .addQueryParameter("language", "en-US")
            .build()
            .getObjectObservable(TvShowsResponse::class.java)
    }
}
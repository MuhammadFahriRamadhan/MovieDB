package mfr.example.movies_tvshow.data.source.remote.response

import mfr.example.movies_tvshow.BuildConfig

object ObjectConstant {

    const val API_MOVIES = BuildConfig.BASE_URL + "movie/now_playing"

    const val API_TVSHOWS = BuildConfig.BASE_URL + "tv/popular"

    const val API_KEY = BuildConfig.API_KEY
}
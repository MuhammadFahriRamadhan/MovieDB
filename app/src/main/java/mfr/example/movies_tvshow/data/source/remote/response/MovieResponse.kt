package mfr.example.movies_tvshow.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class MovieResponse(val results : ArrayList<MoviesItems> )

@Parcelize
data class MoviesItems(
    var id : String? = null,
    var poster_path: String? = null,
    var overview: String? = null,
    var release_date: String? = null,
    var title: String? = null,
    var vote_average : String? = null) : Parcelable
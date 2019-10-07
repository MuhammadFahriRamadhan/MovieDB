package mfr.example.movies_tvshow.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class TvShowsResponse(val results : ArrayList<TvShowsItems>)

@Parcelize
data class TvShowsItems(
    var id : String? = null,
    var poster_path: String? = null,
    var overview: String? = null,
    var first_air_date: String? = null,
    var name: String? = null,
    var vote_average : String? = null
): Parcelable
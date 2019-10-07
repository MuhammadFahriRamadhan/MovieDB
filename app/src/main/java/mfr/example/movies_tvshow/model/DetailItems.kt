package mfr.example.movies_tvshow.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailItems(
    val id :String?,
    val vote_average : String?,
    val tv_title : String?,
    val popularity : String?,
    val img_poster : String?,
    val overview : String?,
    val tv_releasedate : String?,
    val status :  String?

) : Parcelable


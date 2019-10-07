package mfr.example.movies_tvshow.utils

import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide

internal fun ImageView.ImageLoad(url: String?) {

    Log.e("sttingurl", "mesassageurl" + url)

    Glide.with(this.context).load("http://image.tmdb.org/t/p/w185"+url).centerCrop().into(this)

}
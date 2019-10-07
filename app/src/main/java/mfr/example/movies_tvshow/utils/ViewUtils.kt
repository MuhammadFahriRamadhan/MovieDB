package mfr.example.movies_tvshow.utils

import android.view.View
import android.widget.ProgressBar

object ViewUtils {


    fun progressConfig(view: ProgressBar, networkState: NetworkState?) {
        when (networkState) {
            NetworkState.LOADING -> view.visibility = View.VISIBLE
            else -> view.visibility = View.GONE
        }
    }
}
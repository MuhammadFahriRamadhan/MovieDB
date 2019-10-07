package mfr.example.movies_tvshow.ui.main.favorite.moviesfavorite

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import mfr.example.movies_tvshow.R
import mfr.example.movies_tvshow.data.source.local.entity.MovieEntity.FavoriteEntity
import mfr.example.movies_tvshow.utils.ImageLoad


class MovieFavAdapter : PagedListAdapter<FavoriteEntity, MovieFavAdapter.MovieFavViewHolder>(DIFF_CALLBACK) {
    private lateinit var  moviesfavlistener :(FavoriteEntity) -> Unit

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): MovieFavViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieFavViewHolder(view)
    }

    override fun onBindViewHolder( holder: MovieFavViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            data.let {
                it.poster_path.let {holder.imagemovies.ImageLoad(it)}
                holder.tv_title.text = it.title
                holder.tv_overview.text = it.overview
                holder.tv_release.text = it.release_date

            }
            holder.itemView.setOnClickListener {
                Log.i("kesini","")
                moviesfavlistener ( data )
            }
        }
    }

    inner class MovieFavViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        var imagemovies : ImageView
        var tv_title : TextView
        var tv_overview : TextView
        var tv_release : TextView


        init {
            imagemovies = view.findViewById(R.id.imageViews)
            tv_title = view.findViewById(R.id.tv_title)
            tv_overview = view.findViewById(R.id.tv_overview)
            tv_release = view.findViewById(R.id.tv_releasedate)

        }
    }

    fun setFavMoviesOnclickListener(listenermoviesfav : (FavoriteEntity) -> Unit){
        this.moviesfavlistener = listenermoviesfav


    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoriteEntity>() {
            // Concert details may have changed if reloaded from the database,
            // but ID is fixed.
            override fun areItemsTheSame(oldFav: FavoriteEntity, newFav: FavoriteEntity): Boolean {
                return oldFav.title.equals(newFav.title)
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldFav: FavoriteEntity, newFav: FavoriteEntity): Boolean {
                return oldFav == newFav
            }
        }
    }
}
package mfr.example.movies_tvshow.ui.main.favorite.tvshowsfavorite

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
import mfr.example.movies_tvshow.data.source.local.entity.TvShowFavorite.FvTvShowEntity
import mfr.example.movies_tvshow.utils.ImageLoad

class TvShowsAdapter : PagedListAdapter<FvTvShowEntity,TvShowsAdapter.TvShowsViewHolder>(DIFF_CALLBACK)  {
    private lateinit var  tvshowsfavlistener :(FvTvShowEntity) -> Unit

    override fun onBindViewHolder(holder: TvShowsViewHolder, position: Int) {
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
                tvshowsfavlistener ( data )
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tv_shows, parent, false)
        return TvShowsViewHolder(view)
    }
    inner class TvShowsViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        var imagemovies : ImageView
        var tv_title : TextView
        var tv_overview : TextView
        var tv_release : TextView


        init {
            imagemovies = view.findViewById(R.id.img_tvshows)
            tv_title = view.findViewById(R.id.tv_titleshow)
            tv_overview = view.findViewById(R.id.tv_overviewshow)
            tv_release = view.findViewById(R.id.tv_releasedateshow)

        }
    }

    fun setFavTvShowsOnclickListener(listenertvshowsfav : (FvTvShowEntity) -> Unit){
        this.tvshowsfavlistener = listenertvshowsfav


    }


    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FvTvShowEntity>() {
            // Concert details may have changed if reloaded from the database,
            // but ID is fixed.
            override fun areItemsTheSame(oldFav: FvTvShowEntity, newFav: FvTvShowEntity): Boolean {
                return oldFav.title.equals(newFav.title)
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldFav: FvTvShowEntity, newFav: FvTvShowEntity): Boolean {
                return oldFav == newFav
            }
        }
    }
}
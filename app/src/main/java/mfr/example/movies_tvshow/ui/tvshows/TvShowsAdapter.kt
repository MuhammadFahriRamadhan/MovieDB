package mfr.example.movies_tvshow.ui.tvshows

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mfr.example.movies_tvshow.R
import mfr.example.movies_tvshow.data.source.remote.response.TvShowsItems
import mfr.example.movies_tvshow.utils.ImageLoad

class TvShowsAdapter (val listtvshows : ArrayList<TvShowsItems>): RecyclerView.Adapter<TvShowsAdapter.tvshowsholder>() {
    private lateinit var  tvshowlistener :(TvShowsItems) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): tvshowsholder {

        return tvshowsholder(LayoutInflater.from(parent.context).inflate(R.layout.item_tv_shows,parent,false))
    }

    override fun getItemCount(): Int {

        return listtvshows.size
    }

    fun AddTvShow(datas : ArrayList<TvShowsItems>){
        this.listtvshows.clear()
        this.listtvshows.addAll(datas)
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: tvshowsholder, position: Int) {
        holder.binddata(listtvshows[position])
    }


    inner class tvshowsholder(view : View) : RecyclerView.ViewHolder(view){
        val imagemovies : ImageView = view.findViewById(R.id.img_tvshows)
        val tv_title : TextView = view.findViewById(R.id.tv_titleshow)
        val tv_overview : TextView = view.findViewById(R.id.tv_overviewshow)
        val tv_release : TextView = view.findViewById(R.id.tv_releasedateshow)


        fun binddata(datas : TvShowsItems){

            datas.let {
                it.poster_path.let {imagemovies.ImageLoad(it)}
                tv_title.text = it.name
                tv_overview.text = it.overview
                tv_release.text = it.first_air_date

            }

            itemView.setOnClickListener { tvshowlistener(datas) }
        }

    }

    fun setTvShowsOnclickListener(listenertvshows : (TvShowsItems) -> Unit){
        this.tvshowlistener = listenertvshows

    }
}
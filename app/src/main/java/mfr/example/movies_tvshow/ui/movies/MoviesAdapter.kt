package mfr.example.movies_tvshow.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mfr.example.movies_tvshow.R
import mfr.example.movies_tvshow.data.source.remote.response.MoviesItems
import mfr.example.movies_tvshow.utils.ImageLoad

class MoviesAdapter (val listmovies: ArrayList<MoviesItems>): RecyclerView.Adapter<MoviesAdapter.moviesholder>() {

    private lateinit var  movieslistener :(MoviesItems) -> Unit



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): moviesholder {

        return moviesholder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie,parent,false))
    }

    override fun getItemCount(): Int {

        return listmovies.size
    }

    fun AddMovies(datas : ArrayList<MoviesItems>){
        this.listmovies.clear()
        this.listmovies.addAll(datas)
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: moviesholder, position: Int) {
        holder.binddata(listmovies[position])
    }


    inner class moviesholder(view : View) : RecyclerView.ViewHolder(view){
        val imagemovies : ImageView = view.findViewById(R.id.imageViews)
        val tv_title : TextView = view.findViewById(R.id.tv_title)
        val tv_overview : TextView = view.findViewById(R.id.tv_overview)
        val tv_release : TextView = view.findViewById(R.id.tv_releasedate)


        fun binddata(datas : MoviesItems){

            datas.let {
                it.poster_path.let {imagemovies.ImageLoad(it)}
                tv_title.text = it.title
                tv_overview.text = it.overview
                tv_release.text = it.release_date

            }

            itemView.setOnClickListener { movieslistener(datas) }
        }

    }

    fun setMoviesOnclickListener(listenermovies : (MoviesItems) -> Unit){
        this.movieslistener = listenermovies


    }
}
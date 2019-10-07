package mfr.example.movies_tvshow.ui.main.favorite.moviesfavorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.fragment_tv_shows.*
import mfr.example.movies_tvshow.R
import mfr.example.movies_tvshow.data.source.local.entity.MovieEntity.FavoriteEntity
import mfr.example.movies_tvshow.model.DetailItems
import mfr.example.movies_tvshow.ui.details.DetailsActivity
import mfr.example.movies_tvshow.utils.ViewUtils
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx

import org.koin.android.ext.android.inject

class MoviefavFragment : Fragment() {
    val mviemodelfav : MoviefavViewModel by inject()
    private lateinit var madapter: MovieFavAdapter
    val moviesdetail = ArrayList<DetailItems>()

    companion object {

        fun newInstance(): MoviefavFragment = MoviefavFragment()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        madapter = MovieFavAdapter()
        mviemodelfav.getdata().observe(this, favObserver)
        mviemodelfav.getLoader().observe(this, Observer {
            Log.i("mains","data : "+it.toString())

            ViewUtils.progressConfig(spin_kit, it)

        })
        madapter.setFavMoviesOnclickListener {
            this.moviesdetail.clear()
            moviesdetail.add(DetailItems(it.id.toString(),it.vote_average,it.title,it.vote_average,it.poster_path,it.overview,it.release_date,"movies"))
            Log.i("mains","data : "+it.toString())
            ctx.startActivity<DetailsActivity>("listdata" to moviesdetail)
        }
        val gridlayout = LinearLayoutManager(view.context)
        rcv_movies.layoutManager = gridlayout
        rcv_movies.adapter = madapter
    }



    private val favObserver =
        Observer<PagedList<FavoriteEntity>> {
                Log.i("dataswa","data"+it)
                madapter.submitList(it)

        }



}
package mfr.example.movies_tvshow.ui.main.favorite.tvshowsfavorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_tv_shows.*
import mfr.example.movies_tvshow.R
import mfr.example.movies_tvshow.data.source.local.entity.TvShowFavorite.FvTvShowEntity
import mfr.example.movies_tvshow.model.DetailItems
import mfr.example.movies_tvshow.ui.details.DetailsActivity
import mfr.example.movies_tvshow.utils.ViewUtils
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.koin.android.ext.android.inject

class TvShowsFavFragment : Fragment() {
    val mviemodelfav : TvShowsFavViewModel by inject()
    private lateinit var madapter: TvShowsAdapter
    val moviesdetail = ArrayList<DetailItems>()

    companion object {

        fun newInstance(): TvShowsFavFragment = TvShowsFavFragment()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tv_shows,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        madapter = TvShowsAdapter()
        mviemodelfav.getdata().observe(this, favObserver)
        mviemodelfav.getLoader().observe(this, Observer {
            Log.i("mains","data : "+it.toString())
            ViewUtils.progressConfig(spin_kits, it)
        })
        madapter.setFavTvShowsOnclickListener {
            this.moviesdetail.clear()
            moviesdetail.add(DetailItems(it.id.toString(),it.vote_average,it.title,it.vote_average,it.poster_path,it.overview,it.release_date,"tvshows"))
            Log.i("mains","data : "+it.toString())
            ctx.startActivity<DetailsActivity>("listdata" to moviesdetail)
        }
        val gridlayout = LinearLayoutManager(view.context)
        rcv_tvshows.layoutManager = gridlayout
        rcv_tvshows.adapter = madapter
    }



    private val favObserver =
        Observer<PagedList<FvTvShowEntity>> {
            Log.i("dataswa","data"+it)
            madapter.submitList(it)

        }



}
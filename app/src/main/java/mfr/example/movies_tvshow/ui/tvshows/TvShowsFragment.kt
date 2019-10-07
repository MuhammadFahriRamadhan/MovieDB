package mfr.example.movies_tvshow.ui.tvshows

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_tv_shows.*
import mfr.example.movies_tvshow.R
import mfr.example.movies_tvshow.data.source.remote.response.TvShowsItems
import mfr.example.movies_tvshow.model.DetailItems
import mfr.example.movies_tvshow.ui.details.DetailsActivity
import mfr.example.movies_tvshow.utils.ViewUtils
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.inject

class TvShowsFragment : Fragment() {

    companion object {

        fun newInstance(): TvShowsFragment = TvShowsFragment()
    }

    val madapter  : TvShowsAdapter by inject()
    val model : TvShowsViewModel by viewModel()
    val moviesdetail = ArrayList<DetailItems>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tv_shows,container,false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.getTvShowsData().observe(this, Observer {
            setdata(it)
        })

        model.getLoader().observe(this, Observer {
            Log.i("mains","data : "+it.toString())
            ViewUtils.progressConfig(spin_kits, it)
        })

        madapter.setTvShowsOnclickListener {
            this.moviesdetail.clear()
            moviesdetail.add(DetailItems(it.id,it.vote_average,it.name,it.vote_average,it.poster_path,it.overview,it.first_air_date,"tvshows"))
            ctx.startActivity<DetailsActivity>("listdata" to moviesdetail)
        }

        val gridlayout = LinearLayoutManager(view.context)
        rcv_tvshows.layoutManager = gridlayout
        rcv_tvshows.adapter = madapter

    }

    private fun setdata(data : ArrayList<TvShowsItems>) {


        madapter.AddTvShow(data)

    }


}
package mfr.example.movies_tvshow.ui.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_movies.*
import mfr.example.movies_tvshow.R
import mfr.example.movies_tvshow.data.source.remote.response.MoviesItems
import mfr.example.movies_tvshow.model.DetailItems
import mfr.example.movies_tvshow.ui.details.DetailsActivity
import mfr.example.movies_tvshow.utils.ViewUtils.progressConfig
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.inject

class MoviesFragment: Fragment() {

    val madapter  : MoviesAdapter by inject()
    val model : MoviesViewModel by viewModel()

    val moviesdetail = ArrayList<DetailItems>()

    companion object {

        fun newInstance(): MoviesFragment = MoviesFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies,container,false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        model.getMoviesData().observe(this, Observer {
            Log.i("mains","data : "+it.toString())
            setdata(it)
        })

        model.getLoader().observe(this, Observer {
            Log.i("mains","data : "+it.toString())
            progressConfig(spin_kit,it)
        })
        madapter.setMoviesOnclickListener {
            this.moviesdetail.clear()
            moviesdetail.add(DetailItems(it.id,it.vote_average,it.title,it.vote_average,it.poster_path,it.overview,it.release_date,"movies"))
            Log.i("main s","data : "+it.toString())
            ctx.startActivity<DetailsActivity>("listdata" to moviesdetail)
        }

        val gridlayout = LinearLayoutManager(view.context)
        rcv_movies.layoutManager = gridlayout
        rcv_movies.adapter = madapter

    }

    private fun setdata(data : ArrayList<MoviesItems>) {

        madapter.AddMovies(data)
    }

}
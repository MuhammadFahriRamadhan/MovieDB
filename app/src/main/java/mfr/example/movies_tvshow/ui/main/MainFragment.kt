package mfr.example.movies_tvshow.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import mfr.example.movies_tvshow.R
import mfr.example.movies_tvshow.ui.main.favorite.moviesfavorite.MoviefavFragment
import mfr.example.movies_tvshow.ui.main.favorite.tvshowsfavorite.TvShowsFavFragment

class MainFragment : Fragment() {


    companion object {

        fun newInstance(): MainFragment = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_main,container,false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addTabs(view_pager)
        tabLayouts.setupWithViewPager( view_pager )



    }

    private fun addTabs(viewPager: ViewPager) {
        val adapter = PagerAdapter(childFragmentManager)
        adapter.addfragment(MoviefavFragment(), "MOVIES")
        adapter.addfragment(TvShowsFavFragment(), "TV SHOWS")
        viewPager.adapter = adapter
    }

}

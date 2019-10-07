package mfr.example.movies_tvshow.ui.home

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*
import mfr.example.movies_tvshow.R
import mfr.example.movies_tvshow.ui.main.MainFragment
import mfr.example.movies_tvshow.ui.movies.MoviesFragment
import mfr.example.movies_tvshow.ui.tvshows.TvShowsFragment

class HomeActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val bottomNavigationView: BottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation)
        bottomNavigationView.selectedItemId = R.id.Nav_Movie



    }




    private fun setBottomNavigationFragment(fragment   : MainFragment ) {
        supportFragmentManager.beginTransaction().disallowAddToBackStack().replace(R.id.fragment_controller,fragment).commitNow()

    }
    private fun setBottomNavigationFragment(fragment   : MoviesFragment ) {
        supportFragmentManager.beginTransaction().disallowAddToBackStack().replace(R.id.fragment_controller,fragment).commitNow()

    }
    private fun setBottomNavigationFragment(fragment   : TvShowsFragment ) {
        supportFragmentManager.beginTransaction().disallowAddToBackStack().replace(R.id.fragment_controller,fragment).commitNow()

    }





    private val mOnNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {

        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.Nav_Movie -> {
                    setBottomNavigationFragment(MoviesFragment.newInstance())
                    return true
                }
                R.id.Nav_TvShows -> {
                    setBottomNavigationFragment(TvShowsFragment.newInstance())
                    return true
                }
                R.id.Nav_Fav -> {
                    setBottomNavigationFragment(MainFragment.newInstance())
                    return true
                }

            }
            return false
        }


    }







}
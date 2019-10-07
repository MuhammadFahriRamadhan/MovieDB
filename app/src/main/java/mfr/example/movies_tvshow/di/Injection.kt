package mfr.example.movies_tvshow.di

import androidx.room.Room
import mfr.example.movies_tvshow.data.source.MovieTvShowsDataSource
import mfr.example.movies_tvshow.data.source.MovieTvShowsRepository
import mfr.example.movies_tvshow.data.source.local.DataSourcelocal
import mfr.example.movies_tvshow.data.source.local.LocalRepository
import mfr.example.movies_tvshow.data.source.local.room.FavoriteRoomDatabase
import mfr.example.movies_tvshow.data.source.remote.RemoteRepository
import mfr.example.movies_tvshow.ui.details.DetailsViewModel
import mfr.example.movies_tvshow.ui.main.favorite.moviesfavorite.MoviefavViewModel
import mfr.example.movies_tvshow.ui.main.favorite.tvshowsfavorite.TvShowsFavViewModel
import mfr.example.movies_tvshow.ui.movies.MoviesAdapter
import mfr.example.movies_tvshow.ui.movies.MoviesViewModel
import mfr.example.movies_tvshow.ui.tvshows.TvShowsAdapter
import mfr.example.movies_tvshow.ui.tvshows.TvShowsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

object Injection {

    fun getModule(): Module = applicationContext {

        factory { MoviesViewModel( get())}
        factory { TvShowsFavViewModel(get()) }
        factory { TvShowsViewModel(get()) }
        factory { DetailsViewModel(get()) }
        factory { MoviefavViewModel(get()) }
        bean { MoviesAdapter(ArrayList()) }
        bean { TvShowsAdapter(ArrayList()) }



        bean { LocalRepository(get(),get()) as DataSourcelocal}
        // Room Database
        bean {
            Room.databaseBuilder(androidApplication(), FavoriteRoomDatabase::class.java, "favorite-db")
                .build()
        }

        // DAO
        bean { get<FavoriteRoomDatabase>().favoritedao() }
        bean { get<FavoriteRoomDatabase>().fvtvshowdao() }


        bean { MovieTvShowsRepository() as MovieTvShowsDataSource }

        bean { RemoteRepository()  }




    }
}
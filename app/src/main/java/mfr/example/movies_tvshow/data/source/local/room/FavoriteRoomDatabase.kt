package mfr.example.movies_tvshow.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import mfr.example.movies_tvshow.data.source.local.entity.MovieEntity.FavoriteDao
import mfr.example.movies_tvshow.data.source.local.entity.MovieEntity.FavoriteEntity
import mfr.example.movies_tvshow.data.source.local.entity.TvShowFavorite.FvTvShowDao
import mfr.example.movies_tvshow.data.source.local.entity.TvShowFavorite.FvTvShowEntity

@Database(entities = [FavoriteEntity::class,FvTvShowEntity::class],version = 1,exportSchema = false)
abstract class FavoriteRoomDatabase : RoomDatabase() {

    abstract fun favoritedao(): FavoriteDao
    abstract fun fvtvshowdao(): FvTvShowDao



}
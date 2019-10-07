package mfr.example.movies_tvshow.data.source.local.entity.MovieEntity

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface FavoriteDao {

    @Insert
    fun insert(fav: ArrayList<FavoriteEntity>)

    @Query("DELETE from FavoriteEntity WHERE id IN(:idfav) ")
    fun delete(idfav: Int)

    @Query("SELECT * from FavoriteEntity WHERE id IN(:ids) ")
    fun getAll(ids : Int): DataSource.Factory<Integer, FavoriteEntity>

    @Query("SELECT * from FavoriteEntity ORDER BY id ASC")
    fun getAllFavs(): DataSource.Factory<Integer, FavoriteEntity>
}
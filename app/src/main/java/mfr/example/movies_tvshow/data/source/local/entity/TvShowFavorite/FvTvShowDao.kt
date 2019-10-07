package mfr.example.movies_tvshow.data.source.local.entity.TvShowFavorite

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FvTvShowDao {
    @Insert
    fun insert(fav: ArrayList<FvTvShowEntity>)

    @Query("DELETE from FvTvShowEntity WHERE id IN(:idfav) ")
    fun delete(idfav: Int)

    @Query("SELECT * from FvTvShowEntity WHERE id IN(:ids) ")
    fun getAll(ids : Int): DataSource.Factory<Integer, FvTvShowEntity>

    @Query("SELECT * from FvTvShowEntity ORDER BY id ASC")
    fun getAllFavss(): DataSource.Factory<Integer, FvTvShowEntity>

}
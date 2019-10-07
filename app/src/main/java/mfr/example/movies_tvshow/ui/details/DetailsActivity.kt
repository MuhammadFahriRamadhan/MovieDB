package mfr.example.movies_tvshow.ui.details

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import kotlinx.android.synthetic.main.activity_detail.*
import mfr.example.movies_tvshow.R
import mfr.example.movies_tvshow.data.source.local.entity.MovieEntity.FavoriteEntity
import mfr.example.movies_tvshow.data.source.local.entity.TvShowFavorite.FvTvShowEntity
import mfr.example.movies_tvshow.model.DetailItems
import mfr.example.movies_tvshow.utils.ImageLoad
import org.koin.android.ext.android.inject


class DetailsActivity : AppCompatActivity() {
    val mviemodeldetails : DetailsViewModel by inject()
    var mmovieitem = ArrayList<DetailItems>()
    private var isFavorite : Boolean = false
    val favoritedata = ArrayList<FavoriteEntity>()
    val favoritetvshowdata = ArrayList<FvTvShowEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        mmovieitem = intent.getParcelableArrayListExtra("listdata")
        val datas = mmovieitem[0]
        setFavorite(isFavorite)
        mmovieitem.let {
            it[0].img_poster.let {
                image_detail.ImageLoad(it)
                backdropImageView.ImageLoad(it)


            }
            title_tv.text = it[0].tv_title
            release_date_tv.text = it[0].tv_releasedate
            plotTextView.text = it[0].overview

            ratingTextView.text = it[0].vote_average
             popularityTextView.text = it[0].vote_average

        }
        if (datas.status.equals("movies")){
            Log.i("masuk","masuksinisda")
            mviemodeldetails.getdata(mmovieitem[0].id!!.toInt()).observe(this, favObserver)
        }else{
            mviemodeldetails.getdatatvshow(mmovieitem[0].id!!.toInt()).observe(this, favtvshowObserver)
        }


        floatdetail.setOnClickListener {


                setDataFav(mmovieitem)


        }


    }


    private val favObserver =
        Observer<PagedList<FavoriteEntity>> {
            if(!it.isNullOrEmpty()){
                Log.i("datasw","data"+it)
                setFavorite(true)
                isFavorite = true


            }
        }

    private val favtvshowObserver =
        Observer<PagedList<FvTvShowEntity>> {
            if(!it.isNullOrEmpty()){
                Log.i("datasw","data"+it)
                setFavorite(true)
                isFavorite = true


            }
        }

    private fun setDataFav(data : ArrayList<DetailItems>) {
        this.favoritedata.clear()
        val datas = data[0]
        val id = datas.id!!.toInt()

        if (datas.status.equals("movies")){
            if (isFavorite){
                mviemodeldetails.deletedata(id)
                Toast.makeText(applicationContext,"Movies Removed to favorite", Toast.LENGTH_LONG).show()
                setFavorite(false)
                isFavorite = false
            }
            else {
                favoritedata.add(
                    FavoriteEntity(
                        id,
                        datas.img_poster,
                        datas.overview,
                        datas.tv_releasedate,
                        datas.tv_title,
                        datas.vote_average
                    )
                )
                mviemodeldetails.insert(favoritedata)
                Toast.makeText(applicationContext,"Movies Added to favorite", Toast.LENGTH_LONG).show()
                setFavorite(true)
                isFavorite = true
            }
        }else{
            if (isFavorite){
                mviemodeldetails.deletedatatvshow(id)
                Toast.makeText(applicationContext,"TvShows Removed to favorite", Toast.LENGTH_LONG).show()
                setFavorite(false)
                isFavorite = false
            }
            else {
                favoritetvshowdata.add(FvTvShowEntity(id,datas.img_poster,datas.overview,datas.tv_releasedate,datas.tv_title,datas.vote_average))
                mviemodeldetails.inserttvshow(favoritetvshowdata)
                Toast.makeText(applicationContext,"TvShows Added to favorite", Toast.LENGTH_LONG).show()
                setFavorite(true)
                isFavorite = true
            }
        }

    }



    private fun setFavorite(isfav : Boolean) {
        if (isfav)
        floatdetail.setImageDrawable(resources.getDrawable(R.drawable.ic_set_fav))
        else
        floatdetail.setImageDrawable(resources.getDrawable(R.drawable.ic_no_fav))
    }



}
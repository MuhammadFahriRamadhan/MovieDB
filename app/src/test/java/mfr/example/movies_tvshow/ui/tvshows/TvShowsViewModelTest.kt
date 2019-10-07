package mfr.example.movies_tvshow.ui.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mfr.example.movies_tvshow.data.source.MovieTvShowsDataSource
import mfr.example.movies_tvshow.data.source.remote.response.TvShowsItems
import mfr.example.movies_tvshow.data.source.remote.response.TvShowsResponse
import mfr.example.movies_tvshow.model.jsondummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class TvShowsViewModelTest {

    @Mock
    private lateinit var repositoryinterface: MovieTvShowsDataSource
    @Mock
    lateinit var observer : Observer<ArrayList<TvShowsItems>>
    @Mock
    private lateinit var viewmodel : TvShowsViewModel
    val data = jsondummy()
    val gson = GsonBuilder().create()
    var founderListType = object : TypeToken<TvShowsResponse>() {}.type
    val tvshows : TvShowsResponse =   gson.fromJson(data.tvshows_dummy, founderListType)


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        viewmodel = TvShowsViewModel(repositoryinterface)

    }

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun TestTvShows() {
        val expected = generateDummy()



        Mockito.`when`(repositoryinterface.getTvShows()).thenReturn(expected)

        viewmodel.getTvShowsData().observeForever(observer)
        viewmodel.getTvShowsData()

        Mockito.verify(observer).onChanged(expected.value)

        assertNotNull(viewmodel.getTvShowsData().value)
        assertNotNull(expected.value)
        assertEquals(expected.value, viewmodel.getTvShowsData().value)
    }


    fun generateDummy(): LiveData<ArrayList<TvShowsItems>> {
        val data = MutableLiveData<ArrayList<TvShowsItems>>()


        data.postValue(tvshows.results)
        return data
    }


}
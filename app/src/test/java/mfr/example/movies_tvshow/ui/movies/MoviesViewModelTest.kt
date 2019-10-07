package mfr.example.movies_tvshow.ui.movies


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mfr.example.movies_tvshow.data.source.MovieTvShowsDataSource
import mfr.example.movies_tvshow.data.source.remote.response.MovieResponse
import mfr.example.movies_tvshow.data.source.remote.response.MoviesItems
import mfr.example.movies_tvshow.model.jsondummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MoviesViewModelTest{
    @Mock
    private lateinit var repositoryinterface: MovieTvShowsDataSource
    @Mock
    lateinit var observer : Observer<ArrayList<MoviesItems>>
    @Mock
    private lateinit var viewmodel : MoviesViewModel
    val data = jsondummy()
    val gson = GsonBuilder().create()
    var founderListType = object : TypeToken<MovieResponse>() {}.type
    val moviess : MovieResponse =   gson.fromJson(data.movies_dummy, founderListType)


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        viewmodel = MoviesViewModel(repositoryinterface)

    }

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun TestMovies() {
        val expected = generateDummy()



        Mockito.`when`(repositoryinterface.getMovies()).thenReturn(expected)

        viewmodel.getMoviesData().observeForever(observer)
        viewmodel.getMoviesData()

        Mockito.verify(observer).onChanged(expected.value)

        assertNotNull(viewmodel.getMoviesData().value)
        assertNotNull(expected.value)
        assertEquals(expected.value, viewmodel.getMoviesData().value)
    }


    fun generateDummy(): LiveData<ArrayList<MoviesItems>> {
        val data = MutableLiveData<ArrayList<MoviesItems>>()


        data.postValue(moviess.results)
        return data
    }


}
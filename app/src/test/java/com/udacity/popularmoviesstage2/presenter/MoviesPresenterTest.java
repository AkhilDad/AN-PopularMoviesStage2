package com.udacity.popularmoviesstage2.presenter;

import com.udacity.popularmoviesstage2.data.MoviesDataManager;
import com.udacity.popularmoviesstage2.model.Movie;
import com.udacity.popularmoviesstage2.model.ResponseBean;
import com.udacity.popularmoviesstage2.network.ApiCalls;
import com.udacity.popularmoviesstage2.view.MoviesView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by akhil on 18/06/16.
 */
public class MoviesPresenterTest {

    private MoviesPresenter mMoviesPresenter;
    private MoviesDataManager movieDataManager;
    private MoviesView mView;
    private List<Movie> mMovieList;

    @Before
    public void setUp() throws Exception {
        mMovieList = new ArrayList<>();
        mView = Mockito.mock(MoviesView.class);
        ApiCalls apiCalls = Mockito.mock(ApiCalls.class);
        movieDataManager = Mockito.mock(MoviesDataManager.class);
        when(apiCalls.getMovies("", MoviesPresenter.POPULARITY)).thenReturn(Observable.just(new ResponseBean()));
        mMoviesPresenter = new MoviesPresenter(mView, movieDataManager);
    }

    @Test
    public void tellPresenterToLoadMovies()  {
        mMoviesPresenter.loadPopularMovies();
        verify(movieDataManager, times(1)).getMovies(eq(MoviesPresenter.POPULARITY));
    }


    @Test
    public void checkIfViewRenderIsCalled() throws Exception {
        mMoviesPresenter.loadPopularMovies();
        verify(mView,times(1)).updateMovies(mMovieList);

    }
}

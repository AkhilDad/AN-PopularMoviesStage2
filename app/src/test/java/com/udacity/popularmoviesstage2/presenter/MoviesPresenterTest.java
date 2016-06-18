package com.udacity.popularmoviesstage2.presenter;

import com.udacity.popularmoviesstage2.data.MoviesDataManager;
import com.udacity.popularmoviesstage2.view.MoviesView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by akhil on 18/06/16.
 */
public class MoviesPresenterTest {

    private MoviesPresenter mMoviesPresenter;
    private MoviesDataManager movieDataManager;

    @Before
    public void setUp() throws Exception {
        MoviesView view = Mockito.mock(MoviesView.class);
        movieDataManager = Mockito.mock(MoviesDataManager.class);
        mMoviesPresenter = new MoviesPresenter(view, movieDataManager);

    }

    @Test
    public void tellPresenterToLoadMovies()  {
        mMoviesPresenter.loadPopularMovies();
        verify(movieDataManager, times(1)).getMovies(eq(MoviesPresenter.POPULARITY));
    }
}

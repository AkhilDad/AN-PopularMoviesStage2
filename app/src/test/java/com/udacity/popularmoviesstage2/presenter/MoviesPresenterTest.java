package com.udacity.popularmoviesstage2.presenter;

import com.udacity.popularmoviesstage2.data.MoviesDataManager;
import com.udacity.popularmoviesstage2.model.Movie;
import com.udacity.popularmoviesstage2.view.MoviesView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by akhil on 18/06/16.
 */
public class MoviesPresenterTest {

    private MoviesPresenter mMoviesPresenter;

    @Mock
    private MoviesDataManager movieDataManager;

    @Mock
    private MoviesView mView;
    private List<Movie> mMovieList;
    private TestSubscriber<List<Movie>> mTestSubscriber;

    @Before
    public void setUp() throws Exception {
        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });
        MockitoAnnotations.initMocks(this);
        mMovieList = new ArrayList<>();
        mTestSubscriber = new TestSubscriber<>();
        mMoviesPresenter = new MoviesPresenter(mView, movieDataManager);
        Observable<List<Movie>> testObs = Observable.just(mMovieList);
        when(movieDataManager.getMovies(MoviesPresenter.POPULARITY)).thenReturn(testObs);
    }

    @Test
    public void tellPresenterToLoadMovies()  {

//        testObs.subscribe(mTestSubscriber);
        mMoviesPresenter.loadPopularMovies();
//        mTestSubscriber.assertNoErrors();
//        mTestSubscriber.assertReceivedOnNext(Arrays.asList(mMovieList));
        verify(movieDataManager, times(1)).getMovies(eq(MoviesPresenter.POPULARITY));
    }


    @Test
    public void checkIfViewRenderIsCalled() throws Exception {
        mMoviesPresenter.loadPopularMovies();
        verify(mView,times(1)).updateMovies(mMovieList);

    }

    @After
    public void tearDown() {
        RxAndroidPlugins.getInstance().reset();
    }
}

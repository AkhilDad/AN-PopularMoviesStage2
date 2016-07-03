package com.udacity.popularmoviesstage2.view;

import android.support.test.runner.AndroidJUnit4;

import com.udacity.popularmoviesstage2.R;
import com.udacity.popularmoviesstage2.RecyclerViewMatcher;
import com.udacity.popularmoviesstage2.data.MoviesDataManager;
import com.udacity.popularmoviesstage2.model.Movie;
import com.udacity.popularmoviesstage2.presenter.MoviesPresenter;
import com.udacity.popularmoviesstage2.rules.FragmentTestRule;
import com.udacity.popularmoviesstage2.viewmodel.MovieVM;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by akhil on 29/06/16.
 */

@RunWith(AndroidJUnit4.class)
public class MoviesFragmentTest {
    @Rule
    public FragmentTestRule fragmentTestRule = new FragmentTestRule();
    private ArrayList<MovieVM> mMovieVMs;

    private MoviesPresenter mMoviesPresenter;

    @Mock
    private MoviesDataManager movieDataManager;

    @Mock
    private MoviesView mView;

    private List<Movie> mMovieList;
    private TestSubscriber<List<Movie>> mTestSubscriber;

    @Before
    public void setup() {
//        MockitoAnnotations.initMocks(this);
        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });

        movieDataManager = mock(MoviesDataManager.class);
        mView = mock(MoviesView.class);
        mMovieList = new ArrayList<>();
        Movie movie = new Movie();
        movie.setOriginalTitle("Title1");
        movie.setTitle("Title1");
        mMovieList.add(movie);

        movie = new Movie();
        movie.setOriginalTitle("Title2");
        movie.setTitle("Title2");
        mMovieList.add(movie);

        mMovieVMs = new ArrayList<>(mMovieList.size());
        for (Movie movie1 : mMovieList) {
            mMovieVMs.add(new MovieVM(movie1));
        }

        mTestSubscriber = new TestSubscriber<>();
        mMoviesPresenter = new MoviesPresenter(mView, movieDataManager);
        Observable<List<Movie>> testObs = Observable.just(mMovieList);
        when(movieDataManager.getMovies(MoviesPresenter.POPULARITY)).thenReturn(testObs);
//        mMoviesPresenter.loadPopularMovies();
    }

    @Test
    public void renderSessionTimings(){
        MoviesFragment fragment = new MoviesFragment();
        fragmentTestRule.launch(fragment);
        onView(withId(R.id.rv_movie_list)).check(matches(isDisplayed()));
        onView(withRecyclerView(R.id.rv_movie_list).atPosition(0))
                .check(matches(hasDescendant(withText("Title1"))));
//        onView(withId(R.id.rv_movie_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    @After
    public void tearDown() {
        RxAndroidPlugins.getInstance().reset();
    }
}

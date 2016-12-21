package com.udacity.popularmoviesstage2.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.udacity.popularmoviesstage2.R;
import com.udacity.popularmoviesstage2.data.MoviesDataManager;
import com.udacity.popularmoviesstage2.databinding.FragmentMoviesBinding;
import com.udacity.popularmoviesstage2.presenter.MoviesPresenter;
import com.udacity.popularmoviesstage2.presenter.SortOrder;
import com.udacity.popularmoviesstage2.viewmodel.MovieFragmentVM;
import com.udacity.popularmoviesstage2.viewmodel.MovieVM;

import java.util.List;


public class MoviesFragment extends Fragment implements MoviesView {

    public static final int LOADING = 0;
    public static final int ERROR = 1;
    public static final int DATA_LOADED = 2;

    private static final String SORT_ORDER = "SORT_ORDER";
    private MoviesPresenter mMoviesPresenter;
    private Context mContext;
    private MovieListAdapter mMovieListAdapter;
    private FragmentMoviesBinding mBinding;
    private RecyclerView mMoviesRV;
    @SortOrder.SortOrderDef
    private String mSortOrder;

    private MoviesFragmentCallback mMoviesFragmentCallback;
    private MovieFragmentVM mMovieFragmentVM;

    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        mContext = context;
        super.onAttach(context);
        if (getActivity() instanceof MoviesFragmentCallback) {
            mMoviesFragmentCallback = (MoviesFragmentCallback) getActivity();
        } else {
            throw new UnsupportedOperationException("Movies fragment callback in not implemented by attaching activity");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (savedInstanceState != null) {
            mSortOrder = SortOrder.getSortOrder(savedInstanceState.getString(SORT_ORDER));
        }
        if (mSortOrder == null) {
            mSortOrder = SortOrder.POPULARITY;
        }
        if (getArguments() != null) {

        }
        mMovieFragmentVM = new MovieFragmentVM();
        mMoviesPresenter = new MoviesPresenter(this, new MoviesDataManager(mContext.getResources()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false);
        mBinding.rvMovieList.setLayoutManager(new GridLayoutManager(mContext, getResources().getInteger(R.integer.no_of_elements_in_grid)));
        mMovieListAdapter = new MovieListAdapter(null);
        mBinding.setMovieFragmentVM(mMovieFragmentVM);
        mBinding.rvMovieList.setAdapter(mMovieListAdapter);
        loadData(mSortOrder);
        return mBinding.getRoot();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(SORT_ORDER, mSortOrder);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMoviesPresenter.cleanUp();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_movies_fragment, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.mi_sort);
        switch (mSortOrder) {
            case SortOrder.POPULARITY:
                item.setTitle(R.string.string_rating);
                break;
            case SortOrder.RATING:
                item.setTitle(R.string.string_popularity);
                break;
        }
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.mi_sort) {
            if (item.getTitle().equals(getString(R.string.string_popularity))) {
                loadData(SortOrder.POPULARITY);
            } else {
                loadData(SortOrder.RATING);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
    }

    private void loadData(@SortOrder.SortOrderDef String sortOrder) {
        mSortOrder = sortOrder;
        switch (mSortOrder) {
            case SortOrder.POPULARITY:
                mMoviesFragmentCallback.setActionBarTitle(R.string.string_movies_by_popularity);
                break;
            case SortOrder.RATING:
                mMoviesFragmentCallback.setActionBarTitle(R.string.string_movies_by_rating);
                break;
            default:

        }
        mMoviesFragmentCallback.invalidateOptionsMenu();
        mMoviesPresenter.loadPopularMovies(sortOrder);
    }

    @Override
    public void updateMovies(List<MovieVM> movieVMList) {
        mMovieListAdapter.updateMovies(movieVMList);
    }

    @Override
    public void showError(String message) {
        mMovieFragmentVM.setErrorText(message);
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateViewState(int viewState) {
        switch (viewState) {
            case LOADING :
                mMovieFragmentVM.setErrorViewVisibility(View.GONE);
                mMovieFragmentVM.setRecyclerViewVisibility(View.GONE);
                mMovieFragmentVM.setProgressViewVisibility(View.VISIBLE);
                break;
            case ERROR:
                mMovieFragmentVM.setErrorViewVisibility(View.VISIBLE);
                mMovieFragmentVM.setRecyclerViewVisibility(View.GONE);
                mMovieFragmentVM.setProgressViewVisibility(View.GONE);
                break;
            case DATA_LOADED:
                mMovieFragmentVM.setErrorViewVisibility(View.GONE);
                mMovieFragmentVM.setRecyclerViewVisibility(View.VISIBLE);
                mMovieFragmentVM.setProgressViewVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void clearData() {
        mMovieListAdapter.clearAll();
    }

    public static MoviesFragment newInstance() {
        MoviesFragment fragment = new MoviesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public interface MoviesFragmentCallback {
        void setActionBarTitle(@StringRes int title);

        void invalidateOptionsMenu();
    }
}

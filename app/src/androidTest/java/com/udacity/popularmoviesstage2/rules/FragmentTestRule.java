package com.udacity.popularmoviesstage2.rules;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.v4.app.Fragment;

import com.udacity.popularmoviesstage2.R;
import com.udacity.popularmoviesstage2.TestFragmentContainerActivity;


public class FragmentTestRule extends IntentsTestRule<TestFragmentContainerActivity> {
  public FragmentTestRule() {
    super(TestFragmentContainerActivity.class);
  }

  public void launch(Fragment fragment){
    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.test_fragment_container, fragment).commit();
  }
}

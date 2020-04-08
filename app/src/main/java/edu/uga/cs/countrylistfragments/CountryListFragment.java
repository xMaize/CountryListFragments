package edu.uga.cs.countrylistfragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;

/**
 *
 * Fragment that represents a list of countries that the user can choose from.
 *
 * @author Truc Mai
 */
public class CountryListFragment extends ListFragment implements
        FragmentManager.OnBackStackChangedListener {

    private static final String DEBUG_TAG = "CountryListFragment";
    boolean mShowTwoFragments;
    int mCurPosition = -1;

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        //Populate our ListView control within the Fragment
        String[] countries = getResources().getStringArray(R.array.countries_array);
        setListAdapter(new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1, countries));

        // Check which state we're in
        View details = getActivity().findViewById(R.id.countryentry);
        mShowTwoFragments = details != null && details.getVisibility() == View.VISIBLE;

        //Check for when a country was previously selected
        if (savedInstanceState != null) {
            mCurPosition = savedInstanceState.getInt("curChoice", 0);
        }

        //When switched into portrait mode or an country was selected from a list, get the country's information
        if (mShowTwoFragments || mCurPosition != -1) {
            viewCountryInfo(mCurPosition);
        }
    }

    @Override
    public void onBackStackChanged() {
        Log.d(DEBUG_TAG, "VeggieGardenListFragment.onBackStackChanged()");

        // update position
        CountryDetailsFragment details = (CountryDetailsFragment) getFragmentManager()
                .findFragmentById(R.id.countryentry);
        if (details != null) {
            mCurPosition = details.getShownIndex();
            getListView().setItemChecked(mCurPosition, true);

            // if we're in single pane, then we need to switch forward to the viewer
            if (!mShowTwoFragments) {
                viewCountryInfo(mCurPosition);
            }
        }
    }

    /**
     *
     * Get a country's information given an index in the list of countries.
     *
     * @param index index of the selected country
     */
    void viewCountryInfo(int index){
        mCurPosition = index;
        if (mShowTwoFragments) {
            // Check what fragment is currently shown, replace if needed.
            CountryDetailsFragment details = (CountryDetailsFragment) getFragmentManager()
                    .findFragmentById(R.id.countryentry);
            if (details == null || details.getShownIndex() != index) {
                // Make new fragment to show this selection.
                CountryDetailsFragment newDetails = CountryDetailsFragment
                        .newInstance(index);

                // Execute a transaction, replacing any existing fragment
                // with this one inside the frame.
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.countryentry, newDetails);
                // Add this fragment instance to the back-stack of the Activity
                // so we can backtrack through our countries
                if (index != -1) {
                    String[] countries = getResources().getStringArray(
                            R.array.countries_array);
                    String strBackStackTagName = countries[index];
                    ft.addToBackStack(strBackStackTagName);
                }
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }

        } else {
            // Otherwise we need to launch a new activity to display
            // the dialog fragment with selected text.
            Log.d(DEBUG_TAG, "This is where you are.");
            Intent intent = new Intent();
            intent.setClass(getActivity(), CountryViewActivity.class);
            intent.putExtra("index", index);
            startActivity(intent);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        viewCountryInfo(position);
    }

}

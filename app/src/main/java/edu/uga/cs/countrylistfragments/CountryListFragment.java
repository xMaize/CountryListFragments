package edu.uga.cs.countrylistfragments;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.ListFragment;


public class CountryListFragment extends ListFragment implements
        FragmentManager.OnBackStackChangedListener {

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        //Populate our ListView control within the Fragment
        String[] countries = getResources().getStringArray(R.array.countries_array);
        setListAdapter(new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1, countries));

    }

    @Override
    public void onBackStackChanged() {

    }
}

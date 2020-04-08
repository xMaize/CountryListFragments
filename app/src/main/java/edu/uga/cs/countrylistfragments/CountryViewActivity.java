package edu.uga.cs.countrylistfragments;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

/**
 *
 * This represents the screen for when a country is selected from the list while the phone is in portrait mode.
 * This activity displays information about the selected country.
 *
 * @author Truc Mai
 */
public class CountryViewActivity extends AppCompatActivity {

    private static final String DEBUG_TAG = "CountryList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(DEBUG_TAG, "CountryViewActivity.onCreate()");

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // If the screen is now in landscape mode, we can show the
            // dialog in-line so we don't need this activity.
            finish();
            return;
        }

        if (savedInstanceState == null) {
            setContentView(R.layout.activity_country_fragments);

            // During initial setup, plug in the country fragment.
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            CountryDetailsFragment details = new CountryDetailsFragment();
            details.setArguments(getIntent().getExtras());

            // Replace list of countries with the selected country's info
            ft.replace(R.id.test, details);
            ft.commit();
        }

    }
}

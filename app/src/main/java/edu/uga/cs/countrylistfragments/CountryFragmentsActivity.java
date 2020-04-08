package edu.uga.cs.countrylistfragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 *
 * Represents the first screen after the splash screen. If the user is in portrait mode a list of countries will
 * be displayed, otherwise both the list and the selected country's information will be shown.
 *
 * @author Truc Mai
 */
public class CountryFragmentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_fragments);
    }
}

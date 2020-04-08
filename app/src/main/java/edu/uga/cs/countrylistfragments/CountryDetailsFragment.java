package edu.uga.cs.countrylistfragments;

import android.content.res.Resources;
import android.os.Bundle;

import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

/**
 * 
 * Class used to create fragments that represents a coutry's information.
 * 
 * @author Truc Mai
 */
public class CountryDetailsFragment extends Fragment {

    private static final String DEBUG_TAG = "CountryDetailsFragment";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_country_details, container, false);
        
        //get the index of the element that was selected
        int countryDetailsIndex = getShownIndex();
        //initialize views
        TextView countryText = view.findViewById(R.id.details);
        ImageView countryFlag = view.findViewById(R.id.countryFlag);
        ImageView countryLocation = view.findViewById(R.id.countryLocation);

        try{

            Resources res = getResources();
            InputStream in_s = null;

            //determines which country was selected and displays information and images matching that country
            if(countryDetailsIndex  == 1){
                in_s = res.openRawResource(R.raw.vietnam_info);
                countryFlag.setImageDrawable(res.getDrawable(R.drawable.vietnamflag));
                countryLocation.setImageDrawable(res.getDrawable(R.drawable.halongbay));
            }
            else if(countryDetailsIndex  == 2){
                in_s = res.openRawResource(R.raw.germany_info);
                countryFlag.setImageDrawable(res.getDrawable(R.drawable.germanyflag));
                countryLocation.setImageDrawable(res.getDrawable(R.drawable.neuschwanstein));
            }
            else if(countryDetailsIndex  == 3){
                in_s = res.openRawResource(R.raw.austria_info);
                countryFlag.setImageDrawable(res.getDrawable(R.drawable.austriaflag));
                countryLocation.setImageDrawable(res.getDrawable(R.drawable.schonbrunnpalace));
            }
            else if(countryDetailsIndex  == 4){
                in_s = res.openRawResource(R.raw.new_zealand_info);
                countryFlag.setImageDrawable(res.getDrawable(R.drawable.newzealandflag));
                countryLocation.setImageDrawable(res.getDrawable(R.drawable.milfordsound));
            }
            else{
                in_s = res.openRawResource(R.raw.japan_info);
                countryFlag.setImageDrawable(res.getDrawable(R.drawable.japanflag));
                countryLocation.setImageDrawable(res.getDrawable(R.drawable.goldenpavilion));
            }



            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            countryText.setText(new String(b));

        } catch (Exception e) {

            countryText.setText("Error: can't show info text.");

        }

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 
     * Method to create a new fragment that displays information about the selected country.
     * 
     * @param index index of the country that was selected
     * @return CountryDetailsFragment fragment that displays the details of the selected country
     */
    public static CountryDetailsFragment newInstance(int index){
        Log.v(DEBUG_TAG, "VeggieGardenWebViewFragment.newInstance: Creating new instance: " + index);
        CountryDetailsFragment fragment = new CountryDetailsFragment();

        Bundle args = new Bundle();
        args.putInt("index", index);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Method to get the index of the selected country
     * 
     * @return int index position of the country that was selected from the list
     */
    public int getShownIndex(){
        int index = -1;
        Bundle args = getArguments();
        if (args != null) {
            index = args.getInt("index", -1);
        }
        if (index == -1) {
            Log.e(DEBUG_TAG, "Not an array index.");
        }

        return index;
    }
}

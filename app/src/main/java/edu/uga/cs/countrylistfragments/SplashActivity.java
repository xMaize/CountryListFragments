package edu.uga.cs.countrylistfragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SplashActivity extends AppCompatActivity {

    private Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        start = findViewById(R.id.start);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //go to overview activity and give it information about which country the user selected
                Intent intent = new Intent(v.getContext(), CountryFragmentsActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }
}

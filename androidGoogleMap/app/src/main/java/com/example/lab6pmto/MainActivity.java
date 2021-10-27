package com.example.lab6pmto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Map<String, Double[]> buildingCoordinates = new HashMap<String, Double[]>();
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.university_buildings, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        Button btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMap(v);
            }
        });
        buildingCoordinates.put("Main", new Double[]{49.835687, 24.014437});
        buildingCoordinates.put("1", new Double[]{49.835563,24.009937});
        buildingCoordinates.put("5", new Double[]{49.835187,24.008313});
        buildingCoordinates.put("4", new Double[]{49.836437,24.011437});
        buildingCoordinates.put("7", new Double[]{49.834563,24.009813});
    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        String buildingNumber = parent.getItemAtPosition(pos).toString();
        Toast.makeText(parent.getContext(),
                "Chosen building: " + buildingNumber,
                Toast.LENGTH_SHORT).show();
        Double[] coordinates=buildingCoordinates.get(buildingNumber);
        float[]results = getDistance(coordinates[0],coordinates[1]);
        TextView distance = (TextView) findViewById(R.id.textView);
        distance.setText("Distance: "+results[0] + " m");
        intent = new Intent(getApplicationContext(), MapsActivity.class);
        intent.putExtra("coordinate1", coordinates[0]);
        intent.putExtra("coordinate2", coordinates[1]);
    }
    public void startMap(View v){
        startActivity(intent);
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public float[] getDistance(double newPosition_latitude, double newPosition_longitude){
        float[] results = new float[1];
        Location.distanceBetween(49.23, 23.81,
                newPosition_latitude, newPosition_longitude,results);
        return results;
    }

}
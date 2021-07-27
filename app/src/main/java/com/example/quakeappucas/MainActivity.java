package com.example.quakeappucas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Earthquake>  earthQuakesList = Utils.getPlacesNames();

        ArrayList<String> places= new ArrayList<>();

        for (Earthquake earthquake : earthQuakesList) {
            places.add(earthquake.getmLocation());
        }


        ListView listViewItems = findViewById(R.id.listViewItems);

//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, places);

      final   MyEarthAdapter myEarthAdapter =  new MyEarthAdapter(this,earthQuakesList);
        listViewItems.setAdapter(myEarthAdapter);

        listViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Earthquake earthquake = myEarthAdapter.getItem(position);

                Uri uri =  Uri.parse(earthquake.getmUrl());

                Intent intent= new Intent(Intent.ACTION_VIEW,uri);

                startActivity(intent);
            }
        });

    }
}
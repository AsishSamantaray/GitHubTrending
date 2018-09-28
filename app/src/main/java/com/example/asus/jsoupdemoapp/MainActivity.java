package com.example.asus.jsoupdemoapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    TextView time;
    ArrayList sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        time = findViewById(R.id.time);
        sp = new ArrayList();
        sp.add("Select");
        sp.add("Today");
        sp.add("weekly");
        sp.add("monthly");
        ArrayAdapter aa = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, sp);
        spinner.setAdapter(aa);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String t = parent.getItemAtPosition(position).toString();
                if(!t.equals("Select")) {
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("position", t);
                    startActivity(intent);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}

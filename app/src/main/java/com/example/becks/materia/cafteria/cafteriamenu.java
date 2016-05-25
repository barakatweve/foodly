package com.example.becks.materia.cafteria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.becks.materia.R;
import com.example.becks.materia.restauraunts.chipskavu;
import com.example.becks.materia.restauraunts.tambinyama;
import com.example.becks.materia.restauraunts.ugali;
import com.example.becks.materia.restauraunts.walikuku;
import com.example.becks.materia.restauraunts.walinyama;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class cafteriamenu extends AppCompatActivity {

    String jsonData;
    JSONArray jsonArray;
    JSONObject jsonObject;
    cafelistAdapter lsAdapter;
    ListView listView;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cafe_display);
        jsonData = getIntent().getExtras().getString("myjson");
        lsAdapter = new cafelistAdapter(this, R.layout.idm_items);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(lsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView txt = (TextView) view.findViewById(R.id.name);

                if (txt.getText().equals("chips kavu")){
                    startActivity(new Intent(getApplicationContext(),chipskavucafe.class));
                }

                else if (txt.getText().equals("tambi nyama")){
                    startActivity(new Intent(getApplicationContext(),tambinyamacafe.class));

                }

                else if (txt.getText().equals("wal nyama")){
                    startActivity(new Intent(getApplicationContext(),walinyamacafe.class));

                }
                else if (txt.getText().equals("wali kuku")){
                    startActivity(new Intent(getApplicationContext(),walikukucafe.class));

                }

            }


        });

        if (jsonData == null) {
            Toast.makeText(this, "null data", Toast.LENGTH_LONG).show();
        } else {

            try {

                jsonObject = new JSONObject(jsonData); // to pass json objects
                jsonArray = jsonObject.getJSONArray("result");
                // to get all jsonsdata
                int count = 0;
                String name;
                String address;
                String id;
                while (count < jsonArray.length()) {

                    JSONObject jObject = jsonArray.getJSONObject(count);

                    name = jObject.getString("name");
                    cafeGetSet co = new cafeGetSet(name);
                    lsAdapter.add(co);
                    count++;


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }}



package com.example.becks.materia.restauraunts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.becks.materia.ListAdapters.waliListAdapter;
import com.example.becks.materia.orders.OrderWali;
import com.example.becks.materia.R;

public class wali extends AppCompatActivity {
    ListView lists;
    String listItems[]={"Wali Nyama     1500/=", "Wali kuku 1/8   2000/=", "Wali kuku1/4   2500/=,", "Wali Samaki 4000/="};
    Integer listIcon[]={R.drawable.dish,R.drawable.dish,R.drawable.dish,R.drawable.dish};

    ListAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chips);
        lists= (ListView) findViewById(R.id.listView1);
        lists.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        waliListAdapter waliListAdapter=new waliListAdapter(this,listItems,listIcon);
        lists.setAdapter(waliListAdapter);
        lists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent=new Intent(wali.this,OrderWali.class);
                        startActivity(intent);
                        break;

                    case 1:
                        intent=new Intent(wali.this,OrderWali.class);
                        startActivity(intent);
                        break;

                    case 2:
                        intent=new Intent(wali.this,OrderWali.class);
                        startActivity(intent);
                        break;

                    case 3:
                        intent=new Intent(wali.this,OrderWali.class);
                        startActivity(intent);
                        break;

                }        }
        });
    }

}


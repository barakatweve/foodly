package com.example.becks.materia.restauraunts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.becks.materia.ListAdapters.pilauListAdapter;
import com.example.becks.materia.orders.OrderPilau;
import com.example.becks.materia.R;

public class pilau extends AppCompatActivity {
    ListView lists;
    String listItems[]={"Pilau Nyama    1500/=", "Pilau Kuku1/4   2500/="};
    Integer listIcon[]={R.drawable.dish,R.drawable.dish,R.drawable.dish};

    ListAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chips);
        lists= (ListView) findViewById(R.id.listView1);
        lists.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
       pilauListAdapter pilauListAdapter=new pilauListAdapter(this,listItems,listIcon);
        lists.setAdapter(pilauListAdapter);
        lists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent=new Intent(pilau.this,OrderPilau.class);
                        startActivity(intent);
                        break;

                    case 1:
                        intent=new Intent(pilau.this,OrderPilau.class);
                        startActivity(intent);
                        break;

                    case 2:
                        intent=new Intent(pilau.this,OrderPilau.class);
                        startActivity(intent);
                        break;

                    case 3:
                        intent=new Intent(pilau.this,OrderPilau.class);
                        startActivity(intent);
                        break;

                }        }
        });
    }

}

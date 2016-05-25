package com.example.becks.materia.restauraunts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.becks.materia.ListAdapters.tambiListAdapter;
import com.example.becks.materia.orders.OrderTambi;
import com.example.becks.materia.R;

public class tambi extends AppCompatActivity {
    ListView lists;
    String listItems[]={"Tambi Tupu 1500/=", "Tambi Nyama   2000/=", "Tambi kuku1/8   3000/=,", "Tambi kuku1/4  4000/="};
    Integer listIcon[]={R.drawable.dish,R.drawable.dish,R.drawable.dish,R.drawable.dish};

    ListAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chips);
        lists= (ListView) findViewById(R.id.listView1);
        lists.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        tambiListAdapter tambiListAdapter=new tambiListAdapter(this,listItems,listIcon);
        lists.setAdapter(tambiListAdapter);
        lists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent=new Intent(tambi.this,OrderTambi.class);
                        startActivity(intent);
                        break;

                    case 1:
                        intent=new Intent(tambi.this,OrderTambi.class);
                        startActivity(intent);
                        break;

                    case 2:
                        intent=new Intent(tambi.this,OrderTambi.class);
                        startActivity(intent);
                        break;

                    case 3:
                        intent=new Intent(tambi.this,OrderTambi.class);
                        startActivity(intent);
                        break;

                }        }
        });
    }

}


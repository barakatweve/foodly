package com.example.becks.materia.restauraunts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.becks.materia.ListAdapters.ugaliListAdapter;
import com.example.becks.materia.R;
import com.example.becks.materia.orders.orderUgali;

public class Ugali extends AppCompatActivity {
    ListView lists;
    String listItems[]={"Ugali Nyama    1500/=", "Ugali kuKu1/8   2500/=", "Chips kuku1/4   3000/="};
    Integer listIcon[]={R.drawable.dish,R.drawable.dish,R.drawable.dish};

    ListAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chips);
        lists= (ListView) findViewById(R.id.listView1);
        lists.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ugaliListAdapter ugaliListAdapter=new ugaliListAdapter(this,listItems,listIcon);
        lists.setAdapter(ugaliListAdapter);
        lists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent=new Intent(Ugali.this,orderUgali.class);
                        startActivity(intent);
                        break;

                    case 1:
                        intent=new Intent(Ugali.this,orderUgali.class);
                        startActivity(intent);
                        break;

                    case 2:
                        intent=new Intent(Ugali.this,orderUgali.class);
                        startActivity(intent);
                        break;



                }        }
        });
    }

}

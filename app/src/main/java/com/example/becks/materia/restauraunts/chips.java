package com.example.becks.materia.restauraunts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.becks.materia.ListAdapters.chipsListAdapter;
import com.example.becks.materia.orders.OrderChips;
import com.example.becks.materia.R;

public class chips extends AppCompatActivity {
   ListView lists;
    String listItems[]={"Chips Kavu     1500/=", "Chips Mayai   2000/=", "Chips kuku1/8   3500/=,", "Chips Mishikaki  3000/="};
    Integer listIcon[]={R.drawable.dish,R.drawable.dish,R.drawable.dish,R.drawable.dish};

    ListAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chips);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);

        lists= (ListView) findViewById(R.id.listView1);
        lists.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        chipsListAdapter chipsAdapter=new chipsListAdapter(this,listItems,listIcon);
        lists.setAdapter(chipsAdapter);
        lists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String n= (String) parent.getItemAtPosition(position);

                switch (position){
                    case 0:
                        Intent intent=new Intent(chips.this,OrderChips.class);
                        startActivity(intent);
                        break;

                    case 1:
                        intent=new Intent(chips.this,OrderChips.class);
                        startActivity(intent);
                        break;

                    case 2:
                        intent=new Intent(chips.this,wali.class);
                        startActivity(intent);
                        break;

                    case 3:
                        intent=new Intent(chips.this,wali.class);
                        startActivity(intent);
                        break;

                }        }
        });
    }

}

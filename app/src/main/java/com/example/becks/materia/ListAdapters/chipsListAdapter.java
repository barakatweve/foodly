package com.example.becks.materia.ListAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.becks.materia.R;

/**
 * Created by becks on 5/3/16.
 */
// LIst addapter to populate the data on the listview

public class chipsListAdapter extends ArrayAdapter<String> {

    String listItems[]={"Chips Kavu   1500/=", "Chips Mayai   2000/=", "Chips kuku1/8   3500/=", "Chips Mishikaki  3000/="};
    int listIcon[]={R.drawable.dish,R.drawable.dish,R.drawable.dish,R.drawable.dish};
    Context context;
    public chipsListAdapter(Context context,String[] web,Integer[] imageId) {
        super(context, R.layout.chips_items,web);
        this.context=context;
    }

    @Override
    public void add(String object) {
        super.add(object);
    }

    @Override
    public String getItem(int position) {
        return super.getItem(position);
    }
// method to dislplay the single views of the list items
    @Override
    public View getView(int position, View ConvertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // creatitn view to be inflated
       View  row=inflater.inflate(R.layout.chips_items,parent,false);
        TextView txt_fDescription= (TextView) row.findViewById(R.id.txt);
        txt_fDescription.setText(listItems[position]);
        //txt_fDescription.setText("the sectors");
        ImageView imageIcon= (ImageView) row.findViewById(R.id.img);
       // imageIcon.setImageResource(R.drawable.dish);

        imageIcon.setImageResource(listIcon[position]);


        return row;
    }

}

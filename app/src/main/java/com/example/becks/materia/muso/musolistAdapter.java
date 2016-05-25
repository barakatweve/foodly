package com.example.becks.materia.muso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.becks.materia.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by becks on 5/8/16.
 */

    public class musolistAdapter extends ArrayAdapter {
        List lists = new ArrayList();


        public musolistAdapter(Context context, int resource) {

            super(context, resource);
        }

        public void add(musoGetSet object) {
            super.add(object);
            lists.add(object);

        }

        @Override
        public Object getItem(int position) {

            return lists.get(position);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            myholder holder;
            View row;
            row=convertView;
            if(row==null){
                LayoutInflater inflater= (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row=inflater.inflate(R.layout.idm_items,parent,false);
                holder=new myholder();
                //                        holder.id= (TextView) row.findViewById(R.id.id);
                holder.txt_name= (TextView) row.findViewById(R.id.name);
                //                        holder.txt_address= (TextView) row.findViewById(R.id.address);

                row.setTag(holder);
            }

            else{
                holder= (myholder) row.getTag();
            }
            // to get the item in each posutions
            musoGetSet co= (musoGetSet) getItem(position);
            //                holder.id.setText(co.getId());
            holder.txt_name.setText(co.getName());
            //                holder.txt_address.setText(co.getAddress());

            return row;
        }

        @Override
        public int getCount() {
            return lists.size();
        }
        static class myholder{
            TextView txt_name;
            TextView txt_address;
            //  TextView id;
        }
    }


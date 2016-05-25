package com.example.becks.materia.adapters;

/**
 * Created by becks on 4/25/16.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


import com.example.becks.materia.R;
import com.example.becks.materia.cafteria.cafteria;
import com.example.becks.materia.cafteria.cafteriamenu;
import com.example.becks.materia.itemClick;
import com.example.becks.materia.muso.Muso;
import com.example.becks.materia.restaurants;
import com.example.becks.materia.IDM.Idm;
import com.example.becks.materia.setAndGet;

/**
 * Created by becks on 4/19/16.
 */
public class recAdapter2 extends RecyclerView.Adapter<ViewHolder> {
    Context context;
    ArrayList<setAndGet> mItems;
    //from the class you want to add your items
    public recAdapter2(Context context,ArrayList<setAndGet> myarray) {
        this.context = context;
        this.mItems = myarray;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //  final View.OnClickListener mOnClickListener = new MyOnClickListener();

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.restcardview, viewGroup, false);
        // to hold the view holder of the cardlayout
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        // setAndGet movie = mItems.get(i);
        viewHolder.footText2.setText(mItems.get(i).getName());
        viewHolder.imgThumbnail.setImageResource(mItems.get(i).getImage());
//        viewHolder.imgThumbnail.setImageResource(movie.getImage());

        viewHolder.setItemClickListener(new itemClick() {
            @Override
            public void getItemClicked(View v, int position) {
                final Intent intent;
                switch (position){
                    case 0:
                        intent=new Intent(context,Idm.class);
                        context.startActivity(intent);
                        break;
                    case 1:

                        intent=new Intent(context,Muso.class);
                        context.startActivity(intent);
                        break;
                    case 2:

                        intent=new Intent(context,cafteria.class);
                        context.startActivity(intent);
                        break;

                    default:
                        intent=new Intent(context,restaurants.class);
                        context.startActivity(intent);

                }
              // context.startActivity(intent);
                //Toast.makeText(context,mItems.get(position).getName(),Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public int getItemCount() {

        return mItems.size();
    }



}

package com.example.becks.materia.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.becks.materia.R;
import com.example.becks.materia.itemClick;

/**
 * Created by becks on 4/23/16.
 */
public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public ImageView imgThumbnail;
           public ImageView restLogo;
        public TextView restDescription;
    public TextView footText2;
    //        public TextView priceText;
    private Context context;

    itemClick itemsClicked;

    public ViewHolder(View itemView) {
        super(itemView);
        imgThumbnail = (ImageView) itemView.findViewById(R.id.fid);
        // addButton = (ImageView) itemView.findViewById(R.id.add);
        footText2= (TextView) itemView.findViewById(R.id.foodtype);
        restDescription = (TextView) itemView.findViewById(R.id.foodPlace);
        restLogo= (ImageView) itemView.findViewById(R.id.fid);
        // priceText= (TextView) itemView.findViewById(R.id.price);
        //itemView.setClickable(true);

        itemView.setOnClickListener(this);


    }
    // method to set the view clickable
    public void setItemClickListener(itemClick itemsClicked){
        this.itemsClicked=itemsClicked;

    }

    @Override
    public void onClick(View v) {
        this.itemsClicked.getItemClicked(v,getLayoutPosition());
    }



    }







package com.example.becks.materia.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.becks.materia.R;
import com.example.becks.materia.adapters.recAdapter;
import com.example.becks.materia.setAndGet;

import java.util.ArrayList;

/**
 * Created by becks on 4/23/16.
 */
public class Tab1Frag extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.tab1,container,false);
        // to inflate the recyclerview
        RecyclerView recyclerView= (RecyclerView) v.findViewById(R.id.rectab1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(new recAdapter(this.getActivity(),createArray()));


        return v;
    }

    private ArrayList<setAndGet> createArray() {



        setAndGet movie;
        // we name the aray to hold our items



        ArrayList<setAndGet> mItems;
        mItems=new ArrayList<>();
        movie = new setAndGet();

        movie.setName("CHIPS");

        movie.setImage(R.drawable.chips);

        mItems.add(movie);



        movie = new setAndGet();

        movie.setName("WALI");

        movie.setImage(R.drawable.wali);

        mItems.add(movie);



        movie = new setAndGet();

        movie.setName("WALI PILAU");

        movie.setImage(R.drawable.pilau);

        mItems.add(movie);

        movie = new setAndGet();

        movie.setName("TAMBI");

        movie.setImage(R.drawable.tambi2);

        mItems.add(movie);

        movie = new setAndGet();

        movie.setName("UGALI");

        movie.setImage(R.drawable.ugali);

        mItems.add(movie);

        return mItems;

    }







    @Override

    public String toString() {

        return "FastFood";

    }

}




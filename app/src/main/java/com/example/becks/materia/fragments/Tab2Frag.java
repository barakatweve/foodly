package com.example.becks.materia.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.becks.materia.R;
import com.example.becks.materia.adapters.recAdapter2;
import com.example.becks.materia.setAndGet;

import java.util.ArrayList;

/**
 * Created by becks on 4/23/16.
 */
public class Tab2Frag extends Fragment{
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View v=inflater.inflate(R.layout.tab2,container,false);
            // to inflate the recyclerview
            RecyclerView recyclerView= (RecyclerView) v.findViewById(R.id.rectab2);
            recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
            recyclerView.setAdapter(new recAdapter2(this.getActivity(),createArray()));


            return v;
        }

        private ArrayList<setAndGet> createArray() {



            setAndGet movie;
            // we name the aray to hold our items



            ArrayList<setAndGet> mItems;
            mItems=new ArrayList<>();
            movie = new setAndGet();

            movie.setName("IDM FOOD SERVICES");

            movie.setImage(R.drawable.idmtitle);

            mItems.add(movie);



            movie = new setAndGet();

            movie.setName("MUSO FOOD SERVICES");

            movie.setImage(R.drawable.muso2);

            mItems.add(movie);



            movie = new setAndGet();

            movie.setName("AKO CAFTERIA FOODS");

            movie.setImage(R.drawable.ako);

            mItems.add(movie);
            return mItems;

        }







        @Override

        public String toString() {

            return "Restaurants";

        }

}




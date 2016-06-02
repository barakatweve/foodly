package com.example.becks.materia.cafteria;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.becks.materia.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class cafteria extends AppCompatActivity {
    Context context;
    String myjresul=null;
    private String url = "http://foodly.pe.hu/api/appsripts/fetchCafe.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cafe_interface);
        getData();

    }

    public void getData() {
        class GetDataJSON extends AsyncTask<String, Void, String> {
            String line=null;
            ProgressDialog progressDialog;
            private StringBuilder sb=null;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected String doInBackground(String... params) {
                try {
                    URL urlConnection = new URL(url);
                    try {
                        HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection.openConnection();
                        InputStream is = httpURLConnection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

                        if(bufferedReader !=null){
                            sb = new StringBuilder();
                            while ((line = bufferedReader.readLine()) != null) {
                                sb.append(line + "\n");
                            }
                        }
                        else{
                            Toast.makeText(context,"no data readed",Toast.LENGTH_SHORT).show();
                        }

                        bufferedReader.close();
                        if (is!=null){
                            is.close();
                            httpURLConnection.disconnect();
                        }
                        String jsonResult=null;
                        jsonResult=sb.toString().trim();
                        return jsonResult;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                return myjresul;
            }


            @Override
            protected void onPostExecute(String result) {

                if (result !=null){

                    myjresul=result;
//progressDialog.dismiss();

                }
                else {
                    Toast.makeText(context,"network error",Toast.LENGTH_SHORT).show();
                }



            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
    }

    public void cafeDisplay(View v){
        if ( myjresul!=null){

            Intent intent=new Intent(this,cafteriamenu.class);
            intent.putExtra("myjson",myjresul);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"reload again the menu",Toast.LENGTH_LONG).show();
        }
    }


    /**
     * Created by becks on 5/6/16.
     */
    // this class for populating the items on the list view

}

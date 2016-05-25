package com.example.becks.materia;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class order extends AppCompatActivity {
    // fetching order from the server
    private String url = "http://myfirstdatabase.esy.es/fetchData.php";
    private String jsonResult;
    private JSONArray jsonArray;
    private String id;
    TextView txtname, txtlocation, txttotal, txtphone;
    private String name,phone,location,fname;
    private String s;
    private String methods;
    private String myemail;
    private int us_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txtname = (TextView) findViewById(R.id.fname);
        txtlocation = (TextView) findViewById(R.id.Location);
        txttotal = (TextView) findViewById(R.id.total);
        txtphone = (TextView) findViewById(R.id.phone);

        OrderBackground();
        readJson();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    void OrderBackground() {


        class logBg extends AsyncTask<String, Void, String> {
            // constructor to for this backgrond
            ProgressDialog pDialog;
            Context context;
            AlertDialog alertDialog;

            logBg(Context context) {
                this.context = context;
                pDialog = new ProgressDialog(context);
                pDialog.setTitle("Connecting....");
                pDialog.setMessage("PLease wait ...");
                pDialog.setIndeterminate(true);
                pDialog.setCancelable(true);
                pDialog.show();
            }

            @Override
            protected void onPreExecute() {
                alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Connecting to the Services");
            }


            @Override
            protected String doInBackground(String... params) {
                String url_login = "http://foodly.pe.hu/api/appsripts/login.php"; // for login verification


                methods = params[0];
                int userID = Integer.parseInt(params[1]);

                try {
                    URL url = new URL(url_login);
                    try {
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                        urlConnection.setRequestMethod("POST");
                        urlConnection.setDoOutput(true);
                        urlConnection.setDoInput(true);
                        OutputStream os = urlConnection.getOutputStream();
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                        String dataLogin =
                                URLEncoder.encode("userID", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(userID), "UTF-8");
                        bw.write(dataLogin);
                        bw.flush();
                        bw.close();
                        os.close();
                        // to get the response
                        InputStream inputStream = urlConnection.getInputStream();
                        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                        String line = "";
                        String response = "";
                        while ((line = br.readLine()) != null) {
                            response += line;


                        }
                        inputStream.close();
                        br.close();
                        urlConnection.disconnect();
                        return response;

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }


                return null;

            }

            @Override
            protected void onPostExecute(String result) {
                s = result.trim();
                // when the details from the php script is
                if (!s.equalsIgnoreCase("failure")) {
                    pDialog.dismiss();
                    TextView ed= (TextView) findViewById(R.id.fname);
                    //ed.setText(us_ID +" " + " " + fname+" "+ phone);
                    ed.setText(s);

                }


            }
        }
        logBg  logBack=new logBg(this);
        logBack.execute(methods, String.valueOf(us_ID));
        //  readJson();
    }





    void readJson() {
        if (s !=null) {


            // try to get json data from the server and saving them on the shared preference
            try {
                // to pass json objects
                // jsonArray = jsonObject.getJSONArray("result");
                // to get all jsonsdataint count
                //
                JSONObject jsonObject = new JSONObject(s);
                myemail = jsonObject.getString("U_ID");
                us_ID= Integer.parseInt(myemail);
                phone= jsonObject.getString("Phone");
                location = jsonObject.getString("Location");
                fname = jsonObject.getString("Fname");

            } catch (JSONException e) {
                e.printStackTrace();
            }


//        } else {
//            Toast.makeText(this, "null data", Toast.LENGTH_LONG).show();

        }
    }


}










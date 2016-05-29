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

    private String id;
    TextView txtname, txtlocation, txttotal, txtphone,txtfoname,txtquantity;
    private String name, phone, location, fname,total,foodname,quantity;
    private String s;
    private String methods;
    private String myemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txtname = (TextView) findViewById(R.id.fname);
        txtlocation = (TextView) findViewById(R.id.location);
        txttotal = (TextView) findViewById(R.id.total);
        txtphone = (TextView) findViewById(R.id.phones);
        txtfoname = (TextView) findViewById(R.id.fooname);
        txtquantity = (TextView) findViewById(R.id.quantity);


        SharedPreferences sharedPreferences=getSharedPreferences("USERS",MODE_PRIVATE);
        String test=sharedPreferences.getString("fName"," ");
        int userID=sharedPreferences.getInt("U_ID",0);

         id=String.valueOf(userID);
        //txtname.setText(test);
        OrderBackground();


    }


// the method to view the  order on  background

    void OrderBackground() {

        class logBg extends AsyncTask<String, Void, String> {
            // constructor to for this backgrond
            ProgressDialog pDialog;
            Context context;
            AlertDialog alertDialog;

            logBg(Context context) {
                this.context = context;
                pDialog = new ProgressDialog(context);
                pDialog.setTitle("Getting your orders....");
                pDialog.setMessage("PLease wait ...");
                pDialog.setIndeterminate(true);
                pDialog.setCancelable(true);
//                pDialog.show();
            }

            @Override
            protected void onPreExecute() {
                alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Connecting To Service..");
            }


            @Override

            protected String doInBackground(String... params) {
                String url_login = "http://foodly.pe.hu/api/appsripts//viewOrders.php"; // for login verification

                String methods = params[0];
                String uid = params[1];

                try {
                    URL url = new URL(url_login);
                    try {
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                        urlConnection.setRequestMethod("POST");
                        urlConnection.setDoOutput(true);
                        urlConnection.setDoInput(true);
                        OutputStream os = urlConnection.getOutputStream();
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                        String dataLogin = URLEncoder.encode("U_ID", "UTF-8") + "=" + URLEncoder.encode(uid, "UTF-8");
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
                  //  pDialog.dismiss();

                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(s);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        phone= jsonObject.getString("Phone");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        quantity = jsonObject.getString("Quantity");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        fname = jsonObject.getString("F_name");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        foodname = jsonObject.getString("Fo_name");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        location = jsonObject.getString("O_location");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        total = jsonObject.getString("Total");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    txtname.setText(fname);
                    txtphone.setText(phone);
                    txtlocation.setText(location);
                    txttotal.setText(total);
                    txtquantity.setText(quantity);
                    txtfoname.setText(foodname);



                }
                else{
                    Toast.makeText(getApplicationContext(),"already saved", Toast.LENGTH_LONG).show();
                }

             }


        }
        logBg logBack = new logBg(getApplicationContext());
        logBack.execute(methods, id);



    }

    // method to read json data from the server
//    void readJson() {
//        if (s !=null) {
//            try {
//                // to pass json objects
//                // jsonArray = jsonObject.getJSONArray("result");
//                // to get all jsonsdataint count
//                //
//
//                txttotal.setText(myemail+ phone+ fname);
//
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//
////        } else {
////            Toast.makeText(this, "null data", Toast.LENGTH_LONG).show();
//
//        }
//        else
//            Toast.makeText(getApplicationContext(),"Your order is already received", Toast.LENGTH_LONG).show();
//    }


}





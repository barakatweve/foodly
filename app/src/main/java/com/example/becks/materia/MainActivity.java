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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
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

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedpreferences;
    EditText txt_name, txt_password;
    private TextView txtRegister;
    private String password,name,method;
    private String s=null;
    private  String uID;
     private int userID;
    private String phone,fname,location;
    private String myemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        txtRegister = (TextView) findViewById(R.id.textRegister);
        txt_name = (EditText) findViewById(R.id.loginText);
        txt_password = (EditText) findViewById(R.id.logPassTxt);
        SharedPreferences sharedPreferences = getSharedPreferences(Register.UserDetails, MODE_PRIVATE);
        String sp_fname = sharedPreferences.getString("Fname", " ");
 // on register clickst
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });
        if (txt_name.equals("")) {
            txt_name.setText(sp_fname);
            setSupportActionBar(toolbar);
           ;
        }


    }

    // login method to verigy user information to database
    public void signUp(View v) {
        // to get the user details from the preference
         password = txt_password.getText().toString();
         name = txt_name.getText().toString();
      loginBackground();
        readJson();

    }


    // background process for login


    void loginBackground() {


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
                pDialog.setCancelable(false);
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

                String methods = params[0];
                String name = params[1];
                String password = params[2];

                try {
                    URL url = new URL(url_login);
                    try {
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                        urlConnection.setRequestMethod("POST");
                        urlConnection.setDoOutput(true);
                        urlConnection.setDoInput(true);
                        OutputStream os = urlConnection.getOutputStream();
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                        String dataLogin = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                                URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
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
                    TextView ed= (TextView) findViewById(R.id.text);
                    ed.setText("Welcome" +"  "+ fname+" ");
                    Intent intent = new Intent(getApplicationContext(), HomeMenu.class);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(MainActivity.this, "Invalid username or Password", Toast.LENGTH_LONG).show();
                    pDialog.dismiss();
                }


            }
        }
        logBg  logBack=new logBg(this);
        logBack.execute(method, name,password);
      //  readJson();
    }

    // trying to read json data from the server

    void readJson() {
        if (s !=null) {


            // try to get json data from the server and saving them on the shared preference
            try {
                // to pass json objects
                // jsonArray = jsonObject.getJSONArray("result");
                // to get all jsonsdataint count
                //
                JSONObject jsonObject = new JSONObject(s);
                uID = jsonObject.getString("U_ID");
                 userID= Integer.parseInt(uID);
                phone= jsonObject.getString("Phone");
                location = jsonObject.getString("Location");
                fname = jsonObject.getString("Fname");
                myemail = jsonObject.getString("Email");


                //    writing on the shared Preference file

                SharedPreferences  sharedpreferences=getSharedPreferences("USERS", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("fName",fname).toString();
                editor.putString("Location",location).toString();
                editor.putString("Phone",phone).toString();
                editor.putInt("U_ID",userID);
                editor.putString("Emails",myemail);

                // editor.putString("Location", address).toString();
                editor.commit();

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }


}







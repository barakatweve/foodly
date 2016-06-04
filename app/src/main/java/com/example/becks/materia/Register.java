package com.example.becks.materia;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.renderscript.Int4;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

public class Register extends AppCompatActivity {
    EditText txt_fname, txt_lname, txt_email, txt_phonenumber, txt_password, txt_hostelname, txt_confirmpassword;
    String email, fname, lname, password, hostelname, confirmpassword;
    String phone;
    private SharedPreferences sharedpreferences;
    static String UserDetails = "U_registration";
    private String methods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txt_fname = (EditText) findViewById(R.id.etfirstName);
        txt_lname = (EditText) findViewById(R.id.etlastName);
        txt_password = (EditText) findViewById(R.id.et_pass);
        txt_phonenumber = (EditText) findViewById(R.id.et_phone);
        txt_email = (EditText) findViewById(R.id.email);
        txt_hostelname = (EditText) findViewById(R.id.ethostelName);
        txt_confirmpassword = (EditText) findViewById(R.id.et_confirmpass);

        sharedpreferences = getSharedPreferences(UserDetails, Context.MODE_PRIVATE);


    }

    public void registerUser(View v) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        fname = txt_fname.getText().toString().trim();
        lname = txt_lname.getText().toString().trim();
        email = txt_email.getText().toString().trim();
        phone = txt_phonenumber.getText().toString().trim();
        hostelname = txt_hostelname.getText().toString().trim();
        password = txt_password.getText().toString().trim();
        confirmpassword = txt_confirmpassword.getText().toString().trim();
         methods = "register";
        // background method to take this parameters
        // if empty fields
        if ((fname.equals("")) || (email.equals("")) || (phone.equals("")||hostelname.equals(""))) {
            Toast.makeText(getApplicationContext(), "Fill all the fileds",Toast.LENGTH_LONG).show();
        }
        else if((password.length()<=8)){
            Toast.makeText(getApplicationContext(), "Password should be long", Toast.LENGTH_LONG).show();

        }
        else if((phone.length()<=9)){
            Toast.makeText(getApplicationContext(), "Phone not exists", Toast.LENGTH_LONG).show();

        }
        else if (!(password.equals(confirmpassword)))
            Toast.makeText(Register.this, "Password do not  match", Toast.LENGTH_LONG).show();

        // to validate email address



// onClick of button perform this simplest code.
        else if(!(email.matches(emailPattern)))
            Toast.makeText(getApplicationContext(),"use correct Email Adress",Toast.LENGTH_SHORT).show();

        else {
         registerUser();
        }

    }
    // background method to sent the data on the server
    public void registerUser() {
        class Background extends AsyncTask<String, Void, String> {
            // constructor to for this backgrond
            ProgressDialog pDialog;
            Context context;
            AlertDialog alertDialog;

            Background(Context context) {
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
                alertDialog.setTitle("Registering your Details....");
            }


            @Override
            protected String doInBackground(String... params) {
                String url_register = "http://foodly.pe.hu/api/appsripts/registerFoodly.php"; // url for register users information
                String url_login = "http://foodly.pe.hu/api/appsripts/login.php"; // for login verification

                String method = params[0];
// for registration and send the user information to the server
                if (method.equals("register")) {
                    String fname = params[1];
                    String lname = params[2];
                    String email = params[3];
                    String phone = params[4];
                    String hostelname = params[5];
                    String password = params[6];

                    try {
                        URL url = new URL(url_register);
                        try {
                            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                            urlConnection.setRequestMethod("POST");
                            urlConnection.setDoOutput(true);
                            OutputStream os = urlConnection.getOutputStream();
                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                            String data = URLEncoder.encode("F_name", "UTF-8") + "=" + URLEncoder.encode(fname, "UTF-8") + "&" +
                                    URLEncoder.encode("L_name", "UTF-8") + "=" + URLEncoder.encode(lname, "UTF-8") + "&" +
                                    URLEncoder.encode("Email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                                    URLEncoder.encode("Phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8") + "&" +
                                    URLEncoder.encode("Hostelname", "UTF-8") + "=" + URLEncoder.encode(hostelname, "UTF-8") + "&" +
                                    URLEncoder.encode("Password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                            bw.write(data);
                            bw.flush();
                            bw.close();
                            os.close();
                            // to get the responsivenes from the server
                            InputStream is = urlConnection.getInputStream();
                            // since there is no script in the phpscript
                            is.close();
                            return "successfully registered..";

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }

                }
                return null;

            }

            @Override
            protected void onPostExecute(String result) {
                String s = result.trim().toString();
                if (! s.equalsIgnoreCase("fail")) {
                    pDialog.dismiss();
                    Toast.makeText(context, s, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));



                } else

                    Toast.makeText(context, "User Already Exist", Toast.LENGTH_LONG).show();


            }
        }
Background bo=new Background(this);
        bo.execute(methods, fname, lname, email, phone, hostelname, password);


    }
}



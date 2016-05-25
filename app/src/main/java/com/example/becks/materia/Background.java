package com.example.becks.materia;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;
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

/**
 * Created by becks on 4/23/16.
 */

// background process for the register and login activities
public class Background extends AsyncTask<String, Void, String> {
    // constructor to for this backgrond
    ProgressDialog pDialog;
    Context context;
    AlertDialog alertDialog;
    Background(Context context){
        this.context=context;
        pDialog = new ProgressDialog(context);
        pDialog.setTitle("Connecting....");
        pDialog.setMessage("PLease wait ...");
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(false);
        pDialog.show();
    }
    @Override
    protected void onPreExecute() {
        alertDialog=new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Connecting to the Services");
    }



    @Override
    protected String doInBackground(String... params) {
        String url_register="http://foodly.pe.hu/api/appsripts/registerFoodly.php"; // url for register users information
        String url_login="http://foodly.pe.hu/api/appsripts/login.php"; // for login verification

        String method=params[0];
// for registration and send the user information to the server
        if (method.equals("register")){
            String fname=params[1];
            String lname=params[2];
            String email=params[3];
            String phone= params[4];
            String hostelname=params[5];
            String password=params[6];

            try {
                URL url=new URL(url_register);
                try {
                    HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setDoOutput(true);
                    OutputStream os=urlConnection.getOutputStream();
                    BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                    String data= URLEncoder.encode("F_name", "UTF-8") +"="+URLEncoder.encode(fname,"UTF-8")+"&" +
                            URLEncoder.encode("L_name","UTF-8") +"="+URLEncoder.encode(lname,"UTF-8")+"&"+
                            URLEncoder.encode("Email","UTF-8") +"="+URLEncoder.encode(email,"UTF-8")+"&"+
                            URLEncoder.encode("Phone","UTF-8") +"="+URLEncoder.encode(phone,"UTF-8")+"&"+
                            URLEncoder.encode("Hostelname","UTF-8") +"="+URLEncoder.encode(hostelname,"UTF-8")+"&"+
                            URLEncoder.encode("Password","UTF-8") +"="+URLEncoder.encode(password,"UTF-8");
                    bw.write(data);
                    bw.flush();
                    bw.close();
                    os.close();
                    // to get the responsivenes from the server
                    InputStream is=urlConnection.getInputStream();
                   // since there is no script in the phpscript
                    is.close();
                    return "you have successfull registerd";

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        }

        // for login purpose
        else if (method.equals("login")){

            String methods=params[0];
            String name=params[1];
            String password=params[2];

            try {
                URL url=new URL(url_login);
                try {
                    HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setDoOutput(true);
                    urlConnection.setDoInput(true);
                    OutputStream os=urlConnection.getOutputStream();
                    BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String dataLogin=    URLEncoder.encode("username","UTF-8") +"="+URLEncoder.encode(name,"UTF-8")+"&"+
                            URLEncoder.encode("password","UTF-8") +"="+URLEncoder.encode(password,"UTF-8");
                    bw.write(dataLogin);
                    bw.flush();
                    bw.close();
                    os.close();
                    // to get the response
                    InputStream inputStream=urlConnection.getInputStream();
                    BufferedReader br=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String line="";
                    String response="";
                    while ((line=br.readLine())!=null){
                        response+=line;


                    }
                    inputStream.close();
                    br.close();
                    urlConnection.disconnect();
                    return  response;

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
        String s=result.trim();

        if (s.equals("you have successfull registerd")) {
//            pDialog.dismiss();
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();


        }
        // when the details from the php script is
        else{
            if (!s.equalsIgnoreCase("failure")){
                pDialog.dismiss();
//                Intent intent=new Intent(context,HomeMenu.class);
//                context.startActivity(intent);


            }
        else{

//                Toast.makeText(context,"Invalid username or Password",Toast.LENGTH_LONG).show();
//                pDialog.dismiss();
//
 }



        }
    }
}

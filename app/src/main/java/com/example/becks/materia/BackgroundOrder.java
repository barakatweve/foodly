//package com.example.becks.materia;
//
//import android.app.AlertDialog;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.os.AsyncTask;
//import android.widget.Toast;
//
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.io.UnsupportedEncodingException;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.ProtocolException;
//import java.net.URL;
//import java.net.URLEncoder;
//
///**
// * Created by becks on 5/21/16.
// */
//public class BackgroundOrder extends AsyncTask<String, Void, String> {
//    // constructor to for this backgrond
//    ProgressDialog pDialog;
//    Context context;
//    AlertDialog alertDialog;
//    BackgroundOrder(Context context){
//        this.context=context;
//        pDialog = new ProgressDialog(context);
//        pDialog.setTitle("Connecting....");
//        pDialog.setMessage("PLease wait ...");
//        pDialog.setIndeterminate(true);
//        pDialog.setCancelable(false);
//        pDialog.show();
//    }
//    @Override
//    protected void onPreExecute() {
//        alertDialog=new AlertDialog.Builder(context).create();
//        alertDialog.setTitle("Connecting to the Services");
//    }
//
//
//
//    @Override
//    protected String doInBackground(String... params) {
//        String url_register="http://myfirstdatabase.esy.es/mydata.php"; // url for register users information
//        // String url_login="http://myfirstdatabase.esy.es/login.php"; // for login verification
//
//        String method=params[0];
//// for registration and send the user information to the server
//        if (method.equals("register")){
//            String U_ID=params[1];
//
//
//            try {
//                URL url=new URL(url_register);
//                try {
//                    HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
//                    urlConnection.setRequestMethod("POST");
//                    urlConnection.setDoOutput(true);
//                    OutputStream os=urlConnection.getOutputStream();
//                    BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
//                    String data =URLEncoder.encode("U_ID","UTF-8") +"="+URLEncoder.encode(U_ID,"UTF-8");
//                    bw.write(data);
//                    bw.flush();
//                    bw.close();
//                    os.close();
//                    // to get the responsivenes from the server
//                    InputStream is=urlConnection.getInputStream();
//                    // since there is no script in the phpscript
//                    is.close();
//                    return "YOur order for today";
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//        return null;
//
//    }
//    @Override
//    protected void onPostExecute(String result) {
//        if (result.equals("YOur order for today")) {
//            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
//            pDialog.dismiss();
//        }
//        // when the details from the php script is for the login activity
//
//    }
//}
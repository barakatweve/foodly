package com.example.becks.materia.restauraunts;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.becks.materia.HomeMenu;
import com.example.becks.materia.R;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class ugali extends AppCompatActivity {
    TextView txt_total, txt_count;
    EditText locations,phonenumbers;
    Button btn_minus, btn_adds,placeOrder;
    private EditText course;
    //Integer phonenumber=null;
    private int counter=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_chips);

        txt_total= (TextView) findViewById(R.id.theTotals);
        txt_count = (TextView) findViewById(R.id.txt_number);
        btn_adds = (Button) findViewById(R.id.plus);
        btn_minus = (Button) findViewById(R.id.minus);
        locations= (EditText) findViewById(R.id.txt_location);
        phonenumbers= (EditText) findViewById(R.id.txt_phonenumber);
        placeOrder= (Button) findViewById(R.id.btn_placeOrder);
        Toolbar toolbar= (Toolbar) findViewById(R.id.backHome);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HomeMenu.class));
            }
        });

    }



    public void Add(View v){

        txt_count.setText(String.valueOf(counter));
        txt_total.setText(String.valueOf(1800*counter));
        counter++;

    }
    public void minus(View v){
        if (counter>=1) {
            counter--;
            txt_count.setText(String.valueOf(counter));
            txt_total.setText(String.valueOf(1800*counter));
        }


    }

    public void placeOrder(View view) {

        int quantity = Integer.parseInt(txt_count.getText().toString());
        int total = Integer.parseInt(txt_total.getText().toString());
        //phonenumbers.setText(Register.phone);

        String phonenumber =phonenumbers.getText().toString().trim();
        String location = locations.getText().toString();

        if (!(location.equals("") && phonenumber.equals("")) ) {
            String message = "register";

            sendorderBackg bo = new sendorderBackg(this);

            bo.execute(message, phonenumber, location, String.valueOf(quantity), String.valueOf(total));

        }
        else{

            Toast.makeText(getApplicationContext(), "Please fill the filled above with the right details", Toast.LENGTH_LONG).show();

        }
    }

    // class for background processs on sending order to the the server
    public class sendorderBackg extends AsyncTask<String,Void,String> {
        ProgressDialog pDialog;
        Context context;
        //  AlertDialog alertDialog;
        sendorderBackg(Context context){

            this.context=context;
            pDialog = new ProgressDialog(context);
            pDialog.setIcon(R.drawable.dish);
            pDialog.setTitle("Sending your order....");
            pDialog.setMessage("PLease wait ...");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(false);
            pDialog.show();
        }
//    @Override
//    protected void onPreExecute() {
//        alertDialog=new AlertDialog.Builder(context).create();
//        alertDialog.setTitle("Submiting your order..");
//    }



        @Override
        protected String doInBackground(String... params) {
            String url_order = "http://myfirstdatabase.esy.es/ordering.php"; // url for register users information
            // for login verification

            String method = params[0];
// for  send the user order information to the server
            if (method.equals("register")) {
                String phone = params[1];
                String location = params[2];
                int quantity = Integer.parseInt(params[3]);
                int total = Integer.parseInt(params[4]);

                try {
                    URL url = new URL(url_order);
                    try {
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                        urlConnection.setRequestMethod("POST");
                        urlConnection.setDoOutput(true);
                        OutputStream os = urlConnection.getOutputStream();
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                        String data = URLEncoder.encode("phonenumber", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8") + "&" +

                                URLEncoder.encode("location","UTF-8") +"="+URLEncoder.encode(location,"UTF-8")+"&"+
                                URLEncoder.encode("quantity","UTF-8") +"="+URLEncoder.encode(String.valueOf(quantity),"UTF-8")+"&"+
                                URLEncoder.encode("total", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(total), "UTF-8");
                        bw.write(data);
                        bw.flush();
                        bw.close();
                        os.close();
                        // to get the responsivenes from the server
                        InputStream is = urlConnection.getInputStream();
                        // since there is no script in the phpscript
                        is.close();
                        return "your order is succefully submited";

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
            if (result.equals("your order is succefully submited")) {
                Toast.makeText(context, result, Toast.LENGTH_LONG).show();
                //alertDialog.setMessage(result);
                pDialog.dismiss();
                context.startActivity(new Intent(context,HomeMenu.class));
            }
        }
    }


}




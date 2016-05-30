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
        fname = txt_fname.getText().toString();
        lname = txt_lname.getText().toString();
        email = txt_email.getText().toString();
        phone = txt_phonenumber.getText().toString();
        hostelname = txt_hostelname.getText().toString();
        password = txt_password.getText().toString();
        confirmpassword = txt_confirmpassword.getText().toString();
        String methods = "register";
        // background method to take this parameters
        if (password.equals(confirmpassword) && (!(fname.equals(" ") ||(phone.equals(" "))))) {
            // to store user details on the preferences
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("Fname", fname);
            editor.putString("Phone", phone);
            editor.putString("Email", email);
            editor.putString("Hostelname", hostelname);
            editor.commit();
            //  editor.putString(Email, e);
            Background backRegister = new Background(this);
            backRegister.execute(methods, fname, lname, email, phone, hostelname, password);

            startActivity(new Intent(this, MainActivity.class));
        } else {
            if (!(password.equals(confirmpassword)))
                Toast.makeText(Register.this, "Password do not  match", Toast.LENGTH_LONG).show();
        }
    }
}



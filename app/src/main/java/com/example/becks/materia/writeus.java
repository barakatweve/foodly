package com.example.becks.materia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by becks on 5/19/16.
 */

public class writeus extends AppCompatActivity {
    EditText textAboutName,textAboutEmail,textAboutFeedBack;
    Button buttonAbout;
    String fullName,email,feedBack;

    //Emails of administrators
    String [] foodly_emails = {"beckssam48@gmail.com"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writeus);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Foodly |Give us your C");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(back);
            }
        });

        textAboutName = (EditText) findViewById(R.id.textAboutName);
        textAboutEmail = (EditText) findViewById(R.id.textAboutEmail);
        textAboutFeedBack = (EditText) findViewById(R.id.textAboutFeedBack);
        buttonAbout = (Button) findViewById(R.id.buttonAbout);

        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullName = textAboutName.getText().toString();
                email = textAboutEmail.getText().toString();
                feedBack = textAboutFeedBack.getText().toString();

                Intent feedBackIntent = new Intent(Intent.ACTION_SEND);
                feedBackIntent.setType("rfc/822");
                feedBackIntent.putExtra(Intent.EXTRA_EMAIL, foodly_emails);
                feedBackIntent.putExtra(Intent.EXTRA_SUBJECT,"Foodly email from: "+fullName);
                feedBackIntent.putExtra(Intent.EXTRA_TEXT,feedBack+"\n\n Replying Email is:" +email);
                startActivity(feedBackIntent);
            }
        });


    }

}
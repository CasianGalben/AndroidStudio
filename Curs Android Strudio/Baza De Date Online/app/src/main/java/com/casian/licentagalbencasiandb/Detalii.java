package com.casian.licentagalbencasiandb;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Detalii extends AppCompatActivity {
    TextView fnDetalii,lnDetalii,ageDetalii;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalii);

        Intent intent= getIntent();

        fnDetalii = findViewById(R.id.firstNameDetalii);
        lnDetalii = findViewById(R.id.lastNameDetalii);
        ageDetalii = findViewById(R.id.ageDetalii);

        if(intent!=null){
            setTitle(intent.getStringExtra("firstname"));
            fnDetalii.setText("First name:" +intent.getStringExtra("firstname"));
            lnDetalii.setText("Last name:" +intent.getStringExtra("lastname"));
            ageDetalii.setText("Age:" +intent.getStringExtra("age"));
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();  //pentru a inchide actiivitatea cand faci back
    }
}
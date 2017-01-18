package com.bignerbranch.android.shippingdbwithandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FinalViewActivity extends AppCompatActivity {
    TextView finalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_view);
        Intent intent = getIntent();
        finalText = (TextView) findViewById(R.id.finalText);
        String Qname = intent.getStringExtra("lname");
        finalText.setText(Qname);
    }
}

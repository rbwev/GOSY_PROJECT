package com.bignerbranch.android.shippingdbwithandroid;

import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends Activity {
    Cursor c = null;
    List<String> QuestionsList = new ArrayList<String>();
    ArrayList<String> AnswerList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Intent intent = getIntent();
        int fName = intent.getIntExtra("lname", 0);
        CreateDB(fName);
        ListView lvMain = (ListView) findViewById(R.id.lvMain);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
        R.layout.my_list_item, QuestionsList);
        lvMain.setAdapter(arrayAdapter);
            lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    CreateAnswer(position);
                }
            });
        }


    public void CreateAnswer(int a){
            String answer = AnswerList.get(a);
            Intent intent = new Intent(ViewActivity.this, FinalViewActivity.class);
            intent.putExtra("lname", answer);
            startActivity(intent);
        }



    public void CreateDB(int a) {
        DatabaseHelper myDbHelper = new DatabaseHelper(ViewActivity.this);
        try {
            myDbHelper.createDataBase(a);
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }
        c = myDbHelper.query("EMP_TABLE", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                QuestionsList.add(c.getString(2));
                AnswerList.add(c.getString(3));
            } while (c.moveToNext());
        }
    }
}
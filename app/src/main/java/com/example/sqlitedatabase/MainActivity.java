package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText text1;
    EditText text2;
    EditText text3;

    DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1= (EditText) findViewById(R.id.id);
        text2 = (EditText) findViewById(R.id.username);
        text3= (EditText) findViewById(R.id.password);

        databaseManager =  new DatabaseManager(this);
        try {
            databaseManager.open();
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    public void btnInsertPressed(View v) {
        databaseManager.insert(Integer.parseInt(text1.getText().toString()), text2.getText().toString(), text3.getText().toString());



    }

    public void btnFetchPressed(View v) {

        Cursor cursor = databaseManager.fetch();

        if (cursor.moveToFirst()) {
            do {
                String ID = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_ID));
                String username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_NAME));
                String password = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_PASSWORD));
                Log.i("DATABASE_TAG", "I have read ID : " + ID + " Username :" + username + " password :" + password);


            } while (cursor.moveToNext());
        }

    }

    public void btnUpdatePressed(View v) {

        databaseManager.update(Long.parseLong(text1.getText().toString()), text2.getText().toString(), text3.getText().toString());


    }

    public void btnDeletePressed(View v) {

        databaseManager.delete(Long.parseLong(text1.getText().toString()));
    }
}
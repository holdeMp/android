package com.example.pmtolab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserActivity extends AppCompatActivity {
    EditText ownerBox;
    EditText versionBox;
    EditText architectureBox;
    EditText shareBox;
    Button saveButton;
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor osCursor;
    long infoId=0;
    String osName="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ownerBox = findViewById(R.id.owner);
        versionBox = findViewById(R.id.version);
        architectureBox = findViewById(R.id.architecture);
        shareBox = findViewById(R.id.share);
        saveButton = findViewById(R.id.saveButton);

        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.getWritableDatabase();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            infoId = extras.getLong("id");
            osName = extras.getString("os");
        }
        if (infoId > 0) {
            // get element by id
            osCursor = db.rawQuery("select * from " + osName + " where " +
                    "_id" + "=?", new String[]{String.valueOf(infoId)});
            osCursor.moveToFirst();
            ownerBox.setText(osCursor.getString(1));
            versionBox.setText(osCursor.getString(2));
            architectureBox.setText(osCursor.getString(3));
            shareBox.setText(osCursor.getString(4));
            osCursor.close();
        }
    }
    public void save(View view) {
        ContentValues cv = new ContentValues();
        cv.put("Owner_Company", ownerBox.getText().toString());
        cv.put("Version", versionBox.getText().toString());
        cv.put("Architecture", architectureBox.getText().toString());
        cv.put("MarketShare", shareBox.getText().toString());

        if (infoId > 0) {
            db.update(osName, cv, "_id" + "=" + infoId, null);
        }
        goHome();
    }
    private void goHome(){
        // close connection
        db.close();
        // go to main activity
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}
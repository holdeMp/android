package com.example.myapplication;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView meg = new TextView(this);
        meg.setText("Hello,world Marii Bohdan KI-44");
        setContentView(meg);
    }
}


package com.example.pmtolab5;
import android.content.Context;
import android.widget.SimpleCursorAdapter;
import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.database.Cursor;
import android.content.Intent;
import android.widget.Toast;
import android.widget.AdapterView;
public class MainActivity extends AppCompatActivity {
    ListView androidList;
    ListView iosList;
    ListView windowsList;
    ListView blackberryList;
    TextView header;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor androidCursor;
    Cursor iosCursor;
    Cursor windowsCursor;
    Cursor blackberryCursor;
    SimpleCursorAdapter androidAdapter;
    SimpleCursorAdapter iosAdapter;
    SimpleCursorAdapter windowsAdapter;
    SimpleCursorAdapter blackberryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("lab5.db", MODE_PRIVATE, null);
        header = findViewById(R.id.header);

        androidList = findViewById(R.id.list);
        iosList = findViewById(R.id.listIOS);
        windowsList = findViewById(R.id.listWindows);
        blackberryList = findViewById(R.id.listBlackberry);
        androidList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("os", "Android");
                startActivity(intent);
            }
        });
        iosList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("os", "IOS");
                startActivity(intent);
            }
        });
        windowsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("os", "WindowsPhone");
                startActivity(intent);
            }
        });
        blackberryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("os", "BlackBerryOS");
                startActivity(intent);
            }
        });
        databaseHelper = new DatabaseHelper(getApplicationContext());
    }
    @Override
    public void onResume() {
        super.onResume();
        // open connection
        db = databaseHelper.getReadableDatabase();

        //get data from db in Cursor
        androidCursor =  db.rawQuery("select * from Android", null);
        iosCursor =  db.rawQuery("select * from IOS", null);
        windowsCursor =  db.rawQuery("select * from BlackBerryOS", null);
        blackberryCursor =  db.rawQuery("select * from WindowsPhone", null);
        // choose what columns will be displayed in view
        String[] headers = new String[] {"Owner_Company", "Version","Architecture","MarketShare"};
        // create adapter , pass cursor in
        androidAdapter = new SimpleCursorAdapter(this, R.layout.list_item,
                androidCursor, headers, new int[]{R.id.owner,R.id.version,R.id.architecture,R.id.share}, 0);

        // choose what columns will be displayed in view
        // create adapter , pass cursor in
        iosAdapter = new SimpleCursorAdapter(this, R.layout.list_item,
                iosCursor, headers, new int[]{R.id.owner,R.id.version,R.id.architecture,R.id.share}, 0);
        blackberryAdapter = new SimpleCursorAdapter(this, R.layout.list_item,
                blackberryCursor, headers, new int[]{R.id.owner,R.id.version,R.id.architecture,R.id.share}, 0);
        windowsAdapter = new SimpleCursorAdapter(this, R.layout.list_item,
                windowsCursor, headers, new int[]{R.id.owner,R.id.version,R.id.architecture,R.id.share}, 0);
        header.setText("Android:");
        androidList = findViewById(R.id.list);
        androidList.setAdapter(androidAdapter);
        iosList.setAdapter(iosAdapter);
        windowsList.setAdapter(windowsAdapter);
        blackberryList.setAdapter(blackberryAdapter);
        TextView headerIOS = findViewById(R.id.headerIOS);
        headerIOS.setText("IOS:");
        TextView headerWindows = findViewById(R.id.headerWindows);
        headerWindows.setText("Windows:");
        TextView headerBlackberry = findViewById(R.id.headerBlackberry);
        headerBlackberry.setText("Blackberry:");
    }
    public void Delete(View view){
        getBaseContext().deleteDatabase("lab5.db");
        Context context = getApplicationContext();
        CharSequence text = "Successfully deleted db!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        // Close connection and cursors
        db.close();
        androidCursor.close();
        iosCursor.close();
        windowsCursor.close();
        blackberryCursor.close();
    }
}
package ch.ffhs.dbexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Main extends AppCompatActivity {
    final static public String TAG = "MainMenu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addDriver(View v){
        Intent intent = new Intent(this, InsertRecord.class);
        startActivity(intent);
        Log.d(TAG, "addDriver");
    }


    public void showDrivers(View v){
        Intent intent = new Intent(this, ShowAll.class);
        startActivity(intent);
        Log.d(TAG, "showDrivers");
    }

    public void showTeam(View v){
        Intent intent = new Intent(this, ShowTeam.class);
        startActivity(intent);
        Log.d(TAG, "showTeam");
    }

    public void showResults(View v){
        Intent intent = new Intent(this, ShowAsList.class);
        startActivity(intent);
        Log.d(TAG, "showResults");
    }
}

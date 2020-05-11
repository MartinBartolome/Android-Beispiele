package ch.ffhs.esa.preferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Main extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{
    final public String TAG = "Settings";
    private static final String KEY_COUNTER = "counter";

    private int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        PreferenceManager.setDefaultValues(this, R.xml.settings, false);
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
        counter++;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            startActivity(new Intent(this, Settings.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestrorInstanceState");
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onRestart(){
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    public void onResume(){
        super.onResume();
        SharedPreferences settings = getSharedPreferences(getPackageName() + "_preferences", MODE_PRIVATE);

        String user = settings.getString("benutzer", "???");
        TextView userTextView = (TextView) findViewById(R.id.benutzerName);
        userTextView.setText(getResources().getString(R.string.user) + user);
        Log.d(TAG, getResources().getString(R.string.user) + user);

        String favoriteColor = settings.getString("lieblingsfarbe", "#ff000000");
        TextView favoriteColorTextView = (TextView) findViewById(R.id.lieblingsfarbe);
        favoriteColorTextView.setText("Lieblingsfarbe: " + favoriteColor);
        int color = Color.parseColor(favoriteColor);
        favoriteColorTextView.setTextColor(color);
        Log.d(TAG, "Lieblingsfarbe: " + favoriteColor);

        String mood = settings.getString("stimmung", "???");
        TextView moodTextView = (TextView) findViewById(R.id.stimmung);
        moodTextView.setText("Stimmung: " + mood);
        Log.d(TAG, "Stimmung: " + mood);

        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause + ");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop");
        SharedPreferences settings = getSharedPreferences(getPackageName() + "_preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(KEY_COUNTER, counter);
        editor.commit();
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.d(TAG, "onSharedPreferenceChanged");

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}

package ch.ffhs.esa.saverestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class Show extends AppCompatActivity {
    private static final String TAG = "SHOW";
    private static final String KEY_PERSIST_TXT = "persistenterText";
    private static final String KEY_COUNTER = "counter";
    private static final String NAME_MYPREF = "MY_PREFERENCES";

    private int counter = 0;
    private int counter1 = 0;
    private String persistText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        SharedPreferences settings = getSharedPreferences(NAME_MYPREF,MODE_PRIVATE);
        counter = settings.getInt(KEY_COUNTER, 0);
        persistText = settings.getString(KEY_PERSIST_TXT, getString(R.string.text_eingeben));

        final EditText editPersistText = (EditText) findViewById(R.id.persistentEditText);
        editPersistText.setText(persistText);

        final TextView introText = (TextView) findViewById(R.id.introText);
        introText.setText(getText(R.string.intro) + " " + Integer.toString(counter) + " / " + Integer.toString(counter1));
        counter++;
        counter1++;
        if(savedInstanceState != null){
            Log.d(TAG, "onCreate + ");
        }else{
            Log.d(TAG,"onCreate - ");
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show, menu);
        Log.d(TAG, "onCreateOptionsMenu");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Log.d(TAG, "onOptionsItemSelected: not implemented");
            // see Example: 5.4	Uebung: Speichern der Einstellungen
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.d(TAG,"onSaveInstanceState");
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG,"onRestrorInstanceState");
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    public void onRestart(){
        super.onRestart();
        Log.d(TAG,"onRestart");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG,"onPause");
    }

    @Override
    public void onStop(){
        super.onStop();
        SharedPreferences settings = getSharedPreferences(NAME_MYPREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(KEY_COUNTER, counter);

        final EditText editPersistText = (EditText) findViewById(R.id.persistentEditText);
        editor.putString(KEY_PERSIST_TXT, editPersistText.getText().toString());
        editor.commit();

        Log.d(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }
}

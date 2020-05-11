package ch.ffhs.dbexamplecontentprovider;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class InsertRecord extends AppCompatActivity {
    private static final String TAG = InsertRecord.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_record);

        if(savedInstanceState != null){
            Log.d(TAG, "onCreate + ");
        }else{
            Log.d(TAG, "onCreate - ");
        }
    }

    public void onClickInsertBtn(final View btn){
        long id = 0;
        final EditText name   = (EditText) findViewById(R.id.in_name);
        final EditText team   = (EditText) findViewById(R.id.in_team);
        final EditText punkte = (EditText) findViewById(R.id.in_punkte);
        final EditText siege  = (EditText) findViewById(R.id.in_siege);
        int pkt = Integer.valueOf(punkte.getText().toString());
        int sg = Integer.valueOf(siege.getText().toString());
        Log.d(TAG, "insert: pkt = " + pkt + "   siege = " + sg);


        ContentValues values = new ContentValues() ;
        values.put( "Fahrer", name.getText().toString());
        values.put( "Team", team.getText().toString());
        values.put( "Punkte", pkt);
        values.put( "Siege", sg);

        getContentResolver().insert( FormulaOneProviderDefintions.Entry.CONTENT_URI, values ) ;
    }


    public void onClickClearBtn(final View btn){
        EditText text   = (EditText) findViewById(R.id.in_name);
        text.setText("");
        text   = (EditText) findViewById(R.id.in_team);
        text.setText("");
        text   = (EditText) findViewById(R.id.in_punkte);
        text.setText("");
        text   = (EditText) findViewById(R.id.in_siege);
        text.setText("");
    }


    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart");
    }

    public void onRestart(){
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    public void onResume(){
        super.onResume();
        Log.d(TAG, "onResume");
    }

    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause");
    }

    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop");
    }


}

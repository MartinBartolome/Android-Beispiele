package ch.ffhs.dbexample_loader;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
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

        FormulaOneDatabase db = new FormulaOneDatabase(this);
        SQLiteDatabase dbConn = db.getWritableDatabase();
        try {
            SQLiteStatement stmtInsert = dbConn.compileStatement("insert into Rangliste "+
                    "(Fahrer,Team,Punkte,Siege) "+
                    "values (?, ?, ?, ?)");
            stmtInsert.bindString(1, name.getText().toString());
            stmtInsert.bindString(2, team.getText().toString());
            stmtInsert.bindLong(3, pkt);
            stmtInsert.bindLong(4, sg);


            id = stmtInsert.executeInsert();
            Log.d(TAG, "id = " + id);
        } catch (Exception e) {
            Log.d(TAG, "Exception");
        } finally {
            db.close();
        }
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

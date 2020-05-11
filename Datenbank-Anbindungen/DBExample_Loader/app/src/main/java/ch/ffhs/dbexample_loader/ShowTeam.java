package ch.ffhs.dbexample_loader;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ShowTeam extends AppCompatActivity {
    private static final String TAG = ShowAll.class.getSimpleName();
    private SQLiteDatabase dbConn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_team);

        FormulaOneDatabase db = new FormulaOneDatabase(this);
        dbConn = db.getReadableDatabase();
        try {
            String[] values = { "Fahrer", "Team", "Punkte", "Siege"};
            Cursor myCursor = dbConn.query(true, "Rangliste", values, null,
                    null, null, null, null, null);

            if (myCursor != null) {
                int fahrer = myCursor.getColumnIndexOrThrow("Fahrer");
                int team = myCursor.getColumnIndexOrThrow("Team");
                int punkte = myCursor.getColumnIndexOrThrow("Punkte");
                int siege = myCursor.getColumnIndexOrThrow("Siege");

                String result = "";
                if (myCursor.moveToFirst()) {
                    do {
                        String fahrerStr = myCursor.getString(fahrer);
                        String teamStr = myCursor.getString(team);
                        String siegeString = myCursor.getString(siege);
                        String punkteString = myCursor.getString(punkte);
                        result = result  + fahrerStr
                                + " / " + teamStr
                                +  " (" + siegeString
                                + " / " + punkteString
                                + ")\n";
                    } while (myCursor.moveToNext());
                }
                TextView showTextView = (TextView) findViewById(R.id.showTextView);
                showTextView.setText(result);
            }

        } catch (Exception e) {
            Log.d(TAG, "Exception");
        } finally {

        }

        if(savedInstanceState != null){
            Log.d(TAG, "onCreate + ");
        }else{
            Log.d(TAG, "onCreate - ");
        }
    }

    public void onSearch(View v){
        FormulaOneDatabase db = new FormulaOneDatabase(this);
        dbConn = db.getReadableDatabase();
        EditText teamName = (EditText)findViewById(R.id.edt_team);

        try {
            String[] values = { "Fahrer", "Team", "Punkte", "Siege"};
            String[] params = {teamName.getText().toString()};

            Cursor myCursor = dbConn.query(true, "Rangliste", values, "Team = ?",
                    params, null, null, null, null);
            if (myCursor != null) {
                int fahrer = myCursor.getColumnIndexOrThrow("Fahrer");
                int team = myCursor.getColumnIndexOrThrow("Team");
                int punkte = myCursor.getColumnIndexOrThrow("Punkte");
                int siege = myCursor.getColumnIndexOrThrow("Siege");

                String result = "";
                if (myCursor.moveToFirst()) {
                    do {
                        String fahrerStr = myCursor.getString(fahrer);
                        String teamStr = myCursor.getString(team);
                        String siegeString = myCursor.getString(siege);
                        String punkteString = myCursor.getString(punkte);
                        result = result  + fahrerStr
                                + " / " + teamStr
                                +  " (" + siegeString
                                + " / " + punkteString
                                + ")\n";
                    } while (myCursor.moveToNext());
                }
                TextView showTextView = (TextView) findViewById(R.id.showTextView);
                showTextView.setText(result);
            }

        } catch (Exception e) {
            Log.d(TAG, "Exception");
        } finally {

        }
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
        dbConn.close();
        super.onPause();
        Log.d(TAG, "onPause");
    }

    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop");
    }


}

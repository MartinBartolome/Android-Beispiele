package ch.ffhs.dbexamplecontentprovider;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ShowAll extends AppCompatActivity {
    private static final String TAG = ShowAll.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        try {
            String[] values = {"_id", "Fahrer", "Team", "Punkte", "Siege"};


            Cursor myCursor = getContentResolver().query(FormulaOneProviderDefintions.Entry.CONTENT_URI, values, null, null, null);

            if (myCursor != null) {
                int fahrer = myCursor.getColumnIndexOrThrow("Fahrer");
                int team = myCursor.getColumnIndexOrThrow("Team");
                int punkte = myCursor.getColumnIndexOrThrow("Punkte");
                int siege = myCursor.getColumnIndexOrThrow("Siege");
                TextView topTextView = (TextView) findViewById(R.id.topTextView);
                topTextView.setText("formulaOneDB:Rangliste\n -> Fahrer(" +
                        fahrer +  ")/Team(" +
                        team + ")  Punkte( " +
                        punkte + ")/Siege(" +
                        siege + ")");
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
        Log.d(TAG, "onResume");
    }


    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}

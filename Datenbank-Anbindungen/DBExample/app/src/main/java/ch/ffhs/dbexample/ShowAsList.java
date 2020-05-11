package ch.ffhs.dbexample;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

public class ShowAsList extends ListActivity {
    private static final String TAG = ShowAsList.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // We'll define a custom screen layout here (the one shown above), but
        // typically, you could just use the standard ListActivity layout.
        setContentView(R.layout.activity_show_as_list);

        FormulaOneDatabase db = new FormulaOneDatabase(this);
        SQLiteDatabase dbConn = db.getReadableDatabase();

        try {

            String[] values = {"Fahrer", "Team", "Punkte", "_id" };

            /** IDs im SimpleListView Layout. */
            final int[] SIMPLE_LIST_VIEW_IDS = new int[] { R.id.name_entry,
                    R.id.team_entry,
                    R.id.punkte_entry};

            Cursor myCursor = dbConn.query(true, "Rangliste", values, null,
                    null, null, null, "Punkte DESC", null);

            ListAdapter adapter = new SimpleCursorAdapter(
                    this, // Context.
                    R.layout.simple_list_item_3,
                    myCursor,                               // Pass in the cursor to bind to.
                    values,           						// Array of cursor columns to bind to.
                    SIMPLE_LIST_VIEW_IDS);  				// Parallel array of which template
            // objects to bind to those columns.

            // Bind to our new adapter.
            setListAdapter(adapter);

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

package ch.ffhs.dbexample_loader;

import android.app.Activity;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.SimpleCursorAdapter;

public class ShowAsList extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = ShowAsList.class.getSimpleName();


    // The cursor adapter for the list view
    private SimpleCursorAdapter adapter ;
    private static final String[] values = {"Punkte", "Fahrer", "Team", "_id"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_as_list);
        Log.d(TAG, "onCreate");
        try {
            final int[] SIMPLE_LIST_VIEW_IDS =  new int[] {R.id.punkte_entry, R.id.name_entry, R.id.team_entry};

            adapter = new SimpleCursorAdapter(
                    this,
                    R.layout.simple_list_item_3,
                    null,
                    values,
                    SIMPLE_LIST_VIEW_IDS, 0);

            setListAdapter(adapter);

            getLoaderManager().initLoader( 0, null, this ) ;

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

    /**
     * A loader manager callback that returns the loader.
     */
    Activity theActivity = this;

    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {

        Loader<Cursor> cursorLoader = new CursorLoader( this, null, values, null, null, null ) {
            @Override
            public Cursor loadInBackground()
            {
                Log.d(TAG, "loadInBackground");
                FormulaOneDatabase db = new FormulaOneDatabase(theActivity);
                SQLiteDatabase dbConn = db.getReadableDatabase();
                // You can use any query that returns a cursor.

                return dbConn.query( "Rangliste", values, null, null, null, null, "Punkte DESC", null );
            }

        };
        Log.d(TAG, "onCreateLoader");
        return cursorLoader;
    }

    /**
     * A loader callback that is called when the cursor is ready.
     */
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        adapter.swapCursor( cursor ) ;
        Log.d(TAG, "onLoadFinished");
    }

    /**
     * A loader callback that is called when the cursor
     * becomes invalid.
     */
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor( null ) ;
        Log.d(TAG, "onLoaderReset");
    }

}

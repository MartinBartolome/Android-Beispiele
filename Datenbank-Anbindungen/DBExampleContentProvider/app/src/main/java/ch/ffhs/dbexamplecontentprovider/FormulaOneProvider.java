package ch.ffhs.dbexamplecontentprovider;

import ch.ffhs.dbexamplecontentprovider.FormulaOneProviderDefintions.Entry;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import java.util.HashMap;

public class FormulaOneProvider extends ContentProvider {

    // Definitions for the URI matcher
    private static final int ENTRIES = 1 ;
    private static final int ENTRY   = 2 ;

    // The URI matcher and a hashmap with the columns
    // of the database table.
    private static final UriMatcher matcher;
    private static HashMap<String, String> map;

    // The database helper
    private FormulaOneDatabase formulaOne;


    /**
     * Deletes one or more rows from the database.
     */
    @Override
    public int delete(Uri uri, String where, String[] args) {

        SQLiteDatabase db = formulaOne.getWritableDatabase() ;

        int count ;
        switch( matcher.match(uri) )
        {
            case ENTRIES:
                count = db.delete( "Rangliste", where, args ) ;
                break;

            case ENTRY:
                String id = uri.getPathSegments().get(1) ;
                count = db.delete( "Rangliste", "_id =" + id + (!TextUtils.isEmpty(where) ? " AND (" + where + ')' : ""), args ) ;
                break;

            default:
                throw new IllegalArgumentException( "Unknown URI " + uri ) ;
        }

        getContext().getContentResolver().notifyChange( uri, null ) ;
        return count ;
    }



    /**
     * Returns the type of the result for a content uri.
     */
    @Override
    public String getType(Uri uri) {
        switch( matcher.match(uri) )
        {
            case ENTRIES:
                return Entry.CONTENT_TYPE ;

            case ENTRY:
                return Entry.CONTENT_ITEM_TYPE ;

            default:
                throw new IllegalArgumentException( "Unknown URI " + uri ) ;
        }
    }


    /**
     * Inserts a row into the table.
     */
    @Override
    public Uri insert(Uri uri, ContentValues content) {
        // Validate the requested uri
        if( matcher.match(uri) != ENTRIES ) {
            throw new IllegalArgumentException( "Unknown URI " + uri ) ;
        }

        ContentValues values;
        if( content != null )
        {
            values = new ContentValues( content ) ;
        } else {
            values = new ContentValues() ;
        }

        if( values.containsKey(Entry.FAHRER) == false )
        {
            values.put( Entry.FAHRER, "undefined" ) ;
        }

        SQLiteDatabase db = formulaOne.getWritableDatabase() ;
        long row = db.insert( "Rangliste", Entry.FAHRER, values ) ;
        if( row > 0 )
        {
            Uri notify = ContentUris.withAppendedId( Entry.CONTENT_URI, row );
            getContext().getContentResolver().notifyChange( notify, null ) ;
            return notify ;
        }

        throw new SQLException( "Failed to insert row into " + uri ) ;
    }


    /**
     * Sets up the helper class.
     */
    @Override
    public boolean onCreate() {
        formulaOne = new FormulaOneDatabase( getContext() ) ;
        return false;
    }


    /**
     * Executes a query on the table.
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] args, String order) {

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder() ;

        qb.setTables( "Rangliste" ) ;

        switch( matcher.match(uri) )
        {
            case ENTRIES:
                qb.setProjectionMap( map ) ;
                break;

            case ENTRY:
                qb.setProjectionMap( map ) ;
                qb.appendWhere( Entry._ID + "=" + uri.getPathSegments().get(1) ) ;
                break;

            default:
                throw new IllegalArgumentException( "Unknown URI " + uri ) ;
        }

        // If no sort order is specified use the default
        String orderBy = null ;
        if( !TextUtils.isEmpty(order) )
        {
            orderBy = order ;
        }

        // Get the database and run the query
        SQLiteDatabase db = formulaOne.getReadableDatabase();
        Cursor c = qb.query( db, projection, selection, args, null, null, orderBy ) ;

        // Tell the cursor what uri to watch, so it knows when its source data changes
        c.setNotificationUri( getContext().getContentResolver(), uri) ;
        return c ;
    }


    /**
     * Updates rows in the table.
     */
    @Override
    public int update(Uri uri, ContentValues values, String where, String[] args) {
        SQLiteDatabase db = formulaOne.getWritableDatabase() ;

        int count;
        switch( matcher.match(uri) )
        {
            case ENTRIES:
                count = db.update( "Rangliste", values, where, args ) ;
                break;

            case ENTRY:
                String id = uri.getPathSegments().get(1) ;
                count = db.update( "Rangliste", values, Entry._ID + "=" + id + (!TextUtils.isEmpty(where) ? " AND (" + where + ')' : ""), args ) ;
                break;

            default:
                throw new IllegalArgumentException( "Unknown URI " + uri ) ;
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    /**
     * Initializes the static variables.
     */
    static {
        matcher = new UriMatcher( UriMatcher.NO_MATCH ) ;
        matcher.addURI( FormulaOneProviderDefintions.AUTHORITY, "entries", ENTRIES );
        matcher.addURI( FormulaOneProviderDefintions.AUTHORITY, "entries/#", ENTRY );

        map = new HashMap<String, String>();
        map.put( Entry._ID, Entry._ID );
        map.put( Entry.FAHRER, Entry.FAHRER );
        map.put( Entry.TEAM, Entry.TEAM );
        map.put( Entry.PUNKTE, Entry.PUNKTE );
        map.put( Entry.SIEGE, Entry.SIEGE );
    }
}

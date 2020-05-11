package ch.ffhs.dbexample_loader;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FormulaOneDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "formulaOne.db";
    private static final int DATABASE_VERSION = 1;

    public FormulaOneDatabase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + "Rangliste" + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, Fahrer TEXT, Team TEXT NOT NULL, Punkte INTEGER, Siege INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "Rangliste");
        onCreate(db);
        fillDB(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "Rangliste");
        onCreate(db);
        fillDB(db);
    }

    private void fillDB(SQLiteDatabase db){

        ContentValues values = null;
        // siehe http://www.formel1.de/saison/wm-stand/2016/fahrer-wertung

        values = new ContentValues();
        values.put("Fahrer", "Hamilton");
        values.put("Team", "Mercedes");
        values.put("Punkte", 39);
        values.put("Siege", 0);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Rosberg");
        values.put("Team", "Mercedes");
        values.put("Punkte", 75);
        values.put("Siege", 3);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Ricciardo");
        values.put("Team", "Red Bull");
        values.put("Punkte", 36);
        values.put("Siege", 0);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Bottas");
        values.put("Team", "Williams");
        values.put("Punkte", 7);
        values.put("Siege", 0);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Vettel");
        values.put("Team", "Ferrari");
        values.put("Punkte", 33);
        values.put("Siege", 0);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Alonso");
        values.put("Team", "McLaren");
        values.put("Punkte", 0);
        values.put("Siege", 0);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Massa");
        values.put("Team", "Williams");
        values.put("Punkte", 22);
        values.put("Siege", 0);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Button");
        values.put("Team", "McLaren");
        values.put("Punkte", 0);
        values.put("Siege", 0);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Huelkenberg");
        values.put("Team", "Force India");
        values.put("Punkte", 6);
        values.put("Siege", 0);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Perez");
        values.put("Team", "Force India");
        values.put("Punkte", 0);
        values.put("Siege", 0);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Magnussen");
        values.put("Team", "RenaultSport");
        values.put("Punkte", 0);
        values.put("Siege", 0);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Raikkonen");
        values.put("Team", "Ferrari");
        values.put("Punkte", 28);
        values.put("Siege", 0);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Sainz");
        values.put("Team", "Toro Rosso");
        values.put("Punkte", 4);
        values.put("Siege", 0);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Grosjean");
        values.put("Team", "Haas");
        values.put("Punkte", 18);
        values.put("Siege", 0);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Kwjat");
        values.put("Team", "Red Bull");
        values.put("Punkte", 21);
        values.put("Siege", 0);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Verstappen");
        values.put("Team", "Toro Rosso");
        values.put("Punkte", 13);
        values.put("Siege", 0);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Vandoorne");
        values.put("Team", "McLaren");
        values.put("Punkte", 1);
        values.put("Siege", 0);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Palmer");
        values.put("Team", "RenaultSport");
        values.put("Punkte", 0);
        values.put("Siege", 0);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Ericsson");
        values.put("Team", "Sauber");
        values.put("Punkte", 0);
        values.put("Siege", 0);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Wehrlein");
        values.put("Team", "Manor Racing");
        values.put("Punkte", 0);
        values.put("Siege", 0);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Nasr");
        values.put("Team", "Sauber");
        values.put("Punkte", 0);
        values.put("Siege", 0);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Gutierrez");
        values.put("Team", "Haas");
        values.put("Punkte", 0);
        values.put("Siege", 0);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Haryanto");
        values.put("Team", "Manor Racing");
        values.put("Punkte", 0);
        values.put("Siege", 0);
        db.insert("Rangliste", null, values);
    }
}

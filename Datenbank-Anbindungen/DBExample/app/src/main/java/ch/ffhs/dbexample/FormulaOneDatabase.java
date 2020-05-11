package ch.ffhs.dbexample;

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
        String sql = "CREATE TABLE IF NOT EXISTS Rangliste" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Fahrer VARCHAR(50) NOT NULL, " + "" +
                "Team VARCHAR(50) NOT NULL," +
                "Punkte INTEGER, " +
                "Siege INTEGER);";

        db.execSQL(sql);
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
        // siehe http://www.formel1.de/saison/wm-stand/2017/fahrer-wertung

        values = new ContentValues();
        values.put("Fahrer", "Hamilton");
        values.put("Team", "Mercedes");
        values.put("Punkte", 73);
        values.put("Siege", 1);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Ricciardo");
        values.put("Team", "Red Bull");
        values.put("Punkte", 22);
        values.put("Siege",0);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Bottas");
        values.put("Team", "Mercedes");
        values.put("Punkte", 25);
        values.put("Siege", 63);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Vettel");
        values.put("Team", "Ferrari");
        values.put("Punkte", 86);
        values.put("Siege", 2);
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
        values.put("Punkte", 18);
        values.put("Siege", 0);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Stroll");
        values.put("Team", "Williams");
        values.put("Punkte", 0);
        values.put("Siege", 0);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Vandorne");
        values.put("Team", "McLaren");
        values.put("Punkte",0);
        values.put("Siege", 0);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Ocon");
        values.put("Team", "Force India");
        values.put("Punkte", 9);
        values.put("Siege", 0);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Perez");
        values.put("Team", "Force India");
        values.put("Punkte", 22);
        values.put("Siege", 0);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Huelkenberg");
        values.put("Team", "RenaultSport");
        values.put("Punkte", 6);
        values.put("Siege", 0);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Raikkonen");
        values.put("Team", "Ferrari");
        values.put("Punkte", 49);
        values.put("Siege",0);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Sainz");
        values.put("Team", "Toro Rosso");
        values.put("Punkte", 11);
        values.put("Siege", 0);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Magnussen");
        values.put("Team", "Haas");
        values.put("Punkte", 4);
        values.put("Siege", 0);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Verstappen");
        values.put("Team", "Red Bull");
        values.put("Punkte", 35);
        values.put("Siege", 0);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Kwajat");
        values.put("Team", "Toro Rosso");
        values.put("Punkte", 2);
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
        values.put("Team", "Sauber");
        values.put("Punkte", 0);
        values.put("Siege", 0);
        db.insert("Rangliste", null, values);

        values = new ContentValues();
        values.put("Fahrer", "Grosjean");
        values.put("Team", "Haas");
        values.put("Punkte", 4);
        values.put("Siege", 0);
        db.insert("Rangliste", null, values);

    }
}

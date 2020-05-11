package com.example.dbroomexample;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Fahrer.class}, version = 1, exportSchema = false)
public abstract class FahrerRoomDatabase extends RoomDatabase {

    public abstract FahrerDao fahrerDao();

    private static volatile FahrerRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static FahrerRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FahrerRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), FahrerRoomDatabase.class, "word_database").addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                FahrerDao dao = INSTANCE.fahrerDao();
                dao.deleteAll();

                Fahrer fahrer = new Fahrer("Hamilton", "Mercedes", 413, 11);
                dao.insert(fahrer);
                fahrer = new Fahrer("Ricciardo", "Renault", 54,0 );
                dao.insert(fahrer);
                fahrer = new Fahrer("Bottas", "Mercedes", 326, 4);
                dao.insert(fahrer);
                fahrer = new Fahrer("Vettel", "Ferrari", 240,1 );
                dao.insert(fahrer);
                fahrer = new Fahrer("Leclerc", "Ferrari", 264,2 );
                dao.insert(fahrer);
                fahrer = new Fahrer("Verstappen", "Red Bull", 278,3 );
                dao.insert(fahrer);
                fahrer = new Fahrer("Sainz", "McLaren", 96,0 );
                dao.insert(fahrer);
            });
        }
    };

}

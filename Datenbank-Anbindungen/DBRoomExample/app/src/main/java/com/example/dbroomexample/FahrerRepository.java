package com.example.dbroomexample;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class FahrerRepository {

    private FahrerDao mFahrerDao;
    private LiveData<List<Fahrer>> mAllFahres;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    FahrerRepository(Application application) {
        FahrerRoomDatabase db = FahrerRoomDatabase.getDatabase(application);
        mFahrerDao = db.fahrerDao();
        mAllFahres = mFahrerDao.getFahrer();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Fahrer>> getAllFahrers() {
        return mAllFahres;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(final Fahrer fahrer) {
        FahrerRoomDatabase.databaseWriteExecutor.execute(() -> {
            mFahrerDao.insert(fahrer);
        });
    }
}

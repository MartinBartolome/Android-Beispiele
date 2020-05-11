package com.example.dbroomexample;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class FahrerViewModel extends AndroidViewModel {
    private FahrerRepository mRepository;

    private LiveData<List<Fahrer>> mAllFahrers;

    public FahrerViewModel (Application application) {
        super(application);
        mRepository = new FahrerRepository(application);
        mAllFahrers = mRepository.getAllFahrers();
    }

    LiveData<List<Fahrer>> getAllFahrer() { return mAllFahrers; }

    public void insert(Fahrer fahrer) { mRepository.insert(fahrer); }
}

package com.skhproject.classifiedapp.ui.fragment.detail;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.skhproject.classifiedapp.application.ClassifiedApp;
import com.skhproject.classifiedapp.db.entity.Listing;
import com.skhproject.classifiedapp.repo.ListingRepo;

public class DetailViewModel extends AndroidViewModel {

    private final String TAG = "DetailViewModel";
    private LiveData<Listing> listLiveData;
    private ListingRepo repository;

    public  LiveData<Listing>getListLiveData() {
        return listLiveData;
    }

    public DetailViewModel(Application application) {
        super(application);
        ClassifiedApp classifiedApp = (ClassifiedApp) application;
        repository = classifiedApp.getRepository();
    }

    public void loadData(String id) {

        /*INFO:
         * This method will load data
         * */

        listLiveData = repository.getListingById(id);

    }

    @Override
    protected void onCleared() {
        super.onCleared();

        clearAllMembersManually();
    }

    private void clearAllMembersManually() {

        /*INFO:
         * This method is used to clear all the
         * member variables
         * */

        Log.d(TAG, "clearAllMembersManually: called");

    }

}
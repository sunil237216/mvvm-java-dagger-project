package com.example.test.mvvmsampleapp.view.ui.main;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.util.Log;


import javax.inject.Inject;

/**
 * Created by sunil.jadhav on 11/13/2018.
 */

public class MainViewModel extends ViewModel {



     @Inject
    public MainViewModel(){

    }

    private final MutableLiveData<Integer> btnclick = new MutableLiveData<>();
    public void onPhase1Click() {
        Log.d("test","phase1 click");
        btnclick.setValue(1);
    }
    public void onPhase2Click() {
        Log.d("test","phase2 click");
        btnclick.setValue(2);
    }
    public void onPhase3Click() {
        Log.d("test","phase3 click");
        btnclick.setValue(3);

    }

    public LiveData<Integer> getbtnclick() {
        return btnclick;
    }


    @Override
    protected void onCleared() {
        super.onCleared();

    }


}

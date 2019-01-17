package com.example.test.mvvmsampleapp.di;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class MvvmApp extends DaggerApplication {


    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder()
                .application(this)
                .build();
        appComponent.inject(this);


        return appComponent;
    }


}

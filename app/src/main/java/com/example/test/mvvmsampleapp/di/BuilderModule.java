package com.example.test.mvvmsampleapp.di;

import com.example.test.mvvmsampleapp.view.ui.login.LoginActivity;
import com.example.test.mvvmsampleapp.view.ui.main.MainActivity;
import com.example.test.mvvmsampleapp.viewmodel.ViewModelModule;

import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.ContributesAndroidInjector;

@Module(includes = {ViewModelModule.class})
public abstract class BuilderModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = {FragmentBuilder.class})
    abstract MainActivity bindMainActivity();

    @ActivityScoped
    @ContributesAndroidInjector()
    abstract LoginActivity bindLoginActivity();





}
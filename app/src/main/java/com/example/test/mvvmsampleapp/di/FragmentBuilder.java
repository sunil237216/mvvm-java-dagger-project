package com.example.test.mvvmsampleapp.di;

import android.support.v4.app.Fragment;

import com.example.test.mvvmsampleapp.view.ui.main.BlankFragment;
import com.example.test.mvvmsampleapp.view.ui.main.ChecklistFragment;
import com.example.test.mvvmsampleapp.view.ui.main.LandingFragment;
import com.example.test.mvvmsampleapp.view.ui.main.SummaryFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilder{

    @ContributesAndroidInjector
    abstract LandingFragment bindLandingFragment();


    @ContributesAndroidInjector
    abstract BlankFragment bindBlackFragment ();

    @ContributesAndroidInjector
    abstract ChecklistFragment bindChecklistFragment ();

    @ContributesAndroidInjector
    abstract SummaryFragment bindSummaryFragment();

}

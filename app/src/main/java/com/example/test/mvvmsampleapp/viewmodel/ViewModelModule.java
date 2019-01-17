package com.example.test.mvvmsampleapp.viewmodel;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;

import com.example.test.mvvmsampleapp.view.ui.login.LoginViewModel;
import com.example.test.mvvmsampleapp.view.ui.main.BlankViewModel;
import com.example.test.mvvmsampleapp.view.ui.main.ChecklistViewModel;
import com.example.test.mvvmsampleapp.view.ui.main.MainViewModel;
import com.example.test.mvvmsampleapp.view.ui.main.SummaryViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindUserViewModel(MainViewModel userViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel userViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(BlankViewModel.class)
    abstract ViewModel bindBlankViewModel(BlankViewModel blankViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(ChecklistViewModel.class)
    abstract ViewModel bindChecklistViewModel(ChecklistViewModel checklistViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(SummaryViewModel.class)
    abstract ViewModel bindSummaryViewModel(SummaryViewModel summaryViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(AppViewModelFactory appViewModelFactory);
}

package com.example.test.mvvmsampleapp.view.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.mvvmsampleapp.R;
import com.example.test.mvvmsampleapp.databinding.SummaryFragmentBinding;
import com.example.test.mvvmsampleapp.viewmodel.AppViewModelFactory;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.support.AndroidSupportInjection;


public class SummaryFragment extends Fragment {

    private SummaryViewModel summaryViewModel;
    private SummaryFragmentBinding binding;



    @Inject
    AppViewModelFactory appViewModelFactory;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        binding = DataBindingUtil.inflate(inflater, R.layout.summary_fragment, container, false);
        summaryViewModel = ViewModelProviders.of(this,appViewModelFactory).get(SummaryViewModel.class);
        binding.setSummary(summaryViewModel);

        return  binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        summaryViewModel.getSummaryData();
        binding.btnDate.setOnClickListener(v ->{
            summaryViewModel.onSelectFromDate(getActivity());
        });
        binding.btnTime.setOnClickListener(v ->{
            summaryViewModel.onSelectToDate(getActivity());
        });
    }

}

package com.example.test.mvvmsampleapp.view.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.mvvmsampleapp.R;
import com.example.test.mvvmsampleapp.databinding.LandingFragmentBinding;

import com.example.test.mvvmsampleapp.viewmodel.AppViewModelFactory;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.support.AndroidSupportInjection;

public class LandingFragment extends Fragment {

    private LandingFragmentBinding binding;

    @Inject
    AppViewModelFactory appViewModelFactory;

    private MainViewModel mainviewmodel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(getActivity());
        binding = DataBindingUtil.inflate(inflater, R.layout.landing_fragment, container, false);
         mainviewmodel = ViewModelProviders.of(this,appViewModelFactory).get(MainViewModel.class);
        binding.setLanding(mainviewmodel);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    binding.button1.setOnClickListener(v -> {

            ((MainActivity)getActivity()).show(1);
            ((MainActivity)getActivity()).setPhase(1);

    });

    binding.button2.setOnClickListener(v -> {
        ((MainActivity)getActivity()).show(1);
        ((MainActivity)getActivity()).setPhase(2);
    });

        binding.button3.setOnClickListener(v  ->{
            ((MainActivity)getActivity()).show(1);
            ((MainActivity)getActivity()).setPhase(3);

            }
         );

        binding.btnsummary.setOnClickListener(v ->{
            ((MainActivity)getActivity()).show(4);
        });

    }


    @Override
    public void onResume() {
        super.onResume();

    }




}

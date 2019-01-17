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
import com.example.test.mvvmsampleapp.data.MyPreferenceManager;
import com.example.test.mvvmsampleapp.databinding.BlankFragmentBinding;
import com.example.test.mvvmsampleapp.viewmodel.AppViewModelFactory;
import javax.inject.Inject;
import dagger.android.support.AndroidSupportInjection;

public class BlankFragment extends Fragment {

    private BlankFragmentBinding binding;

    @Inject
    MyPreferenceManager myPreferenceManager;

    @Inject
    AppViewModelFactory appViewModelFactory;
    private BlankViewModel blankviewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        binding = DataBindingUtil.inflate(inflater, R.layout.blank_fragment, container, false);
        blankviewModel = ViewModelProviders.of(this, appViewModelFactory).get(BlankViewModel.class);
        blankviewModel.phaseno.set("Phase "+((MainActivity)getActivity()).getPhase());
        blankviewModel.pno.set(((MainActivity)getActivity()).getPhase());
          blankviewModel.cid.set(((MainActivity)getActivity()).getTNS());
        blankviewModel.modelno.set(((MainActivity)getActivity()).getModel());
           binding.setPhase(blankviewModel);
          return binding.getRoot();
    }

    @Override

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(((MainActivity)getActivity()).getPhase() == 1)
        {
         //  ((MainActivity) getActivity()).showDialog();
            blankviewModel.getTSN(((MainActivity) getActivity()).getTNS(),myPreferenceManager.getToken());
        }
        if(((MainActivity)getActivity()).getPhase() == 2) {
           //((MainActivity) getActivity()).showDialog();
            blankviewModel.getTSN2(myPreferenceManager.getToken(),((MainActivity) getActivity()).getTNS(),((MainActivity) getActivity()).getModel());
        }


        blankviewModel.showtoast.observe(this, new android.arch.lifecycle.Observer<String>() {
            @Override
            public void onChanged(@Nullable String text) {
               // ((MainActivity) getActivity()).hideDialog();
                ((MainActivity)getActivity()).showToast(text);


                if(text.equalsIgnoreCase("Record Updated Successfully"))
                ((MainActivity)getActivity()).show(5);

            }
        });


        blankviewModel.status.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {

                if(integer == 1) {

                    binding.togglebutton.setChecked(true);
                    System.out.println(binding.togglebutton.isChecked());
                }
                else {
                    binding.btndone.setSelected(true);
                    System.out.println(binding.togglebutton.isChecked());
                }
            }
        });


        // TODO: Use the ViewModel
    }

}

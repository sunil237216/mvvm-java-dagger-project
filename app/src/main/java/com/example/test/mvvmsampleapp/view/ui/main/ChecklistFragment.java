package com.example.test.mvvmsampleapp.view.ui.main;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.test.mvvmsampleapp.R;
import com.example.test.mvvmsampleapp.databinding.ChecklistFragmentBinding;
import com.example.test.mvvmsampleapp.service.model.Phase3Response;
import com.example.test.mvvmsampleapp.service.model.StatusResponse;
import com.example.test.mvvmsampleapp.view.adapter.CheckListAdapter;
import com.example.test.mvvmsampleapp.viewmodel.AppViewModelFactory;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import dagger.android.support.AndroidSupportInjection;

public class ChecklistFragment extends Fragment {
    private ChecklistViewModel mViewModel;
    private List<Phase3Response.CheckListsBean> phase3Response = new ArrayList<>();
    private RecyclerView recyclerView;
    private CheckListAdapter mAdapter;
    private ChecklistFragmentBinding binding;
    public Set<StatusResponse.CheckListDataBean> CheckListData=new HashSet<>();



    @Inject
    AppViewModelFactory appViewModelFactory;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        binding= DataBindingUtil.inflate(inflater, R.layout.checklist_fragment, container, false);
        binding.setChecklistmodel(mViewModel);
          CheckListData =new HashSet<>();
        return binding.getRoot();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this,appViewModelFactory).get(ChecklistViewModel.class);
        recyclerView = binding.getRoot().findViewById(R.id.recycler_view);

        if(((MainActivity)getActivity()).getPhase() ==3) {

           ((MainActivity) getActivity()).showDialog();
            mViewModel.getTSN2(((MainActivity) getActivity()).getTNS(),((MainActivity) getActivity()).getModel());
        }
        // TODO: Use the ViewModel

        mViewModel.phase3Reposnse.observe(this, new Observer<Phase3Response>() {
            @Override
            public void onChanged(@Nullable Phase3Response response) {

                if(response != null) {
                    binding.btnSubmit.setVisibility(View.VISIBLE);

                    if(response.getPhase1Status() == 1)
                    {
                        binding.txtStatus1.setText("Phase 1 Status Cleared");

                    }
                    else if(response.getPhase1Status() == 2) {
                        binding.txtStatus1.setText("Phase 1 Status not cleared");
                    }
                    if(response.getPhase2Status() == 1)
                    {
                        binding.txtStatus2.setText("Phase 2 Status Cleared");
                    }
                    else if(response.getPhase2Status() == 2) {
                        binding.txtStatus2.setText("Phase 2 Status not cleared");
                    }
                    binding.txtError.setVisibility(View.GONE);
                    phase3Response = response.getCheckLists();
                    ((MainActivity) getActivity()).hideDialog();
                    mAdapter = new CheckListAdapter(getActivity(), phase3Response);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.setOnClickListener(onClickInterface);
                    mAdapter.notifyDataSetChanged();
                }
                else{
                    ((MainActivity) getActivity()).hideDialog();
                    binding.btnSubmit.setVisibility(View.GONE);
                    binding.txtError.setVisibility(View.VISIBLE);

                    //MainActivity)
                }
            }
        });

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatusResponse status = new StatusResponse();
                status.setTSN(((MainActivity) getActivity()).getTNS());
                status.setModelNumber(((MainActivity) getActivity()).getModel());
                status.setCheckListData(CheckListData);
                if(CheckListData.size() ==  phase3Response.size()) {
                    mViewModel.onSubmitClick(status);
                }
                else {

                    ((MainActivity) getActivity()).showToast("Please check all list");
                }
            }
        });

        mViewModel.showtoast.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if(aBoolean)
              ((MainActivity) getActivity()).showToast("Submitted Successfully");
            }
        });
    }

    CheckListAdapter.onClickInterface onClickInterface=new CheckListAdapter.onClickInterface() {

        @Override
        public void onItemClickRec(int position, int value,String remark,int id) {

            StatusResponse.CheckListDataBean obj=new StatusResponse.CheckListDataBean();
            if(value > 0 && remark !=null && !remark.equalsIgnoreCase("Select Remark")) {
                obj.setStatus(value);
                obj.setCheckListId(id);
                obj.setRemark(remark);
                CheckListData.add(obj);
            }

        }

    };


}

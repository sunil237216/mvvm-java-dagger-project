package com.example.test.mvvmsampleapp.view.ui.main;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.ToggleButton;

import com.example.test.mvvmsampleapp.R;
import com.example.test.mvvmsampleapp.data.MyPreferenceManager;
import com.example.test.mvvmsampleapp.service.model.TsnRequest;
import com.example.test.mvvmsampleapp.service.model.TsnResponse;
import com.example.test.mvvmsampleapp.service.network.ApiClient;
import javax.inject.Inject;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class BlankViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private ApiClient apiClient;

    public MutableLiveData<Integer> status = new MutableLiveData<>();
    public ObservableField<String> remark = new ObservableField<String>();
    public ObservableField<String> phaseno = new ObservableField<String>();
    public ObservableField<Integer> pno = new ObservableField<Integer>();
    public ObservableField<String> phasestatus = new ObservableField<>();
    public ObservableField<String> cid = new ObservableField<String>();
    public ObservableField<String> modelno = new ObservableField<String>();
    public MutableLiveData<String> showtoast = new MutableLiveData<String>();
    private int tsn;

    @Inject
    MyPreferenceManager myPreferenceManager;

    @Inject
    public BlankViewModel(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public void getTSN(String tsn,String token) {
        apiClient.getTsn(tsn,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<TsnRequest>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(TsnRequest object) {

                status.setValue(object.getStatus());
                remark.set(object.getRemark());
            }

            @Override
            public void onError(Throwable e) {
                status.setValue(0);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void getTSN2(String token,String tsn,String modelid) {
        apiClient.getTsn2(token,tsn,modelid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<TsnResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(TsnResponse response) {

                if(response.getPhase1Status() == 2)
                {
                    phasestatus.set("Phase 1 Status not cleared");
                    //statuscolor.set(R.color.colorPrimary);
                }
                else if(response.getPhase1Status() == 1)
                {
                    phasestatus.set("Phase 1 cleared");
                   // statuscolor.set(R.color.colorPrimary);
                }
                status.setValue(response.getStatus());

            }

            @Override
            public void onError(Throwable e) {
                status.setValue(0);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void onDoneClick() {

        if(tsn == 0) {
            tsn = status.getValue();

        }

        if(tsn == 2 && remark.get() == null)
        {
             showtoast.setValue("Please add remark for not ok");
        }
         else {
            if (pno.get() == 1) {

                if (tsn != 0) {


                    apiClient.sendStatus(new TsnRequest(cid.get(), remark.get(), tsn, ""), myPreferenceManager.getToken()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ResponseBody>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ResponseBody response) {

                            if (response != null) {
                                showtoast.setValue("Record Updated Successfully");

                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
                } else {
                    // showtoast.setValue(true);
                    showtoast.setValue("Please Select Status");
                }
            }

            if (pno.get() == 2) {
                if (tsn != 0) {
                    apiClient.sendStatus2(new TsnRequest(cid.get(), remark.get(), tsn, modelno.get()), myPreferenceManager.getToken()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ResponseBody>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ResponseBody responseBody) {

                            if (responseBody != null)
                                showtoast.setValue("Record Updated Successfully");

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
                } else {
                    // showtoast.setValue(true);
                }
            }
        }

    }

    public void onNotokClick() {

        tsn = 2;
    }

    public void onOkClick() {

        tsn = 1;
    }

    public void onClick(View view)
    {
        boolean on = ((ToggleButton) view).isChecked();
        if (on) {
            tsn = 1;
        } else {
            tsn = 2;
        }

    }




}

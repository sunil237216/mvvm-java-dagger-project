package com.example.test.mvvmsampleapp.view.ui.main;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.util.Log;
import com.example.test.mvvmsampleapp.data.MyPreferenceManager;
import com.example.test.mvvmsampleapp.service.model.Phase3Response;
import com.example.test.mvvmsampleapp.service.model.StatusResponse;

import com.example.test.mvvmsampleapp.service.network.ApiClient;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class ChecklistViewModel extends ViewModel {
    private ApiClient apiClient;
    public MutableLiveData<String> selected;

    @Inject
    MyPreferenceManager myPreferenceManager;

    public MutableLiveData<Phase3Response> phase3Reposnse = new MutableLiveData<>();

    public MutableLiveData<Boolean> showtoast = new MutableLiveData<>();
    public ObservableField<String> phasestatus1=new ObservableField<>();
    public ObservableField<String> phasestatus2=new ObservableField<>();

    @Inject
    public ChecklistViewModel(ApiClient apiClient) {
        this.apiClient = apiClient;
    }


    public void getTSN2(String tsn,String modelid) {
        apiClient.getApiService().getTsn3(myPreferenceManager.getToken(),tsn,modelid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Phase3Response>() {
            @Override
            public void onSubscribe(Disposable d) {

            }
            @Override
            public void onNext(Phase3Response response) {

                phase3Reposnse.setValue(response);

                if(response.getPhase1Status() == 1)
                {
                   phasestatus1.set("Phase 1 Status Cleared");

                }
                else if(response.getPhase1Status() == 2) {
                    phasestatus1.set("Phase 1 Status notcleared");
                }
                if(response.getPhase2Status() == 1)
                {
                    phasestatus2.set("Phase 2 Status Cleared");
                }
                else if(response.getPhase2Status() == 2) {
                    phasestatus2.set("Phase 2 Status notcleared");
                }
            }

            @Override
            public void onError(Throwable e) {
                phase3Reposnse.setValue(null);
            }

            @Override
            public void onComplete() {

            }
        });

    }

    public void onSubmitClick(StatusResponse statusrequest)
    {

       apiClient.getApiService().sendListStatus(myPreferenceManager.getToken(),statusrequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ResponseBody>() {
           @Override
           public void onSubscribe(Disposable d) {

           }

           @Override
           public void onNext(ResponseBody responseBody) {
                   showtoast.setValue(true);

           }

           @Override
           public void onError(Throwable e) {
               showtoast.setValue(false);
           }

           @Override
           public void onComplete() {

           }
       });

    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}

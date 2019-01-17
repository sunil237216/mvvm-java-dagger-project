package com.example.test.mvvmsampleapp.view.ui.main;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.ObservableField;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.test.mvvmsampleapp.data.MyPreferenceManager;
import com.example.test.mvvmsampleapp.service.model.SummaryRequest;
import com.example.test.mvvmsampleapp.service.model.SummaryResponse;
import com.example.test.mvvmsampleapp.service.network.ApiClient;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class SummaryViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private ApiClient apiClient;

    @Inject
    MyPreferenceManager myPreferenceManager;
    private int mYear, mMonth, mDay, mHour, mMinute;
    public ObservableField<String> toDate = new ObservableField<String>();
    public ObservableField<String> fromDate = new ObservableField<String>();
    public ObservableField<String> TotalCount1 = new ObservableField<String>();
    public ObservableField<String> NotComplianceCount1 = new ObservableField<String>();
    public ObservableField<String> ComplianceCount1 = new ObservableField<String>();
    public ObservableField<String> TotalCount2 = new ObservableField<String>();
    public ObservableField<String> NotComplianceCount2 = new ObservableField<String>();
    public ObservableField<String> ComplianceCount2 = new ObservableField<String>();
    public ObservableField<String> TotalCount3 = new ObservableField<String>();
    public ObservableField<String> NotComplianceCount3 = new ObservableField<String>();
    public ObservableField<String> ComplianceCount3 = new ObservableField<String>();


    @Inject
    public SummaryViewModel(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public void onSelectFromDate(Context context){
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                      //  getFromTime(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year,context);
                        String date=(monthOfYear + 1) + "-" +  dayOfMonth+ "-" + year;
                        fromDate.set(date+" "+6+":"+40);

                    }
                }, mYear, mMonth, mDay);

        datePickerDialog.show();

    }

    public void onSelectToDate(Context context){
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                       // gettoTime(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year,context);
                        String date=(monthOfYear + 1) + "-" +  dayOfMonth+ "-" + year;
                        toDate.set(date+" "+6+":"+40);
                    }
                }, mYear, mMonth, mDay);

        datePickerDialog.show();

    }
    String  gettoTime(String date,Context context) {
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        toDate.set(date+":"+mHour+":"+mMinute);

                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();

        return ""+mHour+mMinute;
    }
    String  getFromTime(String date,Context context) {
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        fromDate.set(date+" "+6+":"+40);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();

        return ""+mHour+mMinute;
    }

    public void getSummaryData()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        String from=formatter.format(date) + " 6"+":40";
        String to =formatter.format(date) + " 6"+":40";


        apiClient.getApiService().getSummary(myPreferenceManager.getToken(),new SummaryRequest(to,from)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<SummaryResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SummaryResponse responseBody) {

                       setData(responseBody);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    private void setData(SummaryResponse responseBody)
    {
        System.out.println(responseBody.getPhase1().getComplianceCount());
        TotalCount1.set("Total Count " + responseBody.getPhase1().getTotalCount());
        ComplianceCount1.set("Compliance Count " + responseBody.getPhase1().getComplianceCount());
        NotComplianceCount1.set("Compliance Count " + responseBody.getPhase1().getComplianceCount());
        TotalCount2.set("Total Count " + responseBody.getPhase2().getTotalCount());
        ComplianceCount2.set("Compliance Count " + responseBody.getPhase2().getComplianceCount());
        NotComplianceCount2.set("Compliance Count " + responseBody.getPhase2().getComplianceCount());
        TotalCount3.set("Total Count " + responseBody.getPhase3().getTotalCount());
        ComplianceCount3.set("Compliance Count " + responseBody.getPhase3().getComplianceCount());
        NotComplianceCount3.set("Compliance Count " + responseBody.getPhase3().getComplianceCount());

    }

    public void onSendReport()

    {

      //  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
       // Date date = new Date();
        apiClient.getApiService().sendReport(myPreferenceManager.getToken(),new SummaryRequest(toDate.get(),fromDate.get())).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {

            }

            @Override
            public void onError(Throwable throwable) {

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

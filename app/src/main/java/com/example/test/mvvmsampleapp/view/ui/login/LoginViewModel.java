package com.example.test.mvvmsampleapp.view.ui.login;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.widget.EditText;

import com.example.test.mvvmsampleapp.R;
import com.example.test.mvvmsampleapp.data.Utils;
import com.example.test.mvvmsampleapp.databinding.ActivityLoginBinding;
import com.example.test.mvvmsampleapp.service.model.LoginResponse;
import com.example.test.mvvmsampleapp.service.network.ApiClient;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sunil.jadhav on 11/12/2018.
 */
public class LoginViewModel extends ViewModel {
    public MutableLiveData<LoginResponse> loginObservable = new MutableLiveData<>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();
    public ObservableField<String> username = new ObservableField<String>();
    public ObservableField<String> pass = new ObservableField<String>();
    public MutableLiveData<Integer> validate = new MutableLiveData<Integer>();


    @Inject
    ApiClient apiClient;



    @Inject
    public LoginViewModel() {

        //loginObservable = ApiClient.getInstance().login("");
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public void onLoginClick() {



        if(Validate().getValue() == 0)
        {
            loading.setValue(true);
            apiClient.login(Utils.encrypt(username.get()), Utils.encrypt(pass.get()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<LoginResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(LoginResponse response) {
                            loginObservable.setValue(response);
                            loading.setValue(false);
                        }

                        @Override
                        public void onError(Throwable e) {
                            loading.setValue(false);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    private MutableLiveData<Integer> Validate() {

        validate.setValue(0);

        if (username.get() == null  &&  pass.get() == null) {
            validate.setValue(1);

        }
        else if(username.get().isEmpty() && pass.get().isEmpty())
        {
            validate.setValue(1);
        }
        else if (username.get() ==null || username.get().isEmpty()) {
            validate.setValue(2);
        }
        else if (pass.get() ==null || pass.get().isEmpty()) {
            validate.setValue(3);
        }

//        }  else if (!pass.get().isEmpty() && pass.get().length() < 6) {
//            validate.setValue(3);
//
//        }
            return validate;
        }


    }

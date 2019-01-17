package com.example.test.mvvmsampleapp.view.ui.login;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;

import com.example.test.mvvmsampleapp.R;
import com.example.test.mvvmsampleapp.data.MyPreferenceManager;
import com.example.test.mvvmsampleapp.databinding.ActivityLoginBinding;
import com.example.test.mvvmsampleapp.service.model.LoginResponse;
import com.example.test.mvvmsampleapp.view.ui.BaseActivity;
import com.example.test.mvvmsampleapp.view.ui.main.MainActivity;
import com.example.test.mvvmsampleapp.viewmodel.AppViewModelFactory;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class LoginActivity extends BaseActivity {

   private LoginViewModel loginViewModel;

    @Inject
    AppViewModelFactory appViewModelFactory;
    ActivityLoginBinding loginBinding;

    @Inject
    MyPreferenceManager myPreferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, appViewModelFactory).get(LoginViewModel.class);
        loginBinding.setLogin(loginViewModel);
        loginViewModel.loginObservable.observe(this, new Observer<LoginResponse>() {
            @Override
            public void onChanged(@Nullable LoginResponse loginResponse) {
                if(loginResponse !=null) {
                    myPreferenceManager.setToken(loginResponse.getToken());
                    System.out.println(myPreferenceManager.getToken());
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    Log.d("response", "" + loginResponse.getEmailId());
                    startActivity(intent);
                }

            }
        });

        loginViewModel.loading.observe(this,aBoolean -> {
                if(aBoolean)
                {
                    Log.d("","inside loading obervable");
                   showDialog();
                }
                else{
                    hideDialog();
                }

        });

        loginViewModel.validate.observe(this, new Observer<Integer>() {

            @Override
            public void onChanged(@Nullable Integer aBoolean) {

                if(aBoolean == 1)
                {
                    showToast("Please add all fields");

                }
                else if(aBoolean == 2){
                    loginBinding.editText.setError("Please Enter Username");
                    loginBinding.editText.requestFocus();
                }
                else if(aBoolean == 3){
                    loginBinding.editText2.setError("Please Enter Password");
                    loginBinding.editText2.requestFocus();
                }

            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loginBinding.editText.setText("");
        loginBinding.editText2.setText("");

    }
}

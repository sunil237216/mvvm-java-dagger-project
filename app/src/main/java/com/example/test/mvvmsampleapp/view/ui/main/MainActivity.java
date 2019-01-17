package com.example.test.mvvmsampleapp.view.ui.main;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.ArraySet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.test.mvvmsampleapp.R;
import com.example.test.mvvmsampleapp.data.MyPreferenceManager;
import com.example.test.mvvmsampleapp.databinding.ActivityMainBinding;
import com.example.test.mvvmsampleapp.service.model.TsnRequest;
import com.example.test.mvvmsampleapp.service.network.ApiClient;
import com.example.test.mvvmsampleapp.view.ui.BaseActivity;
import com.example.test.mvvmsampleapp.viewmodel.AppViewModelFactory;

import java.util.HashSet;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class MainActivity extends BaseActivity implements HasSupportFragmentInjector {


    @Inject
    AppViewModelFactory appViewModelFactory;
    private String tsnno;
    private int phaseno;
    private String modelno;
    ActivityMainBinding binding;

    @Inject
    ApiClient apiClient;

    @Inject
    MyPreferenceManager myPreferenceManager;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        setSupportActionBar(binding.myToolbar);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.package.ACTION_LOGOUT");
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("onReceive", "Logout in progress");
                //At this point you should start the login activity and finish this one

                finish();
            }
        }, intentFilter);

        // Add project list fragment if this is first creation
        if (savedInstanceState == null) {
            LandingFragment fragment = new LandingFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, "MainActivity").commit();
        }
        // mainViewModel = ViewModelProviders.of(this, appViewModelFactory).get(MainViewModel.class);

    }

    public void setTSN(String tsnno) {
        this.tsnno = tsnno;
    }

    public String getTNS() {
        return tsnno;
    }

    public void setPhase(int phaseno) {
        this.phaseno = phaseno;
    }

    public int getPhase() {
        return phaseno;
    }

    public void setModel(String modelno) {
        this.modelno = modelno;
    }

    public String getModel() {
        return modelno;
    }

    public void show(int fragment) {
        switch (fragment) {
            case 1:

                ScanFragment scanFragment = new ScanFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,
                                scanFragment, null).addToBackStack("my_fragment").commit();
                break;

            case 2:
                BlankFragment blankFragment = new BlankFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,
                                blankFragment, null).addToBackStack("my_fragment").commit();
                break;

            case 3:
                ChecklistFragment listFragment = new ChecklistFragment();

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,
                                listFragment, null).addToBackStack("my_fragment").commit();
                break;

            case 4:
                SummaryFragment summaryFragment = new SummaryFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,
                                summaryFragment, null).addToBackStack("my_fragment").commit();
                break;

            case 5:

                getSupportFragmentManager().popBackStack("my_fragment", getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
                LandingFragment landing = new LandingFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, landing, "MainActivity").commit();

        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {

           super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sign_out:
                // Red item was selected
                showDialog();
                apiClient.getApiService().logout(myPreferenceManager.getToken()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                        System.out.println(responseBody);
                        hideDialog();
                        logout();
                        myPreferenceManager.clear();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        hideDialog();
                    }

                    @Override
                    public void onComplete() {

                    }
                });


                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

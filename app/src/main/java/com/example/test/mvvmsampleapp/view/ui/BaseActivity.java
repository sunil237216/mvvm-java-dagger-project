package com.example.test.mvvmsampleapp.view.ui;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.example.test.mvvmsampleapp.R;
import com.example.test.mvvmsampleapp.data.MyPreferenceManager;

import javax.inject.Inject;

public class BaseActivity extends AppCompatActivity {


    CustomProgressDialog customProgressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);



    }

    public void showDialog()
    {
        customProgressDialog=new CustomProgressDialog(this);
          customProgressDialog.showDialog();;
    }

    public void showToast(String message) {
        Toast.makeText(this, message,
                Toast.LENGTH_SHORT).show();
    }
    public void hideDialog()
    {
      //  customProgressDialog=new CustomProgressDialog(this);
        customProgressDialog.cancelDialog();
    }


    public void logout()
    {
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("com.package.ACTION_LOGOUT");
        sendBroadcast(broadcastIntent);
    }


}

package com.example.test.mvvmsampleapp.view.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.ProgressBar;

import com.example.test.mvvmsampleapp.R;


public class CustomProgressDialog {
    private ProgressDialog progressDialog;
    private ProgressBar progressBar;
    private Context context;
    public CustomProgressDialog(Context context){
        this.context=context;
        getProgressDialog();
    }
    private void getProgressDialog(){
        if(progressDialog==null) {
            progressDialog = new ProgressDialog(context, R.style.MyTheme);
        }
        progressBar = new ProgressBar(context,null,android.R.attr.progressBarStyleSmall);

    }

    public void showDialog(){
        progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Large);
        progressDialog.setCancelable(false);

        progressDialog.show();
       /* progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setIndeterminate(true);
        progressDialog.getWindow().setGravity(Gravity.CENTER);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setContentView(R.layout.progressbar_layout);*/
    }

    public void cancelDialog(){
        if(progressDialog!=null&&progressDialog.isShowing()){
            progressDialog.cancel();
        }
    }
}

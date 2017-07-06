package com.example.webwerks1.myapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by webwerks1 on 24/6/16.
 */
public class BaseActivity extends AppCompatActivity {
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialog=new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Please wait");
    }

    public void showProgress(){
        if(!isFinishing())
            mProgressDialog.show();
    }
    public void hideProgress(){
        if(!isFinishing())
            mProgressDialog.hide();
    }

    @Override
    protected void onDestroy() {
        if(mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        super.onDestroy();
    }
}

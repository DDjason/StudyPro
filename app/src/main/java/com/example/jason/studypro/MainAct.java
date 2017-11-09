package com.example.jason.studypro;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.jason.studypro.databinding.ActivityMainBinding;
import com.example.jason.studypro.viewCtrl.MainCtrl;
import com.example.jason.tool.PhotoLogicalUtil;
import com.umeng.socialize.UMShareAPI;

public class MainAct extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        UMShareAPI.get(this);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewCtrl(new MainCtrl(binding));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PhotoLogicalUtil.onActivityResult(this,requestCode,resultCode,data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

}

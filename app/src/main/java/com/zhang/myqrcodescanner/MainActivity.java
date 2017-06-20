package com.zhang.myqrcodescanner;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.duoyi.provider.qrscan.activity.CaptureActivity;
import com.jakewharton.rxbinding.view.RxView;
import com.tbruyelle.rxpermissions.RxPermissions;

import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* Button mBtn = (Button) findViewById(R.id.main_btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RxPermissions(MainActivity.this).request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA)
                        .subscribe(new Action1<Boolean>() {
                            @Override
                            public void call(Boolean granted) {
                                if (granted) {
                                    startActivity(new Intent(MainActivity.this, MipcaActivityCapture.class));
                                } else {

                                }
                            }
                        });
            }
        });*/

        RxView.clicks(findViewById(R.id.main_btn))
                .compose(new RxPermissions(this).ensure(Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE))
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean granted) {
                        if (granted){
                            //获取了权限
                            startActivity(new Intent(MainActivity.this, CaptureActivity.class));
                        }else{
                            //没有获取权限

                        }

                    }
                });
    }
}

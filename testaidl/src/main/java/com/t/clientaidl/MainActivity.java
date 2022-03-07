package com.t.clientaidl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.t.serveraidl.myaidl;


public class MainActivity extends AppCompatActivity {

    myaidl mYaidl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                add();
            }
        }, 3000);
    }

    public void start() {
        //  server 在个版本中的适配？
        //  通过Action启动指定注册的Service android6.0之后是无效的了 ?
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT < 23) {
            intent.setAction("com.xxx.yy");
            intent.setPackage("com.t.serveraidl");
            bindService(intent, conn, BIND_AUTO_CREATE);
        } else {
            intent.setComponent(new ComponentName("com.t.serveraidl", "com.t.serveraidl.Myservice"));
            bindService(intent, conn, BIND_AUTO_CREATE);
        }
    }

    public void add() {
        if (mYaidl == null) {
            Log.e("777", "值为null");
        }
        try {
            int i = mYaidl.addition(10, 20);
            Log.e("当前的数字是", i + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mYaidl = com.t.serveraidl.myaidl.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };
}

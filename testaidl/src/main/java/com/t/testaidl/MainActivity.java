package com.t.testaidl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.t.aidl3.myaidl;

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
        Intent intent = new Intent();
        intent.setAction("com.xxx.yy");
        intent.setPackage("com.t.aidl3");
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    public void add() {
        try {
            int i = mYaidl.addition(10, 20);
            Log.e("当前的数字是",i+"");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }




    ServiceConnection conn = new ServiceConnection(){
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
             mYaidl = com.t.aidl3.myaidl.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };
}

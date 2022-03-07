package com.t.serveraidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

public class Myservice extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Myservice","onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("Myservice","onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.e("Myservice","onStart");
    }

    @Override
    public void onDestroy() {
        Log.e("Myservice","onDestroy");
        super.onDestroy();
    }


      myaidl.Stub mBinder = new myaidl.Stub(){
          @Override
          public int addition(int a, int b) throws RemoteException {
              return a+b;
          }
      };
}

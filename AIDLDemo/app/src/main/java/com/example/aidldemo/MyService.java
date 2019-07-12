package com.example.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {

    public final String TAG = this.getClass().getName();

    public MyService() {
    }

    private IBinder binder = new IMyInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String getInfor(String s) throws RemoteException {
            Log.i(TAG,"s = " + s);
            return "我是"+TAG+"返回的自负串";
        }


    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return binder;
    }
}

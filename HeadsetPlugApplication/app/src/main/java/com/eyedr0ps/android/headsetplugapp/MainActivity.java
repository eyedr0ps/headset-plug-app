package com.eyedr0ps.android.headsetplugapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private MusicIntentReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myReceiver = new MusicIntentReceiver();
    }

    @Override
    public void onResume() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        registerReceiver(myReceiver, filter);
        Log.i(TAG, "onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        unregisterReceiver(myReceiver);
        Log.i(TAG, "onPause");
        super.onPause();
    }

    class MusicIntentReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
                int state = intent.getIntExtra("state", -1);
                switch (state) {
                    case 0:
                        Log.i(TAG, "Headset is unplugged / state: " + state);
                        break;
                    case 1:
                        Log.i(TAG, "Headset is plugged with mic / state: " + state);
                        break;
                    case 2:
                        Log.i(TAG, "Headset is plugged without mic / state: " + state);
                        break;
                    default:
                        Log.i(TAG, "Undefined / state: " + state);
                }
            }
        }
    }
}

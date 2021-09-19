package com.example.yashodhara;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkStateChecker extends BroadcastReceiver {
    private Context context;


    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork.isAvailable()){
            Toast.makeText(context, "Please connect the app to internet to download data.", Toast.LENGTH_SHORT).show();
        }
        if (activeNetwork.isConnected()){
            Toast.makeText(context, "Please connect the app to internet to download data.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "No Internet.", Toast.LENGTH_SHORT).show();
        }
    }
}

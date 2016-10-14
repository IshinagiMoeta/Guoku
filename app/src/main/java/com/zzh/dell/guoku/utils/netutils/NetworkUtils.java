package com.zzh.dell.guoku.utils.netutils;

import android.annotation.TargetApi;
import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;

/**朱张华
 * Created by DELL on 2016/10/14.
 */
public class NetworkUtils {
    public static boolean judgeNetwork(Context context){
        ConnectivityManager systemService = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] allNetworks = systemService.getAllNetworkInfo();
        int length = allNetworks.length;
        for (int i = 0; i < length; i++) {
            if(allNetworks[i].getState()==NetworkInfo.State.CONNECTED){
//                NetworkInfo inf = allNetworks[i];
//                if(){
//
//                }
                return true;
            }
        }

        return false;
    }
}

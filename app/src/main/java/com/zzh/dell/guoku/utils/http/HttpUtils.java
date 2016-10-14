package com.zzh.dell.guoku.utils.http;

import android.os.Handler;

import com.zzh.dell.guoku.callback.HttpCallBack;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 朱张华 on 2016/10/14.
 */
public final class HttpUtils {
    private Handler handler = new Handler();
    HttpCallBack callBack;

    public void setCallBack(HttpCallBack callBack) {
        this.callBack = callBack;
    }

    private HttpUtils(){}
    private static HttpUtils intance = null;
    public static HttpUtils getIntance(){
        if(intance==null){
            synchronized (HttpUtils.class){
                if(intance==null){
                    intance = new HttpUtils();
                }
            }
        }
        return intance;
    }


    /**
     * pOSt 请求
     * @param type 请求码
     * @param path 请求路径
     * @param map 请求参数
     */
    public  void getStrPOST(final String type,String path,Map<String,String>map){
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder body = new FormBody.Builder();
        int size = map.size();
        for(Map.Entry<String,String> header:map.entrySet()){
            body.add(header.getKey(),header.getValue());
        }
        Request request = new Request.Builder().post(body.build()).url(path).build();
        if(callBack!=null) {
            callBack.sendStrbefore(type);
        }
        getData(type, client, request);
    }

    /**
     * get请求数据
     * @return
     */
    public void getStrGET(final String type,String path){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().get().url(path).build();
        if(callBack!=null) {
            callBack.sendStrbefore(type);
        }
        getData(type, client, request);
    }

    private void getData(final String type, OkHttpClient client, Request request) {
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(callBack!=null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.sendStrAfter(type);
                            callBack.sendStr(type,"访问失败！");
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String str = response.body().string();
                if(callBack!=null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.sendStrAfter(type);
                            callBack.sendStr(type,str);
                        }
                    });
                }
            }
        });
    }



}
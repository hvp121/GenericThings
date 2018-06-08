package com.genericthings.retrofit;

import android.util.Log;

import com.genericthings.BuildConfig;
import com.genericthings.genericAdapter.ResponseListener;
import com.google.gson.Gson;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MakeRequest {

    private Gson mGson;
    private static MakeRequest mInstance;

    private MakeRequest() {
        mGson = new Gson();
    }

    public static synchronized MakeRequest getInstance() {
        if (mInstance == null)
            mInstance = new MakeRequest();
        return mInstance;
    }

    public void request(Call call, final Class aClass, final ResponseListener listener) {

        printLog("--Request-- " + call.request().url().toString());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    printLog("--Response-- " + jsonObject.toString());
                    Object model = new Gson().fromJson(jsonObject.toString(), aClass);
                    listener.onResponse(model);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<okhttp3.ResponseBody> call, Throwable t) {
                listener.onError("something went wrong");
            }
        });
    }

    private void printLog(String log) {
        if (BuildConfig.DEBUG)
            Log.e("----", log);
    }
}

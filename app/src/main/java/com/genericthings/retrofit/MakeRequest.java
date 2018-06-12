package com.genericthings.retrofit;

import android.util.Log;

import com.genericthings.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MakeRequest {

    private static MakeRequest mInstance;

    private MakeRequest() {
    }

    public static synchronized MakeRequest getInstance() {
        if (mInstance == null)
            mInstance = new MakeRequest();
        return mInstance;
    }


    /**
     * Returns object as response, later cast it based on passed response model
     *
     * @param call              call
     * @param responseModel     expected response model
     * @param listener          result callback
     */
    public void request(Call call, final Class<?> responseModel, final ResponseListener listener) {

        printLog("--Request-- " + call.request().url().toString());
        listener.showHideProgress(true);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    Object model = new Gson().fromJson(jsonObject.toString(), responseModel);
                    listener.showHideProgress(false);
                    listener.onResponse(model);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<okhttp3.ResponseBody> call, Throwable t) {
                listener.showHideProgress(false);
                listener.onError("something went wrong");
            }
        });
    }


    /**
     * Returns String as response, later cast it to String
     *
     * @param call          call
     * @param listener      result callback
     */
    public void request(Call call, final ResponseListener listener) {

        printLog("--Request-- " + call.request().url().toString());
        listener.showHideProgress(true);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    listener.showHideProgress(false);
                    listener.onResponse(new JSONObject(response.body().string()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<okhttp3.ResponseBody> call, Throwable t) {
                listener.showHideProgress(false);
                listener.onError("something went wrong");
            }
        });
    }


    public static void printLog(String log) {
        if (BuildConfig.DEBUG)
            Log.e("----", log);
    }
}

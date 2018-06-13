package com.genericthings.retrofit;

import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ViewDataBinding;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.genericthings.R;
import com.genericthings.base.BaseActivity;
import com.genericthings.databinding.ActivityRetrofitBinding;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class SampleRetrofitActivity extends BaseActivity {


    private ActivityRetrofitBinding mDataBinding;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_retrofit;
    }


    @Override
    public void init(ViewDataBinding dataBinding) {

        mDataBinding = (ActivityRetrofitBinding) dataBinding;

        callAPI();

    }


    private void callAPI() {

        Call<ResponseBody> call = RetrofitInstance.getService().test("https://raw.githubusercontent.com/manojbhadane/Kotlin-LambdaExpression/master/sample.json");

        MakeRequest.getInstance().request(call, ResModel.class, new ResponseListener() {
            @Override
            public void onResponse(Object object) {
                ResModel model = (ResModel) object;
            }

            @Override
            public void onError(String msg) {
                Toast.makeText(SampleRetrofitActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void showHideProgress(boolean shouldShow) {
                mDataBinding.prgbar.setVisibility(shouldShow ? View.VISIBLE : View.GONE);
            }
        });


        /**
         * get String as response
         */
        MakeRequest.getInstance().request(call, new ResponseListener() {
            @Override
            public void onResponse(Object model) {
                String res = (String) model.toString();
            }

            @Override
            public void onError(String msg) {
                Toast.makeText(SampleRetrofitActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void showHideProgress(boolean shouldShow) {
                mDataBinding.prgbar.setVisibility(shouldShow ? View.VISIBLE : View.GONE);
            }
        });
    }
}

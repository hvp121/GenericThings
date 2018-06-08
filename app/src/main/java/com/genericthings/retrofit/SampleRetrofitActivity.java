package com.genericthings.retrofit;

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

        Call<ResponseBody> call = RetrofitInstance.getService().test("https://reqres.in/api/users/");
        MakeRequest.getInstance().request(call, ResModel.class, new ResponseListener() {
            @Override
            public void onResponse(Object d) {
                Log.e("--", "--Result--" + ((ResModel) d).getPage());
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

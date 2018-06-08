package com.genericthings.retrofit;

import android.databinding.ViewDataBinding;
import android.util.Log;
import android.view.View;

import com.genericthings.R;
import com.genericthings.base.BaseActivity;
import com.genericthings.databinding.ActivityRetrofitBinding;
import com.genericthings.genericAdapter.ResponseListener;

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
        mDataBinding.prgbar.setVisibility(View.VISIBLE);

        Call<ResponseBody> call = RetrofitInstance.getService().test("https://reqres.in/api/users/");
        MakeRequest.getInstance().request(call, ResModel.class, new ResponseListener() {
            @Override
            public void onResponse(Object d) {
                mDataBinding.prgbar.setVisibility(View.GONE);
                Log.e("--", "--page--" + ((ResModel) d).getPage());
            }

            @Override
            public void onError(String msg) {

            }
        });
    }
}

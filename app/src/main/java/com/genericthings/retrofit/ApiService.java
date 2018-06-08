package com.genericthings.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiService {

    @GET
    public Call<ResponseBody> test(@Url String url);

    @GET
    public Call<ResponseBody> test1();

}

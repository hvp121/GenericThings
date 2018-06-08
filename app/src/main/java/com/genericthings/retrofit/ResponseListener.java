package com.genericthings.retrofit;

public interface ResponseListener {
    public void onResponse(Object d);

    public void onError(String msg);

    public void showHideProgress(boolean shouldShow);
}

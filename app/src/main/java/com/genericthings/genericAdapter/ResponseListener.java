package com.genericthings.genericAdapter;

public interface ResponseListener {
    public void onResponse(Object d);

    public void onError(String msg);
}

package com.example.eslam.vodafoodies.network;

/**
 * Created by Eslam on 9/11/2017.
 */

public interface NetworkCallback {
    void onSuccess(Object responseBody);
    void onError(Throwable error);
}

package com.example.eslam.vodafoodies.network;

import com.example.eslam.vodafoodies.model.User;
import com.example.eslam.vodafoodies.network.request.UpdateUserDataRequest;
import com.example.eslam.vodafoodies.network.response.UpdateUserDataResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

/**
 * Created by Eslam on 9/10/2017.
 */

public class NetworkManager {
    private static NetworkManager networkManager;
    private static final String baseUrl = "https://us-central1-vodafoodies-e3f2f.cloudfunctions.net";
    private Retrofit retrofit;
    private ApiInterface apiService;

    private NetworkManager(){}

    public static NetworkManager getInstance(){
        if(networkManager == null){
            networkManager = new NetworkManager();
            networkManager.retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
            networkManager.apiService = networkManager.retrofit.create(ApiInterface.class);
        }
        return networkManager;
    }

    public void updateUserData(UpdateUserDataRequest request, final NetworkCallback callback){
        Call<UpdateUserDataResponse> updateUserDataResponseCall = apiService.updateUserData(request.getHeadersMap(), request.getBody());
        updateUserDataResponseCall.enqueue(new Callback<UpdateUserDataResponse>() {
            @Override
            public void onResponse(Call<UpdateUserDataResponse> call, Response<UpdateUserDataResponse> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<UpdateUserDataResponse> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

}

interface ApiInterface{
    @POST("updateUserData")
    Call<UpdateUserDataResponse> updateUserData(@HeaderMap Map<String,String> headers, @Body Map<String,User> user);
}

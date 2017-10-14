package com.example.eslam.vodafoodies.network;

import com.example.eslam.vodafoodies.model.Category;
import com.example.eslam.vodafoodies.model.Item;
import com.example.eslam.vodafoodies.model.User;
import com.example.eslam.vodafoodies.model.Venue;
import com.example.eslam.vodafoodies.network.request.UpdateUserDataRequest;
import com.example.eslam.vodafoodies.network.response.UpdateUserDataResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

    public void listedVenues(final NetworkCallback callback){
        Call<Map<String, Venue>> listedVenues = apiService.listedVenues();
        listedVenues.enqueue(new Callback<Map<String, Venue>>() {
            @Override
            public void onResponse(Call<Map<String, Venue>> call, Response<Map<String, Venue>> response) {
                Map<String, Venue> body = response.body();
                for (String s : body.keySet()) {
                    body.get(s).setId(s);
                }
                callback.onSuccess(body);
            }

            @Override
            public void onFailure(Call<Map<String, Venue>> call, Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }

    public void getVenueMenu(String venueId, final NetworkCallback callback){
        Call<Map<String, Category>> venueMenu = apiService.getVenueMenu(venueId);
        venueMenu.enqueue(new Callback<Map<String, Category>>() {
            @Override
            public void onResponse(Call<Map<String, Category>> call, Response<Map<String, Category>> response) {
                Map<String, Category> responseMap = response.body();
                for (Category category : responseMap.values()) {
                    HashMap<String, Item> items = category.getItems();
                    for (String s : items.keySet()) {
                        items.get(s).setId(s);
                    }
                }
                callback.onSuccess(responseMap);
            }

            @Override
            public void onFailure(Call<Map<String, Category>> call, Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }

}

interface ApiInterface{
    /**
     * updates user data into our FireBase database
     * @param headers - a map of headers (key, value)
     * @param user - app user not FireBase user
     * @return - response is simply status & result.
     */
    @POST("updateUserData")
    Call<UpdateUserDataResponse> updateUserData(@HeaderMap Map<String,String> headers, @Body Map<String,User> user);

    /**
     * gets list of venues
     * @return - response is a hashMap of (id, venue)
     */
    @POST("listedVenues")
    Call<Map<String,Venue>> listedVenues();

    /**
     * gets the venue menu
     * @param venueId - id of the venue
     * @return - response is a HashMap (category name, category)
     */
    @GET("getVenueMenu")
    Call<Map<String,Category>> getVenueMenu(@Query("venue_id") String venueId);
}

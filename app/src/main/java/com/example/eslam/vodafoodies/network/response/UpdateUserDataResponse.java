package com.example.eslam.vodafoodies.network.response;

import com.example.eslam.vodafoodies.network.request.BaseRequest;

/**
 * Created by Eslam on 9/11/2017.
 */

public class UpdateUserDataResponse extends BaseRequest {
    String status;
    String result;

    @Override
    public String toString() {
        return "UpdateUserDataResponse{" +
                "status='" + status + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}

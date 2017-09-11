package com.example.eslam.vodafoodies.network.request;

import com.example.eslam.vodafoodies.MyApplication;

import java.util.Collections;
import java.util.Map;

/**
 * Created by Eslam on 9/10/2017.
 */

public class BaseRequest {
    Map<String, String> headersMap;

    public BaseRequest() {
        headersMap = Collections.singletonMap("uid", MyApplication.getUserId());
    }

    public Map<String, String> getHeadersMap() {
        return headersMap;
    }
}

package com.example.eslam.vodafoodies.network.request;

import com.example.eslam.vodafoodies.MyApplication;
import com.example.eslam.vodafoodies.model.User;

import java.util.Collections;
import java.util.Map;

/**
 * Created by Eslam on 9/11/2017.
 */

public class UpdateUserDataRequest extends BaseRequest {
    Map<String, User> body;

    private UpdateUserDataRequest() {
    }

    public UpdateUserDataRequest(Map<String, User> body) {
        super();
        this.body = body;
    }

    public Map<String, User> getBody() {
        return body;
    }

    public static class Builder {
        private UpdateUserDataRequest request = new UpdateUserDataRequest();

        public Builder setUser(User user) {
            String userId = MyApplication.getUser().getUid();
            request.body = Collections.singletonMap(userId, user);
            return this;
        }

        public UpdateUserDataRequest build() {
            if (request.body == null)
                throw new IllegalStateException("setUser must be called on UpdateUserDataRequest.Builder");
            return request;
        }
    }
}

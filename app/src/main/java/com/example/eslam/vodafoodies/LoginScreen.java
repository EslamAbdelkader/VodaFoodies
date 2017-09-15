package com.example.eslam.vodafoodies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.eslam.vodafoodies.activity.HomeActivity;
import com.example.eslam.vodafoodies.model.User;
import com.example.eslam.vodafoodies.network.NetworkCallback;
import com.example.eslam.vodafoodies.network.NetworkManager;
import com.example.eslam.vodafoodies.network.request.UpdateUserDataRequest;
import com.example.eslam.vodafoodies.network.response.UpdateUserDataResponse;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginScreen extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        mAuth = FirebaseAuth.getInstance();
//        checkLoggedUser();
        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("public_profile", "user_friends", "user_photos", "email", "user_birthday", "public_profile", "pages_messaging_phone_number");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.i("MyTag", "cancel");
                Toast.makeText(LoginScreen.this, "cancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Log.i("MyTag", error.toString());
                Toast.makeText(LoginScreen.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkLoggedUser() {
        if (mAuth.getCurrentUser() != null)
            navigateToHomeActivity();
    }

    private void handleFacebookAccessToken(final AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.i("MyTag", response.toString());
                        Log.i("MyTag", object.toString());
                        signInToFirebase(accessToken, object);
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "name,gender,email,link,picture.type(large)");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void signInToFirebase(AccessToken accessToken, final JSONObject object) {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in facebookUser's information
//                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser facebookUser = mAuth.getCurrentUser();
                            User user = createUser(facebookUser, object);
                            MyApplication.saveUser(user);
                            navigateToHomeActivity();
//                            updateUI(facebookUser);
                        } else {
                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginScreen.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            Log.i("MyTag", "Auth failed.");
//                            updateUI(null);
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("MyTag", "failed" + e.getMessage());
            }
        });
    }

    private User createUser(FirebaseUser facebookUser, JSONObject object) {
        try {
            String uid = facebookUser.getUid();
            String name = object.getString("name");
            String gender = object.getString("gender");
            String email = object.getString("email");
            String url = object.getString("link");
            String imageUrl = object.getJSONObject("picture").getJSONObject("data").getString("url");
            User user = new User(uid, gender, name, email, "", imageUrl, url);
            Log.i("MyTag", "user: " + user.toString());
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void navigateToHomeActivity() {
        startActivity(new Intent(LoginScreen.this, HomeActivity.class));
        updateUserData();
        finish();
    }

    private void updateUserData() {
        UpdateUserDataRequest request = new UpdateUserDataRequest.Builder().setUser(MyApplication.getUser()).build();
        NetworkManager.getInstance().updateUserData(request, new NetworkCallback() {
            @Override
            public void onSuccess(Object responseBody) {
                if (responseBody instanceof UpdateUserDataResponse)
                    Log.i("MyTag", ((UpdateUserDataResponse) responseBody).toString());
            }

            @Override
            public void onError(Throwable error) {
                Log.i("MyTag", error.toString());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}

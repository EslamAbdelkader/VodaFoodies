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

public class LoginScreen extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        mAuth = FirebaseAuth.getInstance();
        checkLoggedUser();
        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
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

    private void handleFacebookAccessToken(AccessToken accessToken) {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            MyApplication.saveUserId(user.getUid());
                            navigateToHomeActivity();
//                            updateUI(user);
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

    private void navigateToHomeActivity() {
        startActivity(new Intent(LoginScreen.this, HomeActivity.class));
        // TODO: 9/11/2017 Remove test
        testUpdateUserRequest();
        finish();
    }

    private void testUpdateUserRequest() {
        UpdateUserDataRequest request = new UpdateUserDataRequest.Builder().setUser(new User("Ess", "EssEmail", "EssPhone", "EssImage", "EssFbProfile")).build();
        NetworkManager.getInstance().updateUserData(request, new NetworkCallback() {
            @Override
            public void onSuccess(Object responseBody) {
                if (responseBody instanceof UpdateUserDataResponse)
                    Log.i("MyTag", ((UpdateUserDataResponse) responseBody).toString());
            }

            @Override
            public void onError(Throwable error) {
                Log.i("MyTag",error.toString());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}

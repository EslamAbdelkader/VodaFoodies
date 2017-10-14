package com.example.eslam.vodafoodies;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.eslam.vodafoodies.network.NetworkCallback;
import com.example.eslam.vodafoodies.network.NetworkManager;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkManager.getInstance().getVenueMenu("9dcbafe1-2cb6ba67",new NetworkCallback() {
                    @Override
                    public void onSuccess(Object responseBody) {
                        Log.i("MyTag",responseBody.toString());
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.i("MyTag",error.getMessage());
                    }
                });
            }
        });
    }

}

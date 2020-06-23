package com.eysisgmbh.grabnowdeleviry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.eysisgmbh.grabnowdeleviry.application.MyApp;
import com.eysisgmbh.grabnowdeleviry.custom.CustomActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (task.isSuccessful()) {
                            Log.w("Refresh token", "getInstanceId failed", task.getException());
                            // Get new Instance ID token
                            String token = task.getResult().getToken();
                            MyApp.setSharedPrefString("TOKEN", token);
                        }


                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                if (MyApp.readUser().getData() == null) {
                                    startActivity(new Intent(Splash.this, Login.class));
                                } else {
                                    startActivity(new Intent(Splash.this, MainActivity.class));

                                }
                                finishAffinity();
                                CustomActivity.animateSwipeLeft(Splash.this);
                            }
                        }, 2000);
                    }
                });



    }
}

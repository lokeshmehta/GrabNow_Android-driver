package com.eysisgmbh.grabnowdeleviry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.eysisgmbh.grabnowdeleviry.custom.CustomActivity;

public class MainActivity extends CustomActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpUi();
    }

    private void setUpUi() {

        setClick(R.id.btn_ongoing);
        setClick(R.id.btn_past);
    }


    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_ongoing){
            startActivity(new Intent(MainActivity.this,OngoingOrder.class));
            CustomActivity.animateSwipeLeft(this);

        }else if (v.getId()==R.id.btn_past){
            startActivity(new Intent(MainActivity.this,PastOrder.class));
            CustomActivity.animateSwipeLeft(this);
        }
    }
}
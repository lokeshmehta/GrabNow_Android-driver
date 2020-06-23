package com.eysisgmbh.grabnowdeleviry;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eysisgmbh.grabnowdeleviry.application.MyApp;
import com.eysisgmbh.grabnowdeleviry.custom.CustomActivity;
import com.eysisgmbh.grabnowdeleviry.model.OrderModel;
import com.eysisgmbh.grabnowdeleviry.networking.GetDataService;
import com.eysisgmbh.grabnowdeleviry.networking.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OngoingOrder extends CustomActivity {

    private TextView txt_order_no, txt_address, txt_no_order;
    private Button btn_view;
    private LinearLayout ll_order;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongoing_order);

    }

    @Override
    protected void onResume() {
        super.onResume();
        seUpUi();
    }

    private void seUpUi() {
        setupActionBar(findViewById(R.id.toolbar), R.string.ongoing_order);


        txt_order_no = findViewById(R.id.txt_order_no);
        txt_address = findViewById(R.id.txt_address);
        btn_view = findViewById(R.id.btn_view);
        ll_order = findViewById(R.id.ll_order);
        txt_no_order = findViewById(R.id.txt_no_order);
        setClick(btn_view);

        MyApp.spinnerStart(this, "");
        GetDataService service = RetrofitClient.getRetrofitInstance().create(GetDataService.class);
        Call<OrderModel> call = service.onGoingOrders(MyApp.readUser().getData().getTokenType() + " " + MyApp.readUser().getData().getApiToken(), "1", "de");


        call.enqueue(new Callback<OrderModel>() {
            @Override
            public void onResponse(Call<OrderModel> call, Response<OrderModel> response) {
                setData(response.body());
                MyApp.spinnerStop();

            }

            @Override
            public void onFailure(Call<OrderModel> call, Throwable t) {

                Log.e(">>>>>", ">>>" + t);
                MyApp.spinnerStop();
                setData(null);

            }
        });


    }

    @Override
    public void onClick(View v) {
        if (v == btn_view){
            startActivity(new Intent(this,OrderDetail.class).putExtra("id",id));
            CustomActivity.animateSwipeLeft(this);
        }
    }

    private void setData(OrderModel order) {

        if (order != null && order.getData().getOrdersData().size() != 0) {
            id=order.getData().getOrdersData().get(0).getId()+"";
            txt_no_order.setVisibility(View.GONE);
            btn_view.setVisibility(View.VISIBLE);
            ll_order.setVisibility(View.VISIBLE);
            txt_order_no.setText(order.getData().getOrdersData().get(0).getInvoiceId());
            txt_address.setText(order.getData().getOrdersData().get(0).getAddress());

        } else {
            txt_no_order.setVisibility(View.VISIBLE);
            btn_view.setVisibility(View.GONE);
            ll_order.setVisibility(View.GONE);

        }


    }


}
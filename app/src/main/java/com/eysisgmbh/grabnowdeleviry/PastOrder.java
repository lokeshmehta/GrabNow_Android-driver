package com.eysisgmbh.grabnowdeleviry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.eysisgmbh.grabnowdeleviry.adapter.MyOrderAdapter;
import com.eysisgmbh.grabnowdeleviry.application.MyApp;
import com.eysisgmbh.grabnowdeleviry.custom.CustomActivity;
import com.eysisgmbh.grabnowdeleviry.model.OrderModel;
import com.eysisgmbh.grabnowdeleviry.networking.GetDataService;
import com.eysisgmbh.grabnowdeleviry.networking.RetrofitClient;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PastOrder extends CustomActivity {

    private RecyclerView rv_order;
    private MyOrderAdapter adapter;
    ArrayList<OrderModel.OrdersDataItem> allOrder;
    ShimmerFrameLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_order);
        setUpUi();
    }

    private void setUpUi() {
        setupActionBar(findViewById(R.id.toolbar), R.string.past_orders);
        rv_order = findViewById(R.id.rv_order);
        container = findViewById(R.id.shimmer_view_container);
        container.startShimmer();
        rv_order.setLayoutManager(new LinearLayoutManager(this));
        allOrder=new ArrayList<>();



        adapter = new MyOrderAdapter(allOrder, this);
        rv_order.setAdapter(adapter);
        getData();
    }

    private void getData() {

        GetDataService service = RetrofitClient.getRetrofitInstance().create(GetDataService.class);
        Call<OrderModel> call = service.deliveredOrders(MyApp.readUser().getData().getTokenType() + " " + MyApp.readUser().getData().getApiToken(),"1",   "de");


        call.enqueue(new Callback<OrderModel>() {
            @Override
            public void onResponse(Call<OrderModel> call, Response<OrderModel> response) {
                allOrder.clear();


                if (response.body().getData() != null){

                    allOrder= (ArrayList<OrderModel.OrdersDataItem>) response.body().getData().getOrdersData();

                }


                rv_order.setVisibility(View.VISIBLE);
                container.stopShimmer();
                container.setVisibility(View.GONE);
                adapter.updateData(allOrder);

            }

            @Override
            public void onFailure(Call<OrderModel> call, Throwable t) {

                Log.e(">>>>>", ">>>" + t);


            }
        });


    }


}

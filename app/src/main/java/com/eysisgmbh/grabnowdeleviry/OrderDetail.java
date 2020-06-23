package com.eysisgmbh.grabnowdeleviry;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eysisgmbh.grabnowdeleviry.adapter.MainProductAdapter;
import com.eysisgmbh.grabnowdeleviry.application.MyApp;
import com.eysisgmbh.grabnowdeleviry.custom.CustomActivity;
import com.eysisgmbh.grabnowdeleviry.model.ChangeStatusModel;
import com.eysisgmbh.grabnowdeleviry.model.OrderDetailModel;
import com.eysisgmbh.grabnowdeleviry.networking.GetDataService;
import com.eysisgmbh.grabnowdeleviry.networking.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetail extends CustomActivity {

    private OrderDetailModel data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        getData();
    }

    private void getData() {
        setupActionBar(findViewById(R.id.toolbar), R.string.order_detail);
        MyApp.spinnerStart(this, "");
        GetDataService service = RetrofitClient.getRetrofitInstance().create(GetDataService.class);
        Call<OrderDetailModel> call = service.orderDetail(MyApp.readUser().getData().getTokenType() + " " + MyApp.readUser().getData().getApiToken(),
                getIntent().getStringExtra("id"), "de");


        call.enqueue(new Callback<OrderDetailModel>() {
            @Override
            public void onResponse(Call<OrderDetailModel> call, Response<OrderDetailModel> response) {

                data = response.body();
                setUpData();
                MyApp.spinnerStop();

            }

            @Override
            public void onFailure(Call<OrderDetailModel> call, Throwable t) {

                Log.e(">>>>>", ">>>" + t);
                MyApp.spinnerStop();


            }
        });
    }

    private void setUpData() {

        if (data != null && data.getData().getOrderDetail() != null) {

            TextView txt_order_no = findViewById(R.id.txt_order_no);
            TextView txt_customer_name = findViewById(R.id.txt_customer_name);
            TextView txt_address = findViewById(R.id.txt_address);
            TextView txt_note = findViewById(R.id.txt_note);
            setClick(R.id.btn_direction);
            setClick(R.id.btn_deliver);

            txt_note.setText("*" + data.getData().getOrderDetail().getCustomerOrderNote());
            txt_order_no.setText(getString(R.string.order_number) + "-" + data.getData().getOrderDetail().getInvoiceId());
            txt_customer_name.setText(getString(R.string.name) + "-" + data.getData().getOrderDetail().getCustomerDetail().getFirstName() + " " +
                    data.getData().getOrderDetail().getCustomerDetail().getLastName());
            txt_address.setText(getString(R.string.address) + "-" + data.getData().getOrderDetail().getAddress() + "\n" +
                    getString(R.string.building_no) + "-" + data.getData().getOrderDetail().getBuildingNo() + "\n" +
                    getString(R.string.floor_no) + "-" + data.getData().getOrderDetail().getFloorNo() + "\n" +
                    getString(R.string.apt_no) + "-" + data.getData().getOrderDetail().getAptNo() + "\n" +
                    getString(R.string.direction) + "-" + data.getData().getOrderDetail().getDirection());

            RecyclerView rv_products = findViewById(R.id.rv_products);
            MainProductAdapter adapter = new MainProductAdapter(data.getData().getOrderDetail().getProducts(), this);
            rv_products.setLayoutManager(new LinearLayoutManager(this));
            rv_products.setAdapter(adapter);


        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_direction) {
            try {
                String url = "https://www.google.com/maps/dir/?api=1&destination=" + data.getData().getOrderDetail().getLat() + "," + data.getData().getOrderDetail().getLng() + "&travelmode=driving";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            } catch (Exception e) {

            }

        } else if (v.getId() == R.id.btn_deliver) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)

                    .setMessage(getResources().getString(R.string.del_msg))
                    .setPositiveButton(getResources().getString(R.string.deliver), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            MyApp.spinnerStart(OrderDetail.this, "");
                            GetDataService service = RetrofitClient.getRetrofitInstance().create(GetDataService.class);
                            Call<ChangeStatusModel> call = service.changeOrderStatus(MyApp.readUser().getData().getTokenType() + " " + MyApp.readUser().getData().getApiToken(),
                                    getIntent().getStringExtra("id"), "de");


                            call.enqueue(new Callback<ChangeStatusModel>() {
                                @Override
                                public void onResponse(Call<ChangeStatusModel> call, Response<ChangeStatusModel> response) {

                                    MyApp.spinnerStop();
                                    if (response.body().getData().getOrdersData().getStatus().equalsIgnoreCase("Delivered"))
                                        MyApp.popFinishableMessage("", getString(R.string.msg_susscess), OrderDetail.this);
                                    else
                                        MyApp.popFinishableMessage("", response.body().getMessage(), OrderDetail.this);

                                }

                                @Override
                                public void onFailure(Call<ChangeStatusModel> call, Throwable t) {

                                    Log.e(">>>>>", ">>>" + t);
                                    MyApp.spinnerStop();
                                    MyApp.popMessage("", getString(R.string.error_msg), OrderDetail.this);

                                }
                            });


                        }

                    })
                    .setNegativeButton(getResources().getString(R.string.no), null)
                    .show();

        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.call_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", data.getData().getOrderDetail().getCustomerDetail().getMobileNo()+"", null));
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
package com.eysisgmbh.grabnowdeleviry.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eysisgmbh.grabnowdeleviry.R;
import com.eysisgmbh.grabnowdeleviry.custom.CustomActivity;
import com.eysisgmbh.grabnowdeleviry.model.OrderModel;

import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {
    public List<OrderModel.OrdersDataItem> orderList;
    Activity mContext;


    public MyOrderAdapter(List<OrderModel.OrdersDataItem> list, Activity mContext) {
        this.orderList = list;
        this.mContext = mContext;


    }

    public void updateData(List<OrderModel.OrdersDataItem> list) {
        this.orderList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_order, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderAdapter.ViewHolder holder, int position) {

        OrderModel.OrdersDataItem OrdersDataItem = orderList.get(position);

        holder.txt_order_no.setText(mContext.getString(R.string.order_number) + " -" + OrdersDataItem.getInvoiceId());
        holder.txt_date.setText(OrdersDataItem.getUpdatedAt());

       /* if (OrdersDataItem.getOrderStatusCode() == 1) {
            holder.txt_status.setText(mContext.getString(R.string.preparing));


        } else if (OrdersDataItem.getOrderStatusCode() == 2) {

            holder.txt_status.setText(mContext.getString(R.string.in_transmit));
        } else if (OrderModel.OrdersDataItem.getOrderStatusCode() == 3) {
            holder.txt_status.setText(mContext.getString(R.string.delivered));
        }
*/

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private TextView txt_order_no, txt_status, txt_date;


        public ViewHolder(View itemView) {
            super(itemView);

            txt_order_no = itemView.findViewById(R.id.txt_order_no);
            txt_status = itemView.findViewById(R.id.txt_status);

            txt_date = itemView.findViewById(R.id.txt_date);


          
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   /* mContext.startActivity(new Intent(mContext, OrderStatus.class).putExtra("data", orderList.get(getAdapterPosition())));
                    CustomActivity.animateSwipeLeft(mContext);*/
                }
            });


        }
    }

}

package com.eysisgmbh.grabnowdeleviry.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eysisgmbh.grabnowdeleviry.R;
import com.eysisgmbh.grabnowdeleviry.model.ProductsItem;

import java.util.List;


public class MainProductAdapter extends RecyclerView.Adapter<MainProductAdapter.VHItem> {

    public List<ProductsItem> productList;
    Activity mContext;



    public MainProductAdapter(List<ProductsItem> list, Activity mContext ) {
        this.productList = list;
        this.mContext = mContext;


    }


    public void updateData(List<ProductsItem> list) {
        this.productList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VHItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new VHItem(view);

    }

    @Override
    public void onBindViewHolder(@NonNull VHItem holder, int position) {


        ProductsItem item = productList.get(position);
        holder.txt_price.setText("€" + item.getRegularPrice());

        holder.txt_quantity.setText("x"+item.getQuantity() );
        holder.txt_item_detail.setText( item.getTitle()+ "-" + item.getMeasurementVolume()+ item.getMeasurementUnit() );


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public class VHItem extends RecyclerView.ViewHolder {


        private TextView txt_quantity, txt_item_detail, txt_price;


        public VHItem(View itemView) {
            super(itemView);


            txt_item_detail = itemView.findViewById(R.id.txt_item_detail);
            txt_quantity = itemView.findViewById(R.id.txt_quantity);
            txt_price = itemView.findViewById(R.id.txt_price);



        }
    }
}
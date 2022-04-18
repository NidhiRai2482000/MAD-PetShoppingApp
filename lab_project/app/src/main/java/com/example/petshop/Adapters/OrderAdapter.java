package com.example.petshop.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petshop.Buttons;
import com.example.petshop.CancelOrder;
import com.example.petshop.DatabaseHelper;
import com.example.petshop.Detail;
import com.example.petshop.Models.OrderModel;
import com.example.petshop.Orders;
import com.example.petshop.R;

import java.util.ArrayList;


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.viewholder> {
    ArrayList<OrderModel> list;
    Context context;

    public OrderAdapter(ArrayList<OrderModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_order, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        final OrderModel model=list.get(position);
        holder.image.setImageResource(model.getImage());
        holder.address.setText(model.getAddress());
        holder.phone.setText(model.getContact());
        holder.orderno.setText(model.getOrderno());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CancelOrder.class);
                intent.putExtra("image",model.getImage());
                intent.putExtra("address",model.getAddress());
                intent.putExtra("contact",model.getContact());
                intent.putExtra("orderno",model.getOrderno());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView address,phone, orderno;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.oimage);
            address = itemView.findViewById(R.id.address);
            phone = itemView.findViewById(R.id.phone);
            orderno = itemView.findViewById(R.id.orderno);
        }
    }
}

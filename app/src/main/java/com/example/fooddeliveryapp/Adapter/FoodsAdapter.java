package com.example.fooddeliveryapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.fooddeliveryapp.Activity.FoodDetailActivity;
import com.example.fooddeliveryapp.Model.FoodsModel;
import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.databinding.SrdFoodsBinding;

import java.util.ArrayList;

public class FoodsAdapter extends RecyclerView.Adapter<FoodsAdapter.FoodsViewHolder> {
    ArrayList<FoodsModel> foodsModelArrayList;
    Context context;
    
    public FoodsAdapter(ArrayList<FoodsModel> foodsModelArrayList, Context context) {
        this.foodsModelArrayList = foodsModelArrayList;
        this.context = context;
    }
    
    @NonNull
    @Override
    public FoodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.srd_foods, parent, false);
        return new FoodsViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull FoodsViewHolder holder, int position) {
        holder.binding.tvFoodTtile.setText(foodsModelArrayList.get(position).getTitle());
        holder.binding.tvPrice.setText("₹" + foodsModelArrayList.get(position).getPrice());
        holder.binding.tvStarText.setText("" + foodsModelArrayList.get(position).getStar());
        holder.binding.tvTime.setText(foodsModelArrayList.get(position).getTimeValue() + "min");
        Glide.with(context).load(foodsModelArrayList.get(position).getImagePath()).transform(new CenterCrop(), new RoundedCorners(30)).into(holder.binding.pic);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, FoodDetailActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("object",foodsModelArrayList.get(position));
            context.startActivity(intent);
        });
    }
    
    @Override
    public int getItemCount() {
        return foodsModelArrayList.size();
    }
    
    public class FoodsViewHolder extends RecyclerView.ViewHolder {
        SrdFoodsBinding binding;
        
        public FoodsViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = SrdFoodsBinding.bind(itemView);
        }
    }
}

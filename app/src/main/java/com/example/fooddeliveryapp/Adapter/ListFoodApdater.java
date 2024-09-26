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
import com.example.fooddeliveryapp.databinding.SrdListFoodBinding;

import java.util.ArrayList;

public class ListFoodApdater extends RecyclerView.Adapter<ListFoodApdater.ListFoodViewholder>{
    ArrayList<FoodsModel> foodsModelArrayList;
    Context context;
    
    public ListFoodApdater() {
    }
    
    public ListFoodApdater(ArrayList<FoodsModel> foodsModelArrayList, Context context) {
        this.foodsModelArrayList = foodsModelArrayList;
        this.context = context;
    }
    
    @NonNull
    @Override
    public ListFoodViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.srd_list_food,parent,false);
        return new ListFoodViewholder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull ListFoodViewholder holder, int position) {
        holder.binding.tvTitleListFoodSrd.setText(foodsModelArrayList.get(position).getTitle());
        holder.binding.tvTimeListFood.setText(foodsModelArrayList.get(position).getTimeValue()+" min");
        holder.binding.tvPriceListFood.setText("₹"+foodsModelArrayList.get(position).getPrice());
        holder.binding.tvRating.setText(""+foodsModelArrayList.get(position).getStar());
        Glide.with(context).load(foodsModelArrayList.get(position).getImagePath())
                .transform(new CenterCrop(),new RoundedCorners(30))
                .into(holder.binding.srdFoodImage);
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
    
    public class ListFoodViewholder extends RecyclerView.ViewHolder{
        SrdListFoodBinding binding;
        public ListFoodViewholder(@NonNull View itemView) {
           
            super(itemView);
            binding = SrdListFoodBinding.bind(itemView);
        }
    }
}

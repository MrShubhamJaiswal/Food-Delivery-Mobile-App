package com.example.fooddeliveryapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.fooddeliveryapp.Activity.ListFoodsActivity;
import com.example.fooddeliveryapp.Model.CategoryModel;
import com.example.fooddeliveryapp.Model.FoodsModel;
import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.databinding.SrdCategoryBinding;
import com.example.fooddeliveryapp.databinding.SrdFoodsBinding;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    ArrayList<CategoryModel> categoryModelArrayList;
    Context context;
    
    public CategoryAdapter(ArrayList<CategoryModel> categoryModelArrayList, Context context) {
        this.categoryModelArrayList = categoryModelArrayList;
        this.context = context;
    }
    
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.srd_category, parent, false);
        return new CategoryViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.tvCategoryName.setText(categoryModelArrayList.get(position).getName());
        switch (position){
            case 0: {
                holder.binding.imgCategory.setBackgroundResource(R.drawable.bg_cat_0);
                break;
            }case 1: {
                holder.binding.imgCategory.setBackgroundResource(R.drawable.bg_cat_1);
                break;
            }case 2: {
                holder.binding.imgCategory.setBackgroundResource(R.drawable.bg_cat_2);
                break;
            }case 3: {
                holder.binding.imgCategory.setBackgroundResource(R.drawable.bg_cat_3);
                break;
            }case 4: {
                holder.binding.imgCategory.setBackgroundResource(R.drawable.bg_cat_4);
                break;
            }case 5: {
                holder.binding.imgCategory.setBackgroundResource(R.drawable.bg_cat_5);
                break;
            }case 6: {
                holder.binding.imgCategory.setBackgroundResource(R.drawable.bg_cat_6);
                break;
            }case 7: {
                holder.binding.imgCategory.setBackgroundResource(R.drawable.bg_cat_7);
                break;
            }
        }
        int drawableResourceId = context.getResources().getIdentifier(categoryModelArrayList.get(position).getImagePath(),
                "drawable",holder.itemView.getContext().getPackageName());
        Glide.with(context).load(drawableResourceId).into(holder.binding.imgCategory);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListFoodsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("CategoryKiId",categoryModelArrayList.get(position).getId());
                intent.putExtra("CategoryName",categoryModelArrayList.get(position).getName());
                
                context.startActivity(intent);
            }
        });
    }
    
    @Override
    public int getItemCount() {
        return categoryModelArrayList.size();
    }
    
    
    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        SrdCategoryBinding binding;
        
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = SrdCategoryBinding.bind(itemView);
        }
    }
}

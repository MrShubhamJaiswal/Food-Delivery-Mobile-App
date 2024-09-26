package com.example.fooddeliveryapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.fooddeliveryapp.Helper.ChangeNumberItemsListener;
import com.example.fooddeliveryapp.Helper.ManagmentCart;
import com.example.fooddeliveryapp.Model.FoodsModel;
import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.databinding.SrdCartBinding;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    ArrayList<FoodsModel> foodsModelArrayList;
    Context context;
    ChangeNumberItemsListener changeNumberItemsListener;
    ManagmentCart managmentCart;
    
    public CartAdapter() {
    }
    
    public CartAdapter(ArrayList<FoodsModel> foodsModelArrayList, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.foodsModelArrayList = foodsModelArrayList;
        this.context = context;
        this.changeNumberItemsListener = changeNumberItemsListener;
        managmentCart = new ManagmentCart(context);
    }
    
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.srd_cart,parent,false);
        return new CartViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.binding.tvTitleCartSrd.setText(foodsModelArrayList.get(position).getTitle());
        holder.binding.tvPriceCartSrd.setText("₹"+foodsModelArrayList.get(position).getNumberInCart()*foodsModelArrayList.get(position).getPrice());
        holder.binding.tvBasicPriceCartSrd.setText(foodsModelArrayList.get(position).getNumberInCart()+" X ₹"+
                foodsModelArrayList.get(position).getPrice());
        holder.binding.tvQuantityNumberCartSrd.setText(foodsModelArrayList.get(position).getNumberInCart()+"");
        Glide.with(context).load(foodsModelArrayList.get(position).getImagePath())
                .transform(new CenterCrop(),new RoundedCorners(30))
                .into(holder.binding.imgFoodCartSrd);
        holder.binding.tvPlusCartSrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managmentCart.plusNumberItem(foodsModelArrayList, position, new ChangeNumberItemsListener() {
                    @Override
                    public void change() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.change();
                    }
                });
            
            }
        });
        holder.binding.tvMinusCartSrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managmentCart.minusNumberItem(foodsModelArrayList, position, new ChangeNumberItemsListener() {
                    @Override
                    public void change() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.change();
                    
                    }
                });
            
            }
        });
    }
    
    @Override
    public int getItemCount() {
        return foodsModelArrayList.size();
    }
    
    public class CartViewHolder extends RecyclerView.ViewHolder{
        SrdCartBinding binding;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = SrdCartBinding.bind(itemView);
            
        }
    }
}

package com.example.fooddeliveryapp.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.fooddeliveryapp.Helper.ManagmentCart;
import com.example.fooddeliveryapp.Model.FoodsModel;
import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.databinding.ActivityFoodDetailBinding;

public class FoodDetailActivity extends BaseActivity {
    ActivityFoodDetailBinding binding;
    private FoodsModel object;
    int num =1;
    ManagmentCart managmentCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityFoodDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getIntentExtra();
        setVariable();
    }
    
    private void setVariable() {
        managmentCart = new ManagmentCart(this);
        binding.imgBackFoodDetail.setOnClickListener(v -> finish());
        Glide.with(FoodDetailActivity.this)
                .load(object.getImagePath()).into(binding.imgFoodDetail);
        binding.tvTitleFoodDetail.setText(object.getTitle());
        binding.tvPriceFoodDetail.setText("₹"+object.getPrice());
        binding.tvDescriptionFoodDetail.setText(object.getDescription());
        binding.ratingBar.setRating((float) object.getStar());
        binding.tvTotalPriceFoodDetail.setText("₹"+num*object.getPrice());
        binding.tvTimeFoodDetail.setText(object.getTimeValue()+"min");
        binding.tvPlusFoodDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = num+1;
                binding.tvQuantityNumberFoodDetail.setText(num+"");
                binding.tvTotalPriceFoodDetail.setText("₹"+num*object.getPrice());
            }
        });
        binding.tvMinusFoodDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num>1){
                    num = num-1;
                    binding.tvQuantityNumberFoodDetail.setText(num+"");
                    binding.tvTotalPriceFoodDetail.setText("₹"+num*object.getPrice());
                }
                
            }
        });
        binding.btnAddToCartFoodDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            object.setNumberInCart(num);
            managmentCart.insertFood(object);
            
            }
        });
    }
    
    private void getIntentExtra() {
        object = (FoodsModel) getIntent().getSerializableExtra("object");
    }
}
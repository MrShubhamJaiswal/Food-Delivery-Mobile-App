package com.example.fooddeliveryapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.fooddeliveryapp.Adapter.CartAdapter;
import com.example.fooddeliveryapp.Helper.ChangeNumberItemsListener;
import com.example.fooddeliveryapp.Helper.ManagmentCart;
import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.databinding.ActivityCartBinding;

public class CartActivity extends AppCompatActivity {
    ActivityCartBinding binding;
    ManagmentCart managmentCart;
    CartAdapter adapter;
    Double tax;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        
        managmentCart = new ManagmentCart(this);
        setVariable();
        calculateCart();
        initList();
    }
    
    private void initList() {
        if (managmentCart.getListCart().isEmpty()){
            binding.tvEmpty.setVisibility(View.VISIBLE);
            binding.scrollviewCart.setVisibility(View.GONE);
        }else {
            binding.tvEmpty.setVisibility(View.GONE);
            binding.scrollviewCart.setVisibility(View.VISIBLE);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        binding.recCart.setLayoutManager(linearLayoutManager);
        adapter = new CartAdapter(managmentCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void change() {
                calculateCart();
                
            }
        });
        binding.recCart.setAdapter(adapter);
    }
    
    private void calculateCart() {
        double percatgeTax = 0.02;
        double delivery = 30;
        tax = (double) (Math.round(managmentCart.getTotalFee()*percatgeTax*100.0)/100);
        double total = Math.round((managmentCart.getTotalFee()+tax+delivery)*100)/100;
        double itemTotal = Math.round(managmentCart.getTotalFee() *100)/100;
        
        binding.tvSubtotalCart.setText("₹"+itemTotal);
        binding.tvDeliveryCart.setText("₹"+delivery);
        binding.tvTotalTaxCart.setText("₹"+tax);
        binding.tvTotalPriceCart.setText("₹"+total);
    }
    
    private void setVariable() {
        binding.imgBackCart.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        });
    }
}
package com.example.fooddeliveryapp.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.fooddeliveryapp.Adapter.ListFoodApdater;
import com.example.fooddeliveryapp.Model.FoodsModel;
import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.databinding.ActivityListFoodsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListFoodsActivity extends BaseActivity {
    ActivityListFoodsBinding binding;
    private ListFoodApdater listFoodApdater;
    private int categoryId;
    private String categoryName;
    private String searchText;
    private boolean isSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityListFoodsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        
        getIntentExtra();
        initList();
    }
    private void getIntentExtra() {
        categoryId = getIntent().getIntExtra("CategoryKiId",2);
        categoryName = getIntent().getStringExtra("CategoryName");
        searchText = getIntent().getStringExtra("text");
        isSearch = getIntent().getBooleanExtra("isSearch",false);
        binding.tvTitleListFood.setText(categoryName);
        binding.backbtn.setOnClickListener(v -> {
            finish();
        });
    }
    private void initList() {
        DatabaseReference myRef = database.getReference("Foods");
        binding.progressBarListFood.setVisibility(View.VISIBLE);
        ArrayList<FoodsModel> foodsModelArrayList = new ArrayList<>();
        Query query;
        if (isSearch) {
            query = myRef.orderByChild("Title").startAt(searchText).endAt(searchText+'\uf8ff');
        }else {
            query = myRef.orderByChild("CategoryId").equalTo(categoryId);
        }
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot snapshot1: snapshot.getChildren()){
                        foodsModelArrayList.add(snapshot1.getValue(FoodsModel.class));
                    }
                    if (foodsModelArrayList.size()>0){
                        binding.recListFood.setLayoutManager(new GridLayoutManager(ListFoodsActivity.this,2));
                        listFoodApdater = new ListFoodApdater(foodsModelArrayList,getApplicationContext());
                        binding.recListFood.setAdapter(listFoodApdater);
                    }
                    binding.progressBarListFood.setVisibility(View.GONE);
                }
            }
            
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            
            }
        });
    }
    
    
}
package com.example.fooddeliveryapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.fooddeliveryapp.Adapter.CategoryAdapter;
import com.example.fooddeliveryapp.Adapter.FoodsAdapter;
import com.example.fooddeliveryapp.Model.CategoryModel;
import com.example.fooddeliveryapp.Model.FoodsModel;
import com.example.fooddeliveryapp.Model.LocationModel;
import com.example.fooddeliveryapp.Model.PriceModel;
import com.example.fooddeliveryapp.Model.TimeModel;
import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        
        initLocation();
        initTime();
        initBestFood();
        initCategory();
        initLogout();
        initSearch();
    }
    
   
    
    private void initLocation() {
        DatabaseReference myRef = database.getReference("Location");
        ArrayList<LocationModel> locationModelArrayList = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot snapshot1:snapshot.getChildren()){
                        locationModelArrayList.add(snapshot1.getValue(LocationModel.class));
                    }
                    ArrayAdapter<LocationModel> adapter = new ArrayAdapter<>(MainActivity.this,R.layout.srd_location,locationModelArrayList);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spinnerLocation.setAdapter(adapter);
                }
            }
            
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            
            }
        });
    }
    private void initTime() {
        DatabaseReference myRef = database.getReference("Time");
        ArrayList<TimeModel> locationModelArrayList = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot snapshot1:snapshot.getChildren()){
                        locationModelArrayList.add(snapshot1.getValue(TimeModel.class));
                    }
                    ArrayAdapter<TimeModel> adapter = new ArrayAdapter<>(MainActivity.this,R.layout.srd_location,locationModelArrayList);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spinnerTime.setAdapter(adapter);
                }
            }
            
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            
            }
        });
    }
    private void initBestFood() {
        DatabaseReference myRef = database.getReference("Foods");
        binding.progressBar.setVisibility(View.VISIBLE);
        ArrayList<FoodsModel> foodsModelArrayList = new ArrayList<>();
        Query query = myRef.orderByChild("BestFood").equalTo(true);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot snapshot1: snapshot.getChildren()){
                        foodsModelArrayList.add(snapshot1.getValue(FoodsModel.class));
                    }
                    if (foodsModelArrayList.size() > 0) {
                        
                        binding.recViewBestFood.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
                        FoodsAdapter adapter = new FoodsAdapter(foodsModelArrayList,getApplicationContext());
                        binding.recViewBestFood.setAdapter(adapter);
                    }
                    binding.progressBar.setVisibility(View.GONE);
                }
            }
            
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            
            }
        });
    }
    private void initCategory() {
        DatabaseReference myRef = database.getReference("Category");
        binding.progressBarCategory.setVisibility(View.VISIBLE);
        ArrayList<CategoryModel> categoryModelArrayList = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot snapshot1: snapshot.getChildren()){
                        categoryModelArrayList.add(snapshot1.getValue(CategoryModel.class));
                    }
                    if (categoryModelArrayList.size() > 0) {
                        
                        binding.recViewCategory.setLayoutManager(new GridLayoutManager(MainActivity.this,4));
                        CategoryAdapter adapter = new CategoryAdapter(categoryModelArrayList,getApplicationContext());
                        binding.recViewCategory.setAdapter(adapter);
                    }
                    binding.progressBarCategory.setVisibility(View.GONE);
                }
            }
            
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            
            }
        });
    }
    
    private void initLogout() {
        binding.imgLogoutMain.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        });
    }
    
    private void initSearch() {
        binding.searchBtn.setOnClickListener(v -> {
            String text = binding.edtSearchBar.getText().toString();
            if (!text.isEmpty()){
                Intent intent = new Intent(getApplicationContext(),ListFoodsActivity.class);
                intent.putExtra("text",text);
                intent.putExtra("isSearch",true);
                startActivity(intent);
            }
        });
        binding.imgCartMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CartActivity.class));
                
            }
        });
        
    }
    
}
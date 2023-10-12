package com.posen82645project.pointofsales;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity {

    Button viewStock, newBill, addStock, addProduct, addCategory, viewCategory, viewProduct, refund, reports, addPromo, viewPromo, logout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewStock = findViewById(R.id.view_stock_button);
        newBill=findViewById(R.id.new_bill_button);
        addStock=findViewById(R.id.addStockButton);
        addProduct=findViewById(R.id.addProductButton);
        addCategory=findViewById(R.id.addCategoryButton);
        viewStock=findViewById(R.id.view_stock_button);
        viewCategory=findViewById(R.id.viewCategoryButton);
        viewProduct=findViewById(R.id.viewProductButton);
        refund=findViewById(R.id.returnRefundButton);
        reports=findViewById(R.id.report_Button);
        addPromo=findViewById(R.id.addPromoButton);
        viewPromo=findViewById(R.id.viewPromoButton);
        logout=findViewById(R.id.logOutButton);


        addCategory.setOnClickListener(view -> {
            Intent toAddCatActivity =new Intent(Main.this, AddCategoryactivity.class);
            startActivity(toAddCatActivity);
        });

        viewCategory.setOnClickListener(view -> {
            Intent toViewCategoryActivity = new Intent(Main.this, ViewCategoryActivity.class);
            startActivity(toViewCategoryActivity);
        });

        addProduct.setOnClickListener(view -> {
            Intent toAddProdActivity = new Intent(Main.this, AddProductActivity.class);
            startActivity(toAddProdActivity);
        });

        addProduct.setOnClickListener(view -> {
            Intent toAddProdActivity = new Intent(Main.this, AddProductActivity.class);
            startActivity(toAddProdActivity);
        });

        viewProduct.setOnClickListener(view -> {
            Intent toViewProdActivity = new Intent(Main.this, ViewProductActivity.class);
            startActivity(toViewProdActivity);
        });




    }//end of on create
}
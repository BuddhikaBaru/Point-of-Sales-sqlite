package com.posen82645project.pointofsales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewProductActivity extends AppCompatActivity {

    ListView productList;
    ArrayList<String> titles =new ArrayList<>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);

        productList=findViewById(R.id.productList);

        SQLiteDatabase mydatabase = openOrCreateDatabase("minipos", Context.MODE_PRIVATE,null);
        final Cursor cursorProducts =mydatabase.rawQuery("SELECT * FROM producttable", null);
        int id = cursorProducts.getColumnIndex("product_id");
        int productName=cursorProducts.getColumnIndex("product");
        int catName=cursorProducts.getColumnIndex("category");
        int prodDescript = cursorProducts.getColumnIndex("productdescription");
        titles.clear();

        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, titles);
        productList.setAdapter(arrayAdapter);

        final ArrayList<prodClass> prodList = new ArrayList<prodClass>();

        if (cursorProducts.moveToFirst()) {
            do {
                prodClass prodObj = new prodClass();
                prodObj.id = cursorProducts.getString(id);
                prodObj.productName = cursorProducts.getString(productName);
                prodObj.category = cursorProducts.getString(catName);
                prodObj.prodDescript = cursorProducts.getString(prodDescript);
                prodList.add(prodObj);
                titles.add(cursorProducts.getString(id) + "\t\t" + cursorProducts.getString(productName) + "\t\t" + cursorProducts.getString(catName) + "\t\t" + cursorProducts.getString(prodDescript));

            } while (cursorProducts.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            productList.invalidateViews();


        }

    }
}
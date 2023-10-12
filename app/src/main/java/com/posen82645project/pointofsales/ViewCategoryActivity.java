package com.posen82645project.pointofsales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewCategoryActivity extends AppCompatActivity {

    ListView categoryList;
    ArrayList<String> titles = new ArrayList<>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_category);

        categoryList=findViewById(R.id.categoriesList);

        SQLiteDatabase mydatabase = openOrCreateDatabase("minipos", Context.MODE_PRIVATE,null);
        final Cursor cursorCategories =mydatabase.rawQuery("SELECT * FROM categorytable", null);
        int id = cursorCategories.getColumnIndex("category_id");
        int categoryName=cursorCategories.getColumnIndex("category");
        int catDescript = cursorCategories.getColumnIndex("categorydescription");
        titles.clear();

        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, titles);
        categoryList.setAdapter(arrayAdapter);

        final ArrayList<categor> categorList = new ArrayList<categor>();
        if (cursorCategories.moveToFirst()){

            do {
                categor cats = new categor();
                cats.id = cursorCategories.getString(id);
                cats.categoryName = cursorCategories.getString(categoryName);
                cats.catDescript = cursorCategories.getString(catDescript);
                categorList.add(cats);
                titles.add(cursorCategories.getString(id)+"\t\t"+cursorCategories.getString(categoryName)+"\t\t"+cursorCategories.getString(catDescript));

            }while (cursorCategories.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            categoryList.invalidateViews();

        }


        categoryList.setOnItemClickListener((adapterView, view, i, l) -> {
            String temp1=titles.get(i).toString();
            categor cats = categorList.get(i);
            Intent intentToEditCats =new Intent(getApplicationContext(), EditDeleteCategory.class);
            intentToEditCats.putExtra("id",cats.id);
            intentToEditCats.putExtra("category",cats.categoryName);
            intentToEditCats.putExtra("description",cats.catDescript);
            startActivity(intentToEditCats);


        });










    }
}
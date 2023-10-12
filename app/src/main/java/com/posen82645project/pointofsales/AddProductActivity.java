package com.posen82645project.pointofsales;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddProductActivity extends AppCompatActivity {

    EditText newProdText, newProdDescriptText;
    Button addProductButton, cancelProductButton;
    Spinner catSnip;
    ArrayList<String> titles=new ArrayList<>();

    ArrayAdapter arrayAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        newProdText = findViewById(R.id.newProduct);
        newProdDescriptText = findViewById(R.id.newProductDescription);
        addProductButton=findViewById(R.id.addProdDbButton);
        cancelProductButton=findViewById(R.id.cancelProdButton);
        catSnip=findViewById(R.id.catSpinner);



        SQLiteDatabase mydatabase = openOrCreateDatabase("minipos", Context.MODE_PRIVATE,null);
        final Cursor cursorCategories =mydatabase.rawQuery("SELECT * FROM categorytable", null);
        int categoryName=cursorCategories.getColumnIndex("category");
        titles.clear();

        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, titles);
        catSnip.setAdapter(arrayAdapter);

        final ArrayList<categor> categorList = new ArrayList<>();
        if (cursorCategories.moveToFirst()){

            do {
                categor cats = new categor();

                cats.categoryName = cursorCategories.getString(categoryName);

                categorList.add(cats);
                titles.add(cursorCategories.getString(categoryName));


            }while (cursorCategories.moveToNext());
            arrayAdapter.notifyDataSetChanged();

        }

        addProductButton.setOnClickListener(view -> insertProduct());


        cancelProductButton.setOnClickListener(view -> {
            Intent toMainActivity =new Intent(AddProductActivity.this, Main.class);
            startActivity(toMainActivity);
        });

    }
    //end of on create method

    public void insertProduct(){

        try {
            String prodName= newProdText.getText().toString();
            String productDescription=newProdDescriptText.getText().toString();
            String category=catSnip.getSelectedItem().toString();

            SQLiteDatabase mydatabase = openOrCreateDatabase("minipos", Context.MODE_PRIVATE,null);
            mydatabase.execSQL("CREATE TABLE IF NOT EXISTS producttable(product_id INTEGER PRIMARY KEY AUTOINCREMENT, product VARCHAR, category VARCHAR, productdescription VARCHAR)");


            String sqlAddProduct= "INSERT INTO producttable (product, category, productdescription)VALUES(?,?,?)";
            SQLiteStatement statement = mydatabase.compileStatement(sqlAddProduct);
            statement.bindString(1, prodName);
            statement.bindString(2, category);
            statement.bindString(3, productDescription);
            statement.execute();
            Toast.makeText(this,"Product Added", Toast.LENGTH_LONG).show();
            newProdText.setText("");
            newProdDescriptText.setText("");
            newProdText.requestFocus();


        } catch (Exception exception){
        }//end try catch


    }
}
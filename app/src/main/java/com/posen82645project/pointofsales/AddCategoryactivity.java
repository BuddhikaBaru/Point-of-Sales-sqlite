package com.posen82645project.pointofsales;

import androidx.appcompat.app.AppCompatActivity;

//import android.annotation.SuppressLint;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCategoryactivity extends AppCompatActivity {

    EditText newCategoryLabal, newCategoryDescription;
    Button addCatButton, addCategoryCancelButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        newCategoryLabal =findViewById(R.id.newCategory);
        newCategoryDescription = findViewById(R.id.newCategoryDescription);
        addCatButton = findViewById(R.id.addCategoryDbButton);
        addCategoryCancelButton=findViewById(R.id.cancelCategoryButton);

        addCategoryCancelButton.setOnClickListener(view -> {
            Intent i = new Intent(AddCategoryactivity.this, Main.class);
            startActivity(i);
        });

        addCatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
            }
        });
    }

    public void insert(){
        try {
            String category= newCategoryLabal.getText().toString();
            String categoryDescription=newCategoryDescription.getText().toString();
            SQLiteDatabase mydatabase = openOrCreateDatabase("minipos", Context.MODE_PRIVATE,null);
            mydatabase.execSQL("CREATE TABLE IF NOT EXISTS categorytable(category_id INTEGER PRIMARY KEY AUTOINCREMENT, category VARCHAR, categorydescription VARCHAR)");


            String sqlAddCategory= "INSERT INTO categorytable (category, categorydescription)VALUES(?,?)";
            SQLiteStatement statement = mydatabase.compileStatement(sqlAddCategory);
            statement.bindString(1, category);
            statement.bindString(2, categoryDescription);
            statement.execute();
            Toast.makeText(this,"Category Added", Toast.LENGTH_LONG).show();
            newCategoryLabal.setText("");
            newCategoryDescription.setText("");
            newCategoryLabal.requestFocus();


        } catch (Exception exception){
        }//end try catch
    }
}
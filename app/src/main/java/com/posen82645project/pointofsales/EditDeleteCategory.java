package com.posen82645project.pointofsales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditDeleteCategory extends AppCompatActivity {

    EditText catId, editCat, editCatDest;
    Button updateCatButton, deleteCatButton,backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete_category);

        catId=findViewById(R.id.categoryID);
        editCat=findViewById(R.id.editCategory);
        editCatDest=findViewById(R.id.editCategoryDescription);
        updateCatButton=findViewById(R.id.updateCategoryButton);
        deleteCatButton=findViewById(R.id.deleteCategoryButton);
        backButton=findViewById(R.id.toViewCatButton);

        Intent intentToEditCats = getIntent();
        String id  = intentToEditCats.getStringExtra("id").toString();
        String categoryName = intentToEditCats.getStringExtra("category").toString();
        String description = intentToEditCats.getStringExtra("description").toString();

        catId.setText(id);
        editCat.setText(categoryName);
        editCatDest.setText(description);




         updateCatButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 editCategory();
             }
         });

         deleteCatButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 deleteCat();

             }
         });

         backButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent i = new Intent(EditDeleteCategory.this, ViewCategoryActivity.class);
                 startActivity(i);
             }
         });



    }//endOncreate state

//delete category function declaration
    public void deleteCat(){

        try {

            String id= catId.getText().toString();



            SQLiteDatabase mydatabase = openOrCreateDatabase("minipos", Context.MODE_PRIVATE,null);

            String sqlAddCategory= "delete from categorytable where category_id = ?";
            SQLiteStatement statement = mydatabase.compileStatement(sqlAddCategory);
            statement.bindString(1, id);
            statement.execute();
            Toast.makeText(this,"Category Deleted", Toast.LENGTH_LONG).show();
            Intent i = new Intent(EditDeleteCategory.this, ViewCategoryActivity.class);
            startActivity(i);


        }catch (Exception ex){
            Toast.makeText(this,"Category Deletion Failed!", Toast.LENGTH_LONG).show();
        };



    }




    //start category function declaration

    public void editCategory(){

        try {

            String id= catId.getText().toString();
            String categoryNameNew = editCat.getText().toString();
            String descriptionNew=editCatDest.getText().toString();
            SQLiteDatabase mydatabase = openOrCreateDatabase("minipos", Context.MODE_PRIVATE,null);

            String sqlAddCategory= "update categorytable set category=?, categorydescription=? where category_id = ?";
            SQLiteStatement statement = mydatabase.compileStatement(sqlAddCategory);
            statement.bindString(1, categoryNameNew);
            statement.bindString(2, descriptionNew);
            statement.bindString(3, id);
            statement.execute();
            Toast.makeText(this,"Category Updated", Toast.LENGTH_LONG).show();
            Intent i = new Intent(EditDeleteCategory.this, ViewCategoryActivity.class);
            startActivity(i);


        }catch (Exception ex){
            Toast.makeText(this,"Category Failed!", Toast.LENGTH_LONG).show();
        };



    }
}
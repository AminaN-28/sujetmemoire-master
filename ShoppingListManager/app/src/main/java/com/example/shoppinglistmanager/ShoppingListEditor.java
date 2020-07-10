package com.example.shoppinglistmanager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ShoppingListEditor extends AppCompatActivity {
    ImageView img_Art;
    Button choose_btn, addnextart, createlist;
    CheckBox checkBox;
    EditText articleNAME, quantity, amount;
    static ArticleBase databaseArt;
    final int REQUEST_CODE_GALLERY = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list_editor);


        img_Art = findViewById(R.id.image1);
        amount = findViewById(R.id.amount);
        quantity =findViewById(R.id.quantity);
        articleNAME = findViewById(R.id.article);
        addnextart = findViewById(R.id.nxtbtn);
        createlist = findViewById(R.id.createBTN);
        choose_btn =findViewById(R.id.choosebtn);


        databaseArt = new ArticleBase(this,"article.db",null,1);
        databaseArt.queryData("CREATE TABLE IF NOT EXISTS ARTICLEL (Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "article VARCHAR, amount VARCHAR,quantity INTEGER, image BLOB)");


        choose_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(ShoppingListEditor.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_GALLERY

                );
            }
        });

    addnextart.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                databaseArt.insertData(
                        articleNAME.getText().toString().trim(),
                        quantity.getText().toString().trim(),
                        amount.getText().toString().trim(),
                        imageToconvert(img_Art)
                );
                Toast.makeText(ShoppingListEditor.this,"Successfully Added", Toast.LENGTH_LONG).show();

                articleNAME.setText("");
                amount.setText("");
                quantity.setText("");
                img_Art.setImageResource(R.drawable.ic_image_black_24dp);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    });

    createlist.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ShoppingListEditor.this,ShowListArticle.class);
            startActivity(intent);
        }
    });
    }

    public static byte[] imageToconvert(ImageView image) {
        Bitmap bitmap =((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytearray = stream.toByteArray();
        return bytearray;

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode ==REQUEST_CODE_GALLERY ){
            if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent =new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(ShoppingListEditor.this, "YOU DON'T HAVE PERMISSION TO ACCESS FILE LOCATION",
                        Toast.LENGTH_LONG).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode ==REQUEST_CODE_GALLERY && resultCode == RESULT_OK  && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                img_Art.setImageBitmap(bitmap);
            }catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}

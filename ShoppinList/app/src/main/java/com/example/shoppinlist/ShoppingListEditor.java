package com.example.shoppinlist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;

public class ShoppingListEditor extends AppCompatActivity {


    ImageView img_Art;
    Button choose_btn;
    CheckBox checkBox;
    EditText articleNAME, quantity, amount;

    public static ArticleBD databaseArt;



    private static final int IMG_PICk_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;


    Button createbtn, nextArticle_btn;

   /* public void getArticle(){
        //String rv = "not found";
        SQLiteDatabase db = base_Shoppinglist.getReadableDatabase();
        String[] article = {Article.article, Article.amount, Article.image,Article.quantity};
        Cursor cursor = (Cursor) db.query(Article.Table_Name, article,null, null,null, null,null);
        while (cursor.moveToNext()){
            String elemnt = cursor.getString(0);
            byte[] image = cursor.getBlob(2);
            Toast.makeText(ShoppingListEditor.this,"Récupérée avec succes",Toast.LENGTH_LONG).show();
        }
    }*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list_editor);



        checkBox = findViewById(R.id.checkbox_id);

        //VIEWS
        articleNAME= findViewById(R.id.article);
        quantity= findViewById(R.id.quantity);
        amount=findViewById(R.id.amount);
        img_Art = findViewById(R.id.image1);
        choose_btn = findViewById(R.id.choosebtn);
        databaseArt = new ArticleBD(this,"shoppinglist.db", null ,1);
         databaseArt.queryData("CREATE TABLE IF NOT EXISTS SHOPPINGLIST (article Text,image Blob, quantity INTEGER,amount INTEGER)");


        //handle button choose on click
        //choisir au clic
        choose_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//com.example.shoppinlist
                //check runtime permission
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED) {
                        //permission not granted, request it. //permission non autorisée, la demander.
                        String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};

                        //show popup for runtime permission
                        requestPermissions(permission, PERMISSION_CODE);

                    } else {
                        //permission already granted //permission deja autorisee

                        pickImageFromGallery();

                    }
                } else {
                    //system os is less then marshmallow

                    pickImageFromGallery();
                }

            }
        });





        nextArticle_btn = findViewById(R.id.nxtbtn);
        nextArticle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//BASE DE DONNEES DE 'ADD A SHOPPING LIST' SAUVEGARDE DE DONNEES
                //NB: si cette partie nest pas mis dans un bouton alors l'appli va cracheron aura cette erreur: Unable to start activity
                // ComponentInfo{com.example.shoppinlist/com.example.shoppinlist.ShoppingListEditor}: java.lang.NullPointerException:
                // Attempt to invoke virtual method 'java.lang.String java.lang.Object.toString()' on a null object reference

                try {
                    databaseArt.insertArticle(
                            articleNAME.getText().toString().trim(),
                            quantity.getText().toString().trim(),
                            amount.getText().toString().trim(),
                            imageToconvert(img_Art)
                    );
                    Toast.makeText(ShoppingListEditor.this,"Article added successfully", Toast.LENGTH_LONG).show();
                    articleNAME.setText("");
                    quantity.setText("");
                    amount.setText("");
                    img_Art.setImageResource(R.drawable.ic_image_black_24dp);

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });


        createbtn = findViewById(R.id.createBTN);

        createbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    databaseArt.insertArticle(
                            articleNAME.getText().toString().trim(),
                            quantity.getText().toString().trim(),
                            amount.getText().toString().trim(),
                            imageToconvert(img_Art)
                    );
                    Toast.makeText(ShoppingListEditor.this,"Article added successfully", Toast.LENGTH_LONG).show();
                    articleNAME.setText("");
                    quantity.setText("");
                    amount.setText("");
                    img_Art.setImageResource(R.drawable.ic_image_black_24dp);

                }catch (Exception e){
                    e.printStackTrace();
                }


                Intent showList_intent = new Intent(ShoppingListEditor.this, ShowListArticle.class);
                startActivity(showList_intent);

            }
        });


    }


    private void pickImageFromGallery() {

        //intent to pick image

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMG_PICk_CODE);
    }


    private byte[] imageToconvert(ImageView imageView) {
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] arraytByte = stream.toByteArray();
        return arraytByte;
    }


    //handle result of runtime permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission was granted
                    pickImageFromGallery();
                } else {
                    //permission was denied
                    Toast.makeText(this, "Permission was denied", Toast.LENGTH_LONG).show();
                }

            }
        }
    }


    //handle result of picked image

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == IMG_PICk_CODE && data != null) {
            //set image to image view
            Uri uri= data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                img_Art.setImageBitmap(bitmap);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
           // img_Art.setImageURI(data.getData());

        }


    }
}


  /*  DateM = findViewById(R.id.DateModif);
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a");
                String formattedDate = df.format(c.getTime());
                DateM.setText(formattedDate);*/


  /*public void addListenerOnButtonClick(){
        //Getting instance of CheckBoxes and Button from the activty_main.xml file
        checkBox=(CheckBox)findViewById(R.id.checkbox_id);
        nextArticle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder result=new StringBuilder();
                result.append("Selected Items:");
                if(checkBox.isChecked()){
                    result.append("\nPizza 100Rs");
                    total_amount +=total_amount+amnt;
                }
            }
        });

    }*/




// ENVOI DES STRINGS A L'ACTIVITE MyListView

      /* Intent goToverify = new Intent(ShoppingListEditor.this, MyListView.class);
        goToverify.putExtra("article",editText.getText().toString());
        goToverify.putExtra("qte", editText1.getText().toString());
        goToverify.putExtra("amount", editText2.getText().toString());
       //goToverify.putExtra("img", img.getImageMatrix().toString());
        startActivity(goToverify);*/
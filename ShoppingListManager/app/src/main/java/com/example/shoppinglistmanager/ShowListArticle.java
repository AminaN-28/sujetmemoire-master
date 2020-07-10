package com.example.shoppinglistmanager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteBlobTooBigException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class ShowListArticle extends AppCompatActivity {

    Button addArt;
    GridView gridView;
   static ArticleBase databaseArt;
   Button okbtn;

    //static ArrayList<String> table = new ArrayList<>();
    ArrayList<ArticleCLASS> ArticleC = new ArrayList<>();

    ArticleAdapter adapter= null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_list_article);

        addArt = findViewById(R.id.addartt);
        gridView =findViewById(R.id.listview1);
        okbtn =findViewById(R.id.oktn);

        adapter =new ArticleAdapter(this,R.layout.article_added__row,ArticleC);
        gridView.setAdapter(adapter);


        //get data from database
       databaseArt = new ArticleBase(this, "article.db",null,1);
        databaseArt.queryData("CREATE TABLE IF NOT EXISTS ARTICLEL (Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "article VARCHAR, amount VARCHAR,quantity INTEGER, image BLOB)");
        ArticleC.clear();
        SQLiteDatabase database;
        database = databaseArt.getWritableDatabase();
        try{
            Cursor cursor = database.query("ARTICLEL",new String[]{"Id", "article", "amount", "quantity", "image"}, null,
                    null,null,null,null,null);
            while (cursor.moveToNext()){
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String amount = cursor.getString(2);
                int qte = cursor.getInt(3);
                byte [] image = cursor.getBlob(4);

                ArticleC.add(new ArticleCLASS(name, amount,qte,image,id));
            }
        } catch (SQLiteBlobTooBigException e) {e.printStackTrace(); }
        adapter.notifyDataSetChanged();

        addArt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addGo = new Intent(ShowListArticle.this,ShoppingListEditor.class);
                startActivity(addGo);
            }
        });

        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lastpageGo = new Intent(ShowListArticle.this, LastPage.class);
                startActivity(lastpageGo);
            }
        });


        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                CharSequence[] items = {"Update", "Delete"};
                AlertDialog.Builder alterDialog = new AlertDialog.Builder(ShowListArticle.this);
                alterDialog.setTitle("Choose an action");

                alterDialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0){
                            //update
                            Cursor c = databaseArt.getData("SELECT Id FROM ARTICLEL");
                            ArrayList<Integer> arrId = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrId.add(c.getInt(0));
                            }
                            //show dialog udpdate
                            showDialogtoUpdate(ShowListArticle.this, arrId.get(position));
                        }
                        else
                        {
                            //databaseArt.queryData("CREATE TABLE IF NOT EXISTS ARTICLEL (Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            //"article VARCHAR, amount VARCHAR,quantity INTEGER, image BLOB)");
                            Cursor c = databaseArt.getData("SELECT Id FROM ARTICLEL");
                            ArrayList<Integer> arrId = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrId.add(c.getInt(0));
                            }
                            showDialogtodelete(arrId.get(position));
                        }
                    }
                });
                alterDialog.show();

                return true;
            }
        });


    }

    ImageView imageViewArticle ;
    private void showDialogtoUpdate(Activity activity , final int position ){
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_article_activity);
        dialog.setTitle("Update");
        final ImageView imageViewArticle = (ImageView) dialog.findViewById(R.id.imageViewarticle);
        final EditText articleName = (EditText) dialog.findViewById(R.id.articlename);
        final EditText articleAmount =(EditText) dialog.findViewById(R.id.articleamount);
        final EditText articleQte=(EditText) dialog.findViewById(R.id.articleqte);
        Button updateBtn = (Button) dialog.findViewById(R.id.button);
        //set width for dialog
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        //set height for dialog
        int height= (int)  (activity.getResources().getDisplayMetrics().heightPixels * 0.7);

        dialog.getWindow().setLayout(width, height);
        dialog.show();

        imageViewArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //request  picture library
                ActivityCompat.requestPermissions(ShowListArticle.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},888);
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    databaseArt.updateData(
                            articleName.getText().toString().trim(),
                            articleAmount.getText().toString().trim(),
                            articleQte.getText().toString().trim(),
                            ShoppingListEditor.imageToconvert(imageViewArticle),
                            position
                    );
                    dialog.dismiss();
                    Toast.makeText(ShowListArticle.this, "Update successfully!!!", Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    Log.e("Update error:", e.getMessage());
                }
                UpdateArticleList();
                UpdateArticleList();
            }
        });

    }

    private void UpdateArticleList(){
        databaseArt = new ArticleBase(this, "article.db",null,1);
        Cursor cursor =databaseArt.getData("SELECT * FROM ARTICLEL");
        ArticleC.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String amount = cursor.getString(2);
            int qte = cursor.getInt(3);
            byte [] image = cursor.getBlob(4);
            ArticleC.add(new ArticleCLASS(name, amount,qte,image,id));
        }
        adapter.notifyDataSetChanged();
    }

    private void showDialogtodelete(final int idArticle){
        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(ShowListArticle.this);
        dialogDelete.setTitle("Warning!!");

        dialogDelete.setMessage("Are You sure you want to this delete");
        dialogDelete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    databaseArt.deleteDate(idArticle);
                    Toast.makeText(ShowListArticle.this, "Delete successfully!!!", Toast.LENGTH_LONG).show();

                }catch (Exception e){
                    Log.e("Delete error:", e.getMessage());
                }
                UpdateArticleList();
            }
        });

        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDelete.show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 888) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 888);
            } else {
                Toast.makeText(ShowListArticle.this, "YOU DON'T HAVE PERMISSION TO ACCESS FILE LOCATION", Toast.LENGTH_LONG).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }


    protected void onActivityResult( int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 888 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageViewArticle.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

}

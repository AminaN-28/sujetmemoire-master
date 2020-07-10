package com.example.shoppinglistmanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ShoppingListShow extends AppCompatActivity {
    GridView liste;
   static ListToShopBD articleBD;

    Button addbtn;
    ArrayList<ListeArticle> listeA = new ArrayList<>();
    ListAdapter adp = null;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.add_note) {
            Intent listShop_intent = new Intent(ShoppingListShow.this, ListEditor.class);//on peut aussi utiliser getApplicationContext()
            //a la place de ShoppingListShow.this
            startActivity(listShop_intent);
            return true;
        }
        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_list_show);
        liste = findViewById(R.id.shopListView);

        adp = new ListAdapter(this, R.layout.list__row,listeA);
        liste.setAdapter(adp);


        //get data from database
        articleBD = new ListToShopBD(this, "shoppinlist.db", null, 1);
        articleBD.queryData("CREATE  TABLE IF NOT EXISTS listedetails (id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                 "listename TEXT, listeamount INTEGER, listedate Date)");
        listeA.clear();
        SQLiteDatabase database;
        database = articleBD.getWritableDatabase();
        try {
            Cursor cursor= database.query("listedetails",new String[]{"id", "listename", "listeamount", "listedate"}, null
            ,null, null, null,null,null);
            while (cursor.moveToNext()){
                int id = cursor.getInt(0);
                String namelist = cursor.getString(1);
                int amount = cursor.getInt(2);
                String date = cursor.getString(3);

                listeA.add(new ListeArticle(namelist, amount,date,id));
            }
        }catch (Exception e){e.printStackTrace();}
        adp.notifyDataSetChanged();

        addbtn = findViewById(R.id.addbtn);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent return_int = new Intent(ShoppingListShow.this, ListEditor.class);
                startActivity(return_int);
            }
        });

        //Pour Supprimer ou modifier une liste (nom date montant)
        liste.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                CharSequence [] choose_Action ={"Update", "Delete"};
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ShoppingListShow.this);
                alertDialog.setTitle("What want You Do?");
                alertDialog.setItems(choose_Action, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0){
                            //update
                            Cursor c = articleBD.getData("SELECT id FROM listedetails");
                            ArrayList<Integer> arrayID =new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrayID.add(c.getInt(0));
                            }
                            //show dialog update
                            showDialogToUpdate(ShoppingListShow.this,arrayID.get(position));
                        }
                        else {
                            //delete
                            Cursor c =articleBD.getData("SELECT id FROM listedetails");
                            ArrayList<Integer> arrayID =new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrayID.add(c.getInt(0));
                            }
                            //show dialog delete
                            showDialogtodelete(arrayID.get(position));
                            adp.notifyDataSetChanged();
                        }
                    }
                });
                alertDialog.show();
                return true;
            }
        });

        //Ouvrir une liste

        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent openlist = new Intent(ShoppingListShow.this, ShowListArticle.class);
                startActivity(openlist);
            }
        });

    }

    private void showDialogToUpdate(Activity activity , final int positon){
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_list_activity);
        dialog.setTitle("Update");
        final EditText listName = (EditText) dialog.findViewById(R.id.listname);
        final EditText listAmount =(EditText) dialog.findViewById(R.id.listamount);
        final EditText listDate=(EditText) dialog.findViewById(R.id.listdate);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a");
        String formattedDate = df.format(c.getTime());
        listDate.setText(formattedDate);
        Button updateBtn = (Button) dialog.findViewById(R.id.button);
        //set width for dialog
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        //set height for dialog
        int height= (int)  (activity.getResources().getDisplayMetrics().heightPixels * 0.95);

        dialog.getWindow().setLayout(width, height);
        dialog.show();



        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    articleBD.updateData(
                            listName.getText().toString().trim(),
                            listAmount.getText().toString().trim(),
                            listDate.getText().toString().trim(),
                            positon
                    );
                    dialog.dismiss();
                    Toast.makeText(ShoppingListShow.this, "Update Successfully",Toast.LENGTH_LONG).show();
                    Intent returnedto = new Intent(ShoppingListShow.this, ShoppingListShow.class);
                    startActivity(returnedto);

                }catch (Exception e) {
                    Log.e("Update error:", e.getMessage());
                }
                UpdateArticleList();
               UpdateArticleList();
            }
        });
    }

    private void UpdateArticleList() {
        articleBD = new ListToShopBD(this,"shoppinlist",null,1);
        Cursor cursor = articleBD.getData("SELECT * FROM listedetails");
        listeA.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int amount = cursor.getInt(2);
            String date = cursor.getString(3);

            listeA.add(new ListeArticle(name,amount,date,id));
        }
        adp.notifyDataSetChanged();
    }

    private void showDialogtodelete(final int idList){
        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(ShoppingListShow.this);
        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage("Are You sure you want to this delete");
        dialogDelete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    articleBD.deleteData(idList);
                    Toast.makeText(ShoppingListShow.this, "Delete successfully!!!", Toast.LENGTH_LONG).show();

                }catch (Exception e){
                    Log.e("Delete error:", e.getMessage());
                }
               UpdateArticleList();
                UpdateArticleList();
            }
        });
        dialogDelete.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDelete.show();

    }
}
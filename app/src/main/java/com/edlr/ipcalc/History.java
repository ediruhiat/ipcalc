package com.edlr.ipcalc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class History extends AppCompatActivity {

    String[] daftar;
    DataHelper dbCenter;

    protected Cursor cursor;
    private ListView listContent = null;

    //Method Instansiasi objek-objek
    public void Instantiate(){
        dbCenter = new DataHelper(this);

        Button btnHistory = findViewById(R.id.clearHistory);
        Button btnBack = findViewById(R.id.btnBack);

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClearPrompt(view);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //Kode utama
        Instantiate();
        RefreshList();
    }

    //Memperbarui item di dalam list view
    private void RefreshList() {
        SQLiteDatabase db = dbCenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM history", null); //mengisi cursor dengan query select
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();

        //Memasukkan data dari database ke dalam array daftar[]
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftar[cc] = "[IPK: "+ cursor.getString(2) + "] - [SKS: " + cursor.getString(1) + "]";
        }

        listContent = findViewById(R.id.historyList);
        listContent.setAdapter(new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, daftar));
        listContent.setSelected(true);
        listContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                final String selection = daftar[position];
                final CharSequence[] dialogitem = {"View Detail", "Delete Data"};
                AlertDialog.Builder builder = new  AlertDialog.Builder(History.this);
                builder.setItems(dialogitem, new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                switch (item) {
                                    case 0:
                                        cursor.moveToPosition(position);
                                        ShowDetail(cursor.getString(2), cursor.getString(1), cursor.getString(3));
                                        break;
                                    case 1:
                                        SQLiteDatabase db = dbCenter.getReadableDatabase();
                                        cursor.moveToPosition(position);
                                        db.execSQL("delete from history where ids = '" + cursor.getString(0) + "'");
                                        //Log.d("TAG", "onClick: "+cursor.getString(0)+"--"+cursor.getString(1)+"--"+cursor.getString(2));
                                        RefreshList();
                                        break;
                                }
                            }
                        });
                builder.create().show();
            }
        });
    }

    //Method Menampilkan detail item history
    private void ShowDetail(String ipk, String sks, String timestamp) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("IPK\t: "+ipk+"\nSKS\t: "+sks+"\n\n"+timestamp);
        alertDialogBuilder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    //Method Menghapus semua history di dalam list (dalam database)
    private void ClearHistory(){
        SQLiteDatabase db = dbCenter.getWritableDatabase();
        dbCenter.clearHistory(db);
        RefreshList();
    }

    //Method Menampilkan pop-up prompt untuk clear all
    private void ClearPrompt(View view){
        if (listContent.getCount() > 0){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Are you sure, You wanted to clear all history items?");
            alertDialogBuilder.setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            ClearHistory();
                        }
                    });

            alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }else{
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("There's no item on the list");
            alertDialogBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    //Method Fungsi back untuk kembali ke activity sebelumnya
    public void Back(View view){
        finish();
    }

}
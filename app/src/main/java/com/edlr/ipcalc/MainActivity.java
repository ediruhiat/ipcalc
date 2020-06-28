package com.edlr.ipcalc;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    //Konstanta index nilai
    private static double A = 4.00;
    private static double AB = 3.50;
    private static double B = 3.00;
    private static double BC = 2.50;
    private static double C = 2.00;
    private static double D = 1.00;
    private static double E = 0.00;
    private static double K = 0.00;

    int[] jumlahSks = new int[8];
    DataHelper dbCenter;

    TextView tvGradeA;
    TextView tvGradeAB;
    TextView tvGradeB;
    TextView tvGradeBC;
    TextView tvGradeC;
    TextView tvGradeD;
    TextView tvGradeE;
    TextView tvGradeK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbCenter = new DataHelper(this);

        Button btCalculate = findViewById(R.id.btnCalculate);
        Button btHistory = findViewById(R.id.btnHistory);
        ImageButton btSetting = findViewById(R.id.btnSetting);
        ImageButton btAbout = findViewById(R.id.btnAbout);

        tvGradeA = findViewById(R.id.txtNilaiA);
        tvGradeAB = findViewById(R.id.txtNilaiAB);
        tvGradeB = findViewById(R.id.txtNilaiB);
        tvGradeBC = findViewById(R.id.txtNilaiBC);
        tvGradeC = findViewById(R.id.txtNilaiC);
        tvGradeD = findViewById(R.id.txtNilaiD);
        tvGradeE = findViewById(R.id.txtNilaiE);
        tvGradeK = findViewById(R.id.txtNilaiK);

        btCalculate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                double nilaiIp;
                clearSks();

                EditText etNilaiA = findViewById(R.id.etA);
                EditText etNilaiAB = findViewById(R.id.etAB);
                EditText etNilaiB = findViewById(R.id.etB);
                EditText etNilaiBC = findViewById(R.id.etBC);
                EditText etNilaiC = findViewById(R.id.etC);
                EditText etNilaiD = findViewById(R.id.etD);
                EditText etNilaiE = findViewById(R.id.etE);
                EditText etNilaiK = findViewById(R.id.etK);

                TextView resIp = findViewById(R.id.tvIp);
                TextView resSks = findViewById(R.id.tvSks);

                //Exception handling
                if (etNilaiA.getText().length() > 0){
                    jumlahSks[0] = Integer.parseInt(etNilaiA.getText().toString());
                }
                if (etNilaiAB.getText().length() > 0){
                    jumlahSks[1] = Integer.parseInt(etNilaiAB.getText().toString());
                }
                if (etNilaiB.getText().length() > 0){
                    jumlahSks[2] = Integer.parseInt(etNilaiB.getText().toString());
                }
                if (etNilaiBC.getText().length() > 0){
                    jumlahSks[3] = Integer.parseInt(etNilaiBC.getText().toString());
                }
                if (etNilaiC.getText().length() > 0){
                    jumlahSks[4] = Integer.parseInt(etNilaiC.getText().toString());
                }
                if (etNilaiD.getText().length() > 0){
                    jumlahSks[5] = Integer.parseInt(etNilaiD.getText().toString());
                }
                if (etNilaiE.getText().length() > 0){
                    jumlahSks[6] = Integer.parseInt(etNilaiE.getText().toString());
                }
                if (etNilaiK.getText().length() > 0){
                    jumlahSks[7] = Integer.parseInt(etNilaiK.getText().toString());
                }

                nilaiIp = calculateIP(jumlahSks);

                resIp.setText("IP : "+String.format("%.2f", nilaiIp));
                resSks.setText("SKS : "+ (int) totalSks(jumlahSks));

                AddToHistory((int)totalSks(jumlahSks), nilaiIp);
            }
        });

        btHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), History.class);
                startActivity(i);
            }
        });

        btAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAbout();
            }
        });
        btSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        SQLiteDatabase db = dbCenter.getReadableDatabase();
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM grade", null);
        cursor.moveToFirst();

        setTextViewNilai(cursor);

        A = Double.parseDouble(cursor.getString(1));
        AB = Double.parseDouble(cursor.getString(2));
        B = Double.parseDouble(cursor.getString(3));
        BC = Double.parseDouble(cursor.getString(4));
        C = Double.parseDouble(cursor.getString(5));
        D = Double.parseDouble(cursor.getString(6));
        E = Double.parseDouble(cursor.getString(7));
        K = Double.parseDouble(cursor.getString(8));

    }

    private void setTextViewNilai(Cursor cursor) {
        tvGradeA.setText(cursor.getString(1));
        tvGradeAB.setText(cursor.getString(2));
        tvGradeB.setText(cursor.getString(3));
        tvGradeBC.setText(cursor.getString(4));
        tvGradeC.setText(cursor.getString(5));
        tvGradeD.setText(cursor.getString(6));
        tvGradeE.setText(cursor.getString(7));
        tvGradeK.setText(cursor.getString(8));
    }

    //Method tambah data history ke dalam database
    //@RequiresApi(api = Build.VERSION_CODES.O)
    private void AddToHistory(Integer sks, double ipk) {
        ipk = Double.parseDouble(String.format("%.2f", ipk));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String now = sdf.format(new Date());
        SQLiteDatabase db = dbCenter.getWritableDatabase();
        String sql = "INSERT INTO history (sks, ipk, timestamp) VALUES ('"+sks+"', '"+ipk+"', '"+ now +"');";
        db.execSQL(sql);
    }

    //Fungsi Kalkulasi indeks prestasi
    public double calculateIP(int[] jumlahSks){
        double result, totalSks;

        result =
                ((jumlahSks[0]*A)+(jumlahSks[1]*AB)+
                        (jumlahSks[2]*B)+(jumlahSks[3]*BC)+
                        (jumlahSks[4]*C)+(jumlahSks[5]*D)+
                        (jumlahSks[6]*E)+(jumlahSks[7]*K)) / totalSks(jumlahSks);

        return result;
    }

    //Fungsi untuk mendapatkan total sks
    public double totalSks(int[] jumlahSks){
        double totalSks = 0.00;

        for (int i = 0; i < jumlahSks.length; i++){
            totalSks += jumlahSks[i];
        }
        return totalSks;
    }

    //Method prekondisi untuk menghindari exception setting jumlah sks menjadi 0 jika tidak ada input
    public void clearSks(){
        for (int i = 0; i < jumlahSks.length; i++){
            jumlahSks[i] = 0;
        }
    }

    //Method untuk kembali ke activity sebelumnya
    public void Back(View view) {
        finish();
    }

    //Method untuk menampilkan about
    private void ShowAbout() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("app version "+getVersionInfo()+"\nmobile programming\nedi ruhiat 3411161111");
        alertDialogBuilder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private String getVersionInfo() {
        double versionCode = -1.00;
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return Double.toString(versionCode);
    }

    public static void setGrade(double a, double ab, double b, double bc, double c, double d, double e, double k){
        A = a; AB = ab; B = b; BC = bc; C = c; D = d; E = e; K = k;
    }
}
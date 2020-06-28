package com.edlr.ipcalc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {
    DataHelper dbCenter;
    protected Cursor cursor;

    TextView tvValueA, tvValueAB, tvValueB, tvValueBC, tvValueC, tvValueD, tvValueE, tvValueK;

    SeekBar seekBarA, seekBarAB, seekBarB, seekBarBC, seekBarC, seekBarD, seekBarE, seekBarK;

    Button btSave, btReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        dbCenter = new DataHelper(this);

        tvValueA = findViewById(R.id.etA);
        tvValueAB = findViewById(R.id.etAB);
        tvValueB = findViewById(R.id.etB);
        tvValueBC = findViewById(R.id.etBC);
        tvValueC = findViewById(R.id.etC);
        tvValueD = findViewById(R.id.etD);
        tvValueE = findViewById(R.id.etE);
        tvValueK = findViewById(R.id.etK);

        btSave = findViewById(R.id.btnSave);
        btReset = findViewById(R.id.btnReset);

        initiateEditTextValue();

        seekBarA = findViewById(R.id.seekBarA);
        seekBarAB = findViewById(R.id.seekBarAB);
        seekBarB = findViewById(R.id.seekBarB);
        seekBarBC = findViewById(R.id.seekBarBC);
        seekBarC = findViewById(R.id.seekBarC);
        seekBarD = findViewById(R.id.seekBarD);
        seekBarE = findViewById(R.id.seekBarE);
        seekBarK = findViewById(R.id.seekBarK);


        seekBarA.setProgress((int)getGradeValue("a")*100);
        seekBarAB.setProgress((int)getGradeValue("ab")*100);
        seekBarB.setProgress((int)getGradeValue("b")*100);
        seekBarBC.setProgress((int)getGradeValue("bc")*100);
        seekBarC.setProgress((int)getGradeValue("c")*100);
        seekBarD.setProgress((int)getGradeValue("d")*100);
        seekBarE.setProgress((int)getGradeValue("e")*100);
        seekBarK.setProgress((int)getGradeValue("k")*100);

        seekBarA.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            TextView etValueA = findViewById(R.id.etA);
            double valueD = Double.parseDouble(etValueA.getText().toString());
            int value = (int)valueD;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                value = progress;
                valueD = (double)value/100;
                etValueA.setText(String.format("%.2f", valueD));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarAB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            TextView etValueAB = findViewById(R.id.etAB);
            double valueD = Double.parseDouble(etValueAB.getText().toString());
            int value = (int)valueD;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                value = progress;
                valueD = (double)value/100;
                etValueAB.setText(String.format("%.2f", valueD));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            TextView etValueB = findViewById(R.id.etB);
            double valueD = Double.parseDouble(etValueB.getText().toString());
            int value = (int)valueD;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                value = progress;
                valueD = (double)value/100;
                etValueB.setText(String.format("%.2f", valueD));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarBC.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            TextView etValueBC = findViewById(R.id.etBC);
            double valueD = Double.parseDouble(etValueBC.getText().toString());
            int value = (int)valueD;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                value = progress;
                valueD = (double)value/100;
                etValueBC.setText(String.format("%.2f", valueD));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarC.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            TextView etValueC = findViewById(R.id.etC);
            double valueD = Double.parseDouble(etValueC.getText().toString());
            int value = (int)valueD;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                value = progress;
                valueD = (double)value/100;
                etValueC.setText(String.format("%.2f", valueD));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarD.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            TextView etValueD = findViewById(R.id.etD);
            double valueD = Double.parseDouble(etValueD.getText().toString());
            int value = (int)valueD;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                value = progress;
                valueD = (double)value/100;
                etValueD.setText(String.format("%.2f", valueD));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarE.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            TextView etValueE = findViewById(R.id.etE);
            double valueD = Double.parseDouble(etValueE.getText().toString());
            int value = (int)valueD;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                value = progress;
                valueD = (double)value/100;
                etValueE.setText(String.format("%.2f", valueD));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarK.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            TextView etValueK = findViewById(R.id.etK);
            double valueD = Double.parseDouble(etValueK.getText().toString());
            int value = (int)valueD;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                value = progress;
                valueD = (double)value/100;
                etValueK.setText(String.format("%.2f", valueD));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePrompt();
            }
        });

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPrompt();
            }
        });
    }

    private void resetSettingView() {
        seekBarA.setProgress(400);
        seekBarAB.setProgress(350);
        seekBarB.setProgress(300);
        seekBarBC.setProgress(250);
        seekBarC.setProgress(200);
        seekBarD.setProgress(100);
        seekBarE.setProgress(0);
        seekBarK.setProgress(0);
    }

    private double getGradeValue(String grade) {
        SQLiteDatabase db = dbCenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM grade", null); //mengisi cursor dengan query select
        cursor.moveToFirst();

        double value = 0.00;

        switch (grade){
            case "a":
                value = Double.parseDouble(cursor.getString(1));
                break;
            case "ab":
                value = Double.parseDouble(cursor.getString(2));
                break;
            case "b":
                value = Double.parseDouble(cursor.getString(3));
                break;
            case "bc":
                value = Double.parseDouble(cursor.getString(4));
                break;
            case "c":
                value = Double.parseDouble(cursor.getString(5));
                break;
            case "d":
                value = Double.parseDouble(cursor.getString(6));
                break;
            case "e":
                value = Double.parseDouble(cursor.getString(7));
                break;
            case "k":
                value = Double.parseDouble(cursor.getString(8));
                break;
        }

        return value;
    }

    private void initiateEditTextValue(){
        tvValueA.setText(Double.toString(getGradeValue("a")));
        tvValueAB.setText(Double.toString(getGradeValue("ab")));
        tvValueB.setText(Double.toString(getGradeValue("b")));
        tvValueBC.setText(Double.toString(getGradeValue("bc")));
        tvValueC.setText(Double.toString(getGradeValue("c")));
        tvValueD.setText(Double.toString(getGradeValue("d")));
        tvValueE.setText(Double.toString(getGradeValue("e")));
        tvValueK.setText(Double.toString(getGradeValue("k")));
    }

    private void saveGradeToDatabase(String a, String ab, String b, String bc, String c, String d, String e, String k){
        SQLiteDatabase db = dbCenter.getWritableDatabase();
        String sql = "UPDATE grade SET " +
                "a='"+a+"', ab='"+ab+"', b='"+b+"', bc='"+bc+"', c='"+c+"', d='"+d+"', e='"+e+"', k='"+k+"' " +
                "WHERE ids='1'";
        db.execSQL(sql);
    }
    //Method Fungsi back untuk kembali ke activity sebelumnya
    public void Back(View view){
        finish();
    }

    private void resetPrompt(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure, You wanted to reset setting to default?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        dbCenter.resetGradeToDefault(dbCenter.getWritableDatabase());
                        //resetSettingView();
                        finish();
                    }
                });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void savePrompt(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure, You wanted to save all the changes?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        saveGradeToDatabase(tvValueA.getText().toString(),
                                tvValueAB.getText().toString(),
                                tvValueB.getText().toString(),
                                tvValueBC.getText().toString(),
                                tvValueC.getText().toString(),
                                tvValueD.getText().toString(),
                                tvValueE.getText().toString(),
                                tvValueK.getText().toString());

                        finish();
                    }
                });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
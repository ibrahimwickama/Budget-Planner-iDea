package com.wickerlabs.exp_tr;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.wickerlabs.exp_tr.Databases.DatabaseHelper;

public class Settings extends AppCompatActivity {

    DatabaseHelper helper;
    EditText newBudget;

    private static final int MAX_PROGRESS = 100;
    private ProgressDialog mProgressDialog;
    private int mProgress;
    private Handler mProgressHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        helper= new DatabaseHelper(this);

    }

    public void changeBudget(View view){

        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.modifier, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set alertdialog builder
        alertDialogBuilder.setView(promptsView);

        newBudget=(EditText) promptsView.findViewById(R.id.updated_budget);

        // set dialog message
        alertDialogBuilder
                .setCancelable(true)
                .setPositiveButton("Set",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                int newBudgetMoney= Integer.parseInt(newBudget.getText().toString());

                                //takes the data and adds them to Database
                                helper.updateBudget(newBudgetMoney);

                                Intent i = new Intent(Settings.this, MainActivity.class);
                                startActivity(i);

                                Toast.makeText(Settings.this, "Done", Toast.LENGTH_SHORT).show();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.getWindow().setLayout(ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT);
        alertDialog.show();

    }

    public void resetAll(View view){

        helper.deleteAll();
            // creates a percentage loading alertDialog when resiting the data from database
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Erasing...");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setMax(MAX_PROGRESS);
        mProgressDialog.setProgress(0);

        mProgressHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (mProgress >= MAX_PROGRESS) {
                    mProgressDialog.dismiss();
                } else {
                    mProgress++;
                    mProgressDialog.incrementProgressBy(1);
                    mProgressHandler.sendEmptyMessageDelayed(0, 100);
                }
            }
        };

        mProgressHandler.sendEmptyMessage(0);
        mProgressDialog.show();

    }


}

package com.wickerlabs.exp_tr;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    TextView userNameDisp, userPhoneDisp, userEmailDisp, userBudgetDisp;
    EditText userName,phone, email, budgetValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        //getActionBar().hide();
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userNameDisp =(TextView)findViewById(R.id.tvNumber0);
        userPhoneDisp =(TextView)findViewById(R.id.tvNumber1);
        userEmailDisp =(TextView)findViewById(R.id.tvNumber3);
        userBudgetDisp =(TextView)findViewById(R.id.cash);

        if(userName== null || userPhoneDisp==null || userEmailDisp==null || userBudgetDisp==null){

            userNameDisp.setText("User Name");
            userPhoneDisp.setText("Phone");
            userEmailDisp.setText("Email");
            userBudgetDisp.setText("Budget money");
        }else {

            SharedPreferences sharedPref = getSharedPreferences("profileInfo", Context.MODE_PRIVATE);
            userNameDisp.setText(sharedPref.getString("username", ""));
            userPhoneDisp.setText(sharedPref.getString("phone", ""));
            userEmailDisp.setText(sharedPref.getString("email", ""));
            userBudgetDisp.setText(sharedPref.getString("budget", ""));

        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Edit Profile Action ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void editProfile(View view){

        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.activity_prof_edit, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set alertdialog builder
        alertDialogBuilder.setView(promptsView);

        userName=(EditText) promptsView.findViewById(R.id.user_name);

        phone= (EditText) promptsView.findViewById(R.id.user_phoneNo);

        email= (EditText) promptsView.findViewById(R.id.user_email);

        budgetValue=(EditText) promptsView.findViewById(R.id.user_budget);


        // set dialog message
        alertDialogBuilder
                .setCancelable(true)
                .setPositiveButton("Set",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                String username= userName.getText().toString();
                                String userPhone= phone.getText().toString();
                                String userEmail= email.getText().toString();
                                String userBudget= budgetValue.getText().toString();

                                userNameDisp.setText(username);
                                userPhoneDisp.setText(userPhone);
                                userEmailDisp.setText(userEmail);
                                userBudgetDisp.setText(userBudget);

                                SharedPreferences sharedPref = getSharedPreferences("profileInfo", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor= sharedPref.edit();
                                editor.putString("username",username );
                                editor.putString("phone", userPhone);
                                editor.putString("email", userEmail);
                                editor.putString("budget", userBudget);
                                editor.commit();
                                // weka functions humu

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

}

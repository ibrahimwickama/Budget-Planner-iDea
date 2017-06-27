package com.wickerlabs.exp_tr;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wickerlabs.exp_tr.Databases.DatabaseHelper;

import java.io.IOException;

public class Profile extends AppCompatActivity {

    TextView userNameDisp, userPhoneDisp, userEmailDisp, userBudgetDisp;
    EditText userName,phone, email, budgetValue;
    Button editButton;
    ImageView profPic;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        setContentView(R.layout.activity_profile);
       // getActionBar().hide();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        helper= new DatabaseHelper(this);

        editButton=(Button)findViewById(R.id.editBtn);
        userNameDisp =(TextView)findViewById(R.id.tvNumber0);
        userPhoneDisp =(TextView)findViewById(R.id.tvNumber1);
        userEmailDisp =(TextView)findViewById(R.id.tvNumber3);
        userBudgetDisp =(TextView)findViewById(R.id.cash);
        profPic=(ImageView)findViewById(R.id.prof_pic);

        if(userName== null || userPhoneDisp==null || userEmailDisp==null || userBudgetDisp==null){

            Cursor res = helper.getAllData();
            if(res.getCount() == 0) {
                // show message if no data found in the database yet
                Toast.makeText(Profile.this, "No Data Found", Toast.LENGTH_SHORT).show();
                return;
            }
                // greyOut the edit button if there is Data on Database
            editButton.setEnabled(false);
                // Using cursor to get column string values and setText them
            while (res.moveToNext()) {
                userNameDisp.setText(res.getString(1));
                userPhoneDisp.setText(res.getString(2));
                userEmailDisp.setText(res.getString(3));
                userBudgetDisp.setText(res.getString(4));
            }

        }else {
            userNameDisp.setText("User Name");
            userPhoneDisp.setText("Phone");
            userEmailDisp.setText("Email");
            userBudgetDisp.setText("Budget money");
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    // method for Picking pictures in phone gallery
                Intent i= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 1);

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

                                    //takes the data and adds them to Database
                                helper.addUser(username,userPhone, userEmail, userBudget);

                                Toast.makeText(Profile.this, "Done", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK && data != null){
            Uri selectedImage= data.getData();

            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                profPic.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

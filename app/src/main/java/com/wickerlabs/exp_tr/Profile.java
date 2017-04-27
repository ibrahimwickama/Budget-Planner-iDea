package com.wickerlabs.exp_tr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Profile extends AppCompatActivity {

    Button editProf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        editProf=(Button)findViewById(R.id.edit_button);

        editProf.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i= new Intent(Profile.this, Prof_edit.class);
                        startActivity(i);

                 /*       // get data_call_limits.xml view as dialog view
                        LayoutInflater li = LayoutInflater.from(Profile.this);
                        View promptsView = li.inflate(R.layout.activity_prof_edit, null);

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Profile.this);

                        // set alertdialog builder
                        alertDialogBuilder.setView(promptsView);

                     /*   mb_limit = (EditText) promptsView
                                .findViewById(R.id.max_MB_Limit);

                        call_limit = (EditText) promptsView
                                .findViewById(R.id.max_call_limit);

                        // set dialog message
                        alertDialogBuilder
                                .setCancelable(true)
                                .setPositiveButton("Set",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {

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

                        alertDialog.show();    */

                    }
                }
        );
    }
}

package com.wickerlabs.exp_tr;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class AdderSheet extends AppCompatActivity {
    TextView cashfrom; Spinner spinner;
    EditText cost_in, note;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_adder_sheet);


        cashfrom=(TextView)findViewById(R.id.cash_from);
        spinner=(Spinner)findViewById(R.id.spinner);
        cost_in=(EditText)findViewById(R.id.editText);
        //add=(Button)findViewById(R.id.button);


        SharedPreferences sharedPref= getSharedPreferences("profileInfo", Context.MODE_PRIVATE);
        cashfrom.setText(sharedPref.getString("budget", ""));


        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width= dm.widthPixels;
        int height= dm.heightPixels;

        getWindow().setLayout((int)(width*.56), (int)(height*.52));
        getWindow().setGravity(Gravity.CENTER_VERTICAL);


        // method for making PoPuP Window be transparent background
        //getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        String[] a={"Transport", "Bills","Shopping","Food","Credits"};
        SpinnerAdapter spinnerAdapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, a );

       spinner.setAdapter(spinnerAdapter);

        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(AdderSheet.this, "WAaaaaat", Toast.LENGTH_SHORT).show();
                    }
                }
        );




    }
}

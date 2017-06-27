package com.wickerlabs.exp_tr;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wickerlabs.exp_tr.Databases.DatabaseHelper;

public class Today extends Fragment {

    TextView transportCash, billsCash, shoppingCash, foodsCash, creditsCash;

    DatabaseHelper helper;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.activity_today, container, false);

        helper= new DatabaseHelper(getContext());

        transportCash=  (TextView)v.findViewById(R.id.today_transCash);
        billsCash=(TextView)v.findViewById(R.id.today_billsCash);
        shoppingCash=(TextView) v.findViewById(R.id.today_shopngCash);
        foodsCash= (TextView) v.findViewById(R.id.today_foodsCash);
        creditsCash= (TextView) v.findViewById(R.id.today_creditsCash);


        Cursor cursor= helper.getAllExpData();
            while(cursor.moveToNext()){
                transportCash.setText(""+cursor.getInt(5));
                billsCash.setText(""+cursor.getInt(6));
                shoppingCash.setText(""+cursor.getInt(7));
                foodsCash.setText(""+cursor.getInt(8));
                creditsCash.setText(""+cursor.getInt(9));

            }


        return  v;
    }
}

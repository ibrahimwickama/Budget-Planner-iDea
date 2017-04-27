package com.wickerlabs.exp_tr;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Today extends Fragment {

    TextView transportCash, billsCash, shoppingCash, foodsCash, creditsCash;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.activity_today, container, false);

        transportCash=  (TextView)v.findViewById(R.id.today_transCash);
        billsCash=(TextView)v.findViewById(R.id.today_billsCash);
        shoppingCash=(TextView) v.findViewById(R.id.today_shopngCash);
        foodsCash= (TextView) v.findViewById(R.id.today_foodsCash);
        creditsCash= (TextView) v.findViewById(R.id.today_creditsCash);


        return  v;
    }
}

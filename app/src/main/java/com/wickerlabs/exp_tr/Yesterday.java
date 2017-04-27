package com.wickerlabs.exp_tr;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Yesterday extends Fragment {

    TextView yest_transportCash, yest_billsCash, yest_shoppingCash, yest_foodsCash, yest_creditsCash;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.activity_yesterday, container, false);

        yest_transportCash=  (TextView)v.findViewById(R.id.yest_transCash);
        yest_billsCash=(TextView)v.findViewById(R.id.yest_billsCash);
        yest_shoppingCash=(TextView) v.findViewById(R.id.yest_shopngCash);
        yest_foodsCash= (TextView) v.findViewById(R.id.yest_foodsCash);
        yest_creditsCash= (TextView) v.findViewById(R.id.yest_creditsCash);

       // View v= inflater.inflate(R.layout.activity_display_field, container, false);



        return  v;
    }
}

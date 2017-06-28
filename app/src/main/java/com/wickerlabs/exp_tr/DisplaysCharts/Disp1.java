package com.wickerlabs.exp_tr.DisplaysCharts;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wickerlabs.exp_tr.Databases.DatabaseHelper;
import com.wickerlabs.exp_tr.R;

import az.plainpie.PieView;
import az.plainpie.animation.PieAngleAnimation;

public class Disp1 extends Fragment {

    DatabaseHelper helper;
    int budgetCash, transportCash, billsCash, shoppingCash, foodsCash, creditsCash, totalExp,a,b,c,d,e,f;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_disp1, container, false);

        helper= new DatabaseHelper(getContext());
        Cursor cursor= helper.getAllExpData();

        PieView pieView = (PieView) v.findViewById(R.id.pieView);
        pieView.setPercentageBackgroundColor(getResources().getColor(R.color.lightBlue));
        pieView.setPieInnerPadding(50);
        pieView.setInnerTextVisibility(View.VISIBLE);

        if(cursor.getCount()==0){
            pieView.setPercentage(100);
            // Change the text of the widget
            pieView.setInnerText("100%");
        }else{

            while(cursor.moveToNext()){
                    //get the column values of expenses from Database
                budgetCash = Integer.valueOf(cursor.getInt(4));

                transportCash = Integer.valueOf(cursor.getInt(5));

                billsCash = Integer.valueOf(cursor.getInt(6));

                shoppingCash = Integer.valueOf(cursor.getInt(7));

                foodsCash = Integer.valueOf(cursor.getInt(8));

                creditsCash = Integer.valueOf(cursor.getInt(9));

            }

            float totalExp= (float) ((transportCash + billsCash + shoppingCash + foodsCash + creditsCash)*100/budgetCash);

            float result= 100 - totalExp;

                // set remain percentage to the display view
            pieView.setPercentage(result);
                // Change the text of the widget
            pieView.setInnerText(result+"%");

            Toast.makeText(getContext(), ""+totalExp, Toast.LENGTH_SHORT).show();
            Log.i("Result value", ""+totalExp);
        }



















//        pieView.setPercentage(90);
//
//            // Change the text of the widget
//        pieView.setInnerText("90%");
//
        // Change the text size of the widget
        pieView.setPercentageTextSize(45);

        PieAngleAnimation animation = new PieAngleAnimation(pieView);
        animation.setDuration(2000); //This is the duration of the animation in millis
        pieView.startAnimation(animation);

            // Change the color fill of the bar representing the current percentage
        // pieView.setPercentageBackgroundColor(getResources().getColor(R.color.customColor1));

            // Change the color fill of the background of the widget, by default is transparent
        //pieView.setMainBackgroundColor(getResources().getColor(R.color.customColor5));

            // Change the color of the text of the widget
        // pieView.setTextColor(getResources().getColor(R.color.customColor12));


        return v;
    }

    public static Disp1 newInstance(String text) {

        Disp1 disp1 = new Disp1();
        Bundle b = new Bundle();
        b.putString("msg", text);
        disp1.setArguments(b);

        return disp1;
    }
}

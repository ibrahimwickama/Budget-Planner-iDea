package com.wickerlabs.exp_tr.DisplaysCharts;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.wickerlabs.exp_tr.Databases.DatabaseHelper;
import com.wickerlabs.exp_tr.R;

import java.util.ArrayList;

public class Disp2 extends Fragment {

    DatabaseHelper helper;
    float budgetCash, transportCash, billsCash, shoppingCash, foodsCash, creditsCash, a,b,c,d,e,f;
    float resultTransport, resultBills, resultShopping, resultFoods, resultCredits, z;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_disp2, container, false);

        PieChart pieChart2 = (PieChart)v.findViewById(R.id.chart2);

        helper= new DatabaseHelper(getContext());

        Cursor cursor= helper.getAllExpData();

        ArrayList<Entry> entries = new ArrayList<>();

            // check if in database cursor gets any row of data.
        if(cursor.getCount()==0){
            entries.add(new Entry(4f, 0));
            entries.add(new Entry(8f, 1));
            entries.add(new Entry(6f, 2));
            entries.add(new Entry(12f, 3));
            entries.add(new Entry(18f, 4));

        }else {
            while (cursor.moveToNext()) {
                    // get the column values of expenses from Database
                budgetCash = Integer.valueOf(cursor.getInt(4));

                transportCash = Integer.valueOf(cursor.getInt(5));

                billsCash = Integer.valueOf(cursor.getInt(6));

                shoppingCash = Integer.valueOf(cursor.getInt(7));

                foodsCash = Integer.valueOf(cursor.getInt(8));

                creditsCash = Integer.valueOf(cursor.getInt(9));

            }
                // calculate each expense percentage value it occupies from budgetCash given
            resultTransport= (transportCash )*100/budgetCash;
            resultBills= (billsCash )*100/budgetCash;
            resultShopping= (shoppingCash )*100/budgetCash;
            resultFoods= (foodsCash )*100/budgetCash;
            resultCredits= (creditsCash )*100/budgetCash;

                // set results to the pieChart view for each expense category
            entries.add(new Entry(resultTransport, 0));
            entries.add(new Entry(resultBills, 1));
            entries.add(new Entry(resultShopping, 2));
            entries.add(new Entry(resultFoods, 3));
            entries.add(new Entry(resultCredits, 4));

        }

        PieDataSet dataset = new PieDataSet(entries, "Key");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Transport");
        labels.add("Bills");
        labels.add("Shopping");
        labels.add("Food");
        labels.add("Credits");

        PieData data = new PieData(labels, dataset);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
        pieChart2.setDescription("Description");
        pieChart2.setData(data);

        pieChart2.animateY(5000);

        //pieChart2.saveToGallery("/sd/mychart.jpg", 85); // 85 is the quality of the image

        return v;
    }

    public static Disp2 newInstance(String text) {

        Disp2 disp2 = new Disp2();
        Bundle b = new Bundle();
        b.putString("msg", text);
        disp2.setArguments(b);

        return disp2;
    }
}

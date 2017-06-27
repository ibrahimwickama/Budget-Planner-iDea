package com.wickerlabs.exp_tr;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import az.plainpie.PieView;
import az.plainpie.animation.PieAngleAnimation;

public class Disp1 extends Fragment {

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_disp1);
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_disp1, container, false);

        PieView pieView = (PieView) v.findViewById(R.id.pieView);
        pieView.setPercentageBackgroundColor(getResources().getColor(R.color.lightBlue));
        pieView.setPieInnerPadding(50);
        pieView.setInnerTextVisibility(View.VISIBLE);
        pieView.setPercentage(90);

            // Change the text of the widget
        pieView.setInnerText("90%");

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

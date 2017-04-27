package com.wickerlabs.exp_tr;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Summaries extends Fragment{

    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.activity_summaries, container, false);

        listView = (ListView) v.findViewById(R.id.listView);
        String[] summaries= {"Today", "Weekly", " Monthly", "Yearly"};
        ArrayAdapter myAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, summaries);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String sumSelected = String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(getContext(), "Yu clicked "+sumSelected, Toast.LENGTH_SHORT).show();

            }
        });

        return  v;

    }
}




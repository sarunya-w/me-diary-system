package com.example.mediary.diary.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mediary.R;
import com.example.mediary.diary.EventActivity;
import com.example.mediary.report.PdfViewerActivity;
import com.example.mediary.report.ReportActivity;

public class EventFragment extends Fragment {

    String[] values = new String[] { "Android List View",
            "Adapter implementation",
            "Simple List View In Android"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_event, container, false);
        View view = inflater.inflate(R.layout.fragment_mood, container, false);


        ListView listView = (ListView)view.findViewById(R.id.data_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getContext(), values[position], Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getContext(), EventActivity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton floatingActionButton =
                (FloatingActionButton) view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click.
            }
        });

        return view;
    }
}

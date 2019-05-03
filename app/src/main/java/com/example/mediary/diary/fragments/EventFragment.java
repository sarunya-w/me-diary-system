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
import android.widget.TextView;
import android.widget.Toast;

import com.example.mediary.R;
import com.example.mediary.diary.EventActivity;
import com.example.mediary.report.PdfViewerActivity;
import com.example.mediary.report.ReportActivity;

public class EventFragment extends Fragment {

    String[] list = new String[] { "ทานข้าวเช้าจ้าาา",
            "ไปโรงเรียน",
            "ทะเลาะกับเพื่อน",
            "ทำเงินหาย"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_event, container, false);
        View view = inflater.inflate(R.layout.fragment_event, container, false);

        TextView vDate = (TextView) view.findViewById(R.id.txtDate);
        String date = "";
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            date = bundle.getString("DATE");
            vDate.setText(date);
        }

        ListView listView = (ListView)view.findViewById(R.id.data_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), EventActivity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton floatingActionButton =
                (FloatingActionButton) view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), EventActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}

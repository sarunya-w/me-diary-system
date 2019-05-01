package com.example.mediary.diary;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.example.mediary.report.ReportActivity;
import com.example.mediary.scanner.QrCodeScannerActivity;

public class DynamicFragment extends Fragment {
    private String[] list;
    private Class<?> mClss;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dynamic, container, false);
        String[] list1 = new String[] { "Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View"
        };

        String[] list2 = new String[] { "ทานข้าวเช้าจ้าาา",
                "ไปโรงเรียน",
                "ทะเลาะกับเพื่อน",
                "ทำเงินหาย"
        };

        String[] list3 = new String[] { "Android List View",
                "Adapter implementation"
        };


        Bundle bundle = this.getArguments();

        if (bundle != null) {
            int myInt = bundle.getInt("KEY");
            switch (myInt) {
                case R.string.title_mood: list = list1; mClss = MoodActivity.class; break;
                case R.string.title_event: list = list2; mClss = EventActivity.class; break;
                case R.string.title_drug: list = list3; mClss = DrugActivity.class; break;
                default: break;
            }
        }

        ListView listView = (ListView)view.findViewById(R.id.data_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getContext(), list[position], Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(), mClss);
            startActivity(intent);
            }
        });

        FloatingActionButton floatingActionButton =
                (FloatingActionButton) view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(getContext(), mClss);
            startActivity(intent);

            }
        });
        return view;
    }
}

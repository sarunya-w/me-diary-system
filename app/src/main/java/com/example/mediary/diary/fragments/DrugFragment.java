package com.example.mediary.diary.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mediary.R;
import com.example.mediary.diary.DrugActivity;

import java.util.ArrayList;

public class DrugFragment extends Fragment {
    TextView view_disease;

    TextView view_drug_his;

    TextView view_drug_used;


    String[] a_lists = new String[] {
            "ยาขับปัสสาวะ HCTZ 50 MG",
            "Enalapril 5 MG",
            "Glibenclamide 50 MG",
            "Metformin 500 MG",
            "Paracetamol 500 MG",
            "ยาแก้ไอตรามะข้ามป้อม"
    };

    boolean[] a_checked = new boolean[] { true, false, true, false,true, true };

    ArrayList<Integer> mUserItems = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_drug, container, false);

        view_disease = (TextView) view.findViewById(R.id.text_view_disease);
        view_drug_his = (TextView) view.findViewById(R.id.text_view_drug_his);
        view_drug_used = (TextView) view.findViewById(R.id.text_view_drug_used);

        View btn_disease = (View) view.findViewById(R.id.text_disease);
        btn_disease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToOption();
            }
        });

        View btn_text_drug_his = (View) view.findViewById(R.id.text_drug_his);
        btn_text_drug_his.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                goToOption();
            }
        });

        View btn_drug_used = (View) view.findViewById(R.id.text_drug_used);
        btn_drug_used.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToOption();
            }
        });


//        ListView listView = (ListView)view.findViewById(R.id.data_list);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);
//        listView.setAdapter(adapter);
//
        FloatingActionButton floatingActionButton =
                (FloatingActionButton) view.findViewById(R.id.fab_done);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click.
                // save
            }
        });
        return view;
    }

    public void goToOption( ) {


        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
        mBuilder.setMultiChoiceItems(a_lists, a_checked, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                if(isChecked){
                    mUserItems.add(position);
                }else{
                    mUserItems.remove((Integer.valueOf(position)));
                }
            }
        });

        mBuilder.setCancelable(false);
        mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                String item = "";
                for (int i = 0; i < mUserItems.size(); i++) {
                    item = item + a_lists[mUserItems.get(i)];
                    if (i != mUserItems.size() - 1) {
                        item = item + ", ";
                    }
                }

                view_drug_used.setText(item);
            }
        });

        mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        mBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                for (int i = 0; i < a_checked.length; i++) {
                    a_checked[i] = false;
                    mUserItems.clear();
                    view_drug_used.setText("-");
                }
            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }
}

package com.example.mediary.diary.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mediary.R;

import java.util.ArrayList;

public class DialogListFragment extends Fragment {
    String[] values = new String[] { "โรคประจำตัว",
        "ประวัติการแพ้ยา",
        "ข้อมูลการใช้ยา"
    };

    String[] a_lists = new String[] { "โรคประจำตัว",
            "ประวัติการแพ้ยา",
            "ข้อมูลการใช้ยา"
    };



    boolean[] a_checked = new boolean[] { true, false, true };

    ArrayList<Integer> mUserItems = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialog_list, container, false);

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
                // mItemSelected.setText(item);
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
//                            mItemSelected.setText("");
                }
            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();

        return view;
    }
}

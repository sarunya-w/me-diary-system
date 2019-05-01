package com.example.mediary.diary;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mediary.R;
import com.example.mediary.diary.fragments.DialogListFragment;
import com.example.mediary.report.PdfViewerActivity;
import com.example.mediary.report.ReportActivity;

import java.util.ArrayList;

public class DrugActivity extends AppCompatActivity {

    ArrayList<String> values = new ArrayList<String>();

//    String[] a_lists = new String[] { "โรคประจำตัว",
//            "ประวัติการแพ้ยา",
//            "ข้อมูลการใช้ยา"
//    };
//
////    String[] a_lists = new String[] { "โรคประจำตัว",
////            "ประวัติการแพ้ยา",
////            "ข้อมูลการใช้ยา"
////    };
//
//    boolean[] a_checked = new boolean[] { true, false, true };
//
//    ArrayList<Integer> mUserItems = new ArrayList<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_drug);
//
//        // Construct the data source
//        ArrayList<String> arrayOfUsers = new ArrayList<String>();
//        arrayOfUsers.add("aaaaaa");
//        // Create the adapter to convert the array to views
//        ListViewAdapter adapter = new ListViewAdapter(this, arrayOfUsers);
////        // Attach the adapter to a ListView
//        ListView listView = (ListView) findViewById(R.id.list_view);
//        listView.setAdapter(adapter);
//
////        ListView listView = (ListView)findViewById(R.id.data_list);
////        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
////        listView.setAdapter(adapter);
////
////        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
////            @Override
////            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                Toast.makeText(ReportActivity.this, list[position], Toast.LENGTH_SHORT).show();
////                //based on item add info to intent
//////                ItemClicked item = adapter.getItemAtPosition(position);
////                Intent intent = new Intent(currentActivity, PdfViewerActivity.class);
////                startActivity(intent);
//////                Fragment androidFragment = new PdfRendererFragment();
//////                replaceFragment(androidFragment);
////            }
////        });
//
////        AlertDialog.Builder mBuilder = new AlertDialog.Builder(DrugActivity.this);
////        mBuilder.setMultiChoiceItems(a_lists, a_checked, new DialogInterface.OnMultiChoiceClickListener() {
////            @Override
////            public void onClick(DialogInterface dialog, int position, boolean isChecked) {
////                if(isChecked){
////                    mUserItems.add(position);
////                }else{
////                    mUserItems.remove((Integer.valueOf(position)));
////                }
////            }
////        });
////
////        mBuilder.setCancelable(false);
////        mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
////            @Override
////            public void onClick(DialogInterface dialogInterface, int which) {
////                String item = "";
////                for (int i = 0; i < mUserItems.size(); i++) {
////                    item = item + a_lists[mUserItems.get(i)];
////                    if (i != mUserItems.size() - 1) {
////                        item = item + ", ";
////                    }
////                }
////               // mItemSelected.setText(item);
////            }
////        });
////
////        mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
////            @Override
////            public void onClick(DialogInterface dialogInterface, int i) {
////                dialogInterface.dismiss();
////            }
////        });
////
////        mBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
////            @Override
////            public void onClick(DialogInterface dialogInterface, int which) {
////                for (int i = 0; i < a_checked.length; i++) {
////                    a_checked[i] = false;
////                    mUserItems.clear();
//////                            mItemSelected.setText("");
////                }
////            }
////        });
////        AlertDialog mDialog = mBuilder.create();
////        mDialog.show();
////
////        FloatingActionButton floatingActionButton =
////                (FloatingActionButton) findViewById(R.id.fab);
////        floatingActionButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
//////                Intent intent = new Intent(getContext(), mClss);
//////                startActivity(intent);
////
////            }
////        });
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug);
        populateUsersList();
    }

    private void populateUsersList() {
        // Construct the data source
        values.add("โรคประจำตัว");
        values.add("ประวัติการแพ้ยา");
        values.add("ข้อมูลการใช้ยา");
        ArrayList<String> arrayOfUsers = values ;

        // Create the adapter to convert the array to views
        ListViewAdapter adapter = new ListViewAdapter(this, arrayOfUsers);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.lvUsers);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                populateDialog();
            }
        });
    }

    String[] a_lists = new String[] { "A",
            "B",
            "C"
    };

    boolean[] a_checked = new boolean[] { true, false, true };

    ArrayList<Integer> mUserItems = new ArrayList<>();

    private void populateDialog(){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(DrugActivity.this);
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
    }
}

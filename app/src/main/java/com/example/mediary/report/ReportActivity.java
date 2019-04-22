package com.example.mediary.report;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mediary.R;

public class ReportActivity extends AppCompatActivity {
    public static final String FRAGMENT_PDF_RENDERER_BASIC = "pdf_renderer_basic";
    private Activity currentActivity;

    String[] list = new String[]{"Android List View",
            "Adapter implementation",
            "Simple List View In Android",
            "Create List View Android",
            "Android Example",
            "List View Source Code",
            "List View Array Adapter",
            "Android Example List View"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        currentActivity = this;
//        ViewPager viewPager = (ViewPager) findViewById(R.id.main_tab_content2);
//        setupViewPager(viewPager);
        ListView listView = (ListView) findViewById(R.id.data_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ReportActivity.this, list[position], Toast.LENGTH_SHORT).show();
                //based on item add info to intent
//                ItemClicked item = adapter.getItemAtPosition(position);
                Intent intent = new Intent(currentActivity, PdfViewerActivity.class);
                startActivity(intent);
//                Fragment androidFragment = new PdfRendererFragment();
//                replaceFragment(androidFragment);
            }
        });
    }

//    // Replace current Fragment with the destination Fragment.
//    public void replaceFragment(Fragment destFragment)
//    {
//        // First get FragmentManager object.
//        FragmentManager fragmentManager = this.getSupportFragmentManager();
//
//        // Begin Fragment transaction.
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        // Replace the layout holder with the required Fragment object.
//        fragmentTransaction.replace(R.id.dynamic_fragment_frame_layout, destFragment);
//
//        // Commit the Fragment replace action.
//        fragmentTransaction.commit();
//    }

//    private void setupViewPager(ViewPager viewPager) {
//        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
//        adapter.insertNewFragment(new PdfRendererFragment());
//        viewPager.setAdapter(adapter);
//    }

}

package com.example.mediary.diary;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceFragment;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.mediary.R;
import com.example.mediary.diary.fragments.DrugFragment;
import com.example.mediary.diary.fragments.EventFragment;
import com.example.mediary.diary.fragments.MoodFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DiaryActivity extends AppCompatActivity {

    private final int max_tabs = 3;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

//        setContentView(R.layout.activity_date_picker);
//        mDisplayDate = (TextView) findViewById(R.id.tvDate);
//
//        mDisplayDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Calendar cal = Calendar.getInstance();
//                int year = cal.get(Calendar.YEAR);
//                int month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog dialog = new DatePickerDialog(
//                        DiaryActivity.this,
//                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
//                        mDateSetListener,
//                        year,month,day);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.show();
//            }
//        });
//
//        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                month = month + 1;
////                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);
//
//                String date = month + "/" + day + "/" + year;
//                mDisplayDate.setText(date);
//
//            }
//        };
//
//        Button mEmailSignInButton = (Button) findViewById(R.id.btn_next);
//        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setContentView(R.layout.activity_diary);
//                //
//                int[] icons = {R.drawable.ic_mood,
//                        R.drawable.ic_event,
//                        R.drawable.ic_drug
//                };
//
//                CharSequence[] labels = {getResources().getText(R.string.title_mood),
//                        getResources().getText(R.string.title_event),
//                        getResources().getText(R.string.title_drug)
//                };
//
//                TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
//                ViewPager viewPager = (ViewPager) findViewById(R.id.main_tab_content);
//
//                setupViewPager(viewPager);
//
//                tabLayout.setupWithViewPager(viewPager);
//
//                for (int i = 0; i < icons.length; i++) {
//                    tabLayout.getTabAt(i).setIcon(icons[i]);
//                    tabLayout.getTabAt(i).setText(labels[i]);
//                }
//                tabLayout.getTabAt(0).select();
//            }
//        });



        int[] icons = {R.drawable.ic_mood,
                R.drawable.ic_event,
                R.drawable.ic_drug
        };

        CharSequence[] labels = {getResources().getText(R.string.title_mood),
                getResources().getText(R.string.title_event),
                getResources().getText(R.string.title_drug)
        };

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.main_tab_content);

        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < icons.length; i++) {
            tabLayout.getTabAt(i).setIcon(icons[i]);
            tabLayout.getTabAt(i).setText(labels[i]);
        }
        tabLayout.getTabAt(0).select();
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
//        adapter.insertNewFragment(new MoodFragment());
//        adapter.insertNewFragment(new EventFragment());
//        adapter.insertNewFragment(new DrugFragment());

        DynamicFragment mood_fragment = new DynamicFragment();
        Bundle m_bundle = new Bundle();
        m_bundle.putInt("KEY", R.string.title_mood);
        mood_fragment.setArguments(m_bundle);
        adapter.insertNewFragment(mood_fragment);

        DynamicFragment event_fragment = new DynamicFragment();
        Bundle e_bundle = new Bundle();
        e_bundle.putInt("KEY", R.string.title_event);
        event_fragment.setArguments(e_bundle);
        adapter.insertNewFragment(event_fragment);

        DynamicFragment drug_fragment = new DynamicFragment();
        Bundle d_bundle = new Bundle();
        d_bundle.putInt("KEY", R.string.title_drug);
        drug_fragment.setArguments(d_bundle);
        adapter.insertNewFragment(drug_fragment);

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void insertNewFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }
    }

//    public static class DatePickerFragment extends PreferenceFragment {
//        public void onCreate(Bundle savedInstanceState)
//        {
//            super.onCreate(savedInstanceState);
//            addPreferencesFromResource(R.xml.preference_playback);
//        }
//    }
}

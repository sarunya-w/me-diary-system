package com.example.mediary.diary;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mediary.R;
import com.example.mediary.diary.fragments.DrugFragment;
import com.example.mediary.diary.fragments.EventFragment;
import com.example.mediary.diary.fragments.MoodFragment;

import java.util.ArrayList;
import java.util.List;

public class DiaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

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
        adapter.insertNewFragment(new MoodFragment());
        adapter.insertNewFragment(new EventFragment());
        adapter.insertNewFragment(new DrugFragment());
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
}

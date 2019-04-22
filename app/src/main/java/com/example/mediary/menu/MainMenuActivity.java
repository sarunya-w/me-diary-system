package com.example.mediary.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.mediary.R;
import com.example.mediary.diary.DiaryActivity;
import com.example.mediary.report.ReportActivity;

public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView profileCard, calendarCard, diaryCard, reportCard, settingCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // define cards
        profileCard = (CardView) findViewById(R.id.profile_card);
        diaryCard = (CardView) findViewById(R.id.diary_card);
//        calendarCard = (CardView) findViewById(R.id.calendar_card);
        reportCard = (CardView) findViewById(R.id.report_card);
        settingCard = (CardView) findViewById(R.id.setting_card);

        // add click listener to the cards
        profileCard.setOnClickListener(this);
//        calendarCard.setOnClickListener(this);
        diaryCard.setOnClickListener(this);
        reportCard.setOnClickListener(this);
        settingCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.diary_card: i = new Intent(this, DiaryActivity.class); startActivity(i); break;
//            case R.id.profile_card : i = new Intent(this, ProfileActivity.class); startActivity(i);break;
            case R.id.report_card : i = new Intent(this, ReportActivity.class); startActivity(i);break;
//            case R.id.setting_card : i = new Intent(this, SettingActivity.class); startActivity(i);break;
            default: break;
        }

    }
}

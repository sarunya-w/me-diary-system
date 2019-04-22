package com.example.mediary.report;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mediary.R;
import com.github.barteksc.pdfviewer.PDFView;

public class PdfViewerActivity extends AppCompatActivity {

    private PDFView pdfView;
    private Activity currentActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);
        currentActivity = this;

        pdfView = findViewById(R.id.pdf_view);
        pdfView.fromAsset("sample.pdf").load();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(currentActivity.getApplicationContext(), "Sent.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(currentActivity, QrCodeSyncActivity.class);
                startActivity(intent);
            }
        });
    }
}

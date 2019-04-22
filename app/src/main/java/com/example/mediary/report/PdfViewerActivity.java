package com.example.mediary.report;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mediary.R;
import com.github.barteksc.pdfviewer.PDFView;

public class PdfViewerActivity extends AppCompatActivity {

    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);

        pdfView = findViewById(R.id.pdf_view);
        pdfView.fromAsset("sample.pdf").load();


    }
}

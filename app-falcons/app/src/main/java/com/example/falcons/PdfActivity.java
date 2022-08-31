package com.example.falcons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.example.falcons.ui.Ui;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;

public class PdfActivity extends AppCompatActivity {

    String pdfName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        View v = getWindow().getDecorView();
        Ui navbar = new Ui();
        navbar.hideNavBar(v, 900);



        Intent intent = getIntent();

        //Parametro que recebe o nome do pdf

        pdfName = (String) intent.getSerializableExtra("parametro2");

        //Parametro que recebe a pagina q vai abrir

        int pag = (int) intent.getSerializableExtra("parametro");



        PDFView pdfView = findViewById(R.id.pdfView);
        pdfView.fromAsset(pdfName)
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(true)
                .enableDoubletap(true)
                .defaultPage(pag) //****página q vai abrir
                .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                // spacing between pages in dp. To define spacing color, set view background
                .spacing(0)
                .pageFitPolicy(FitPolicy.BOTH)
                .load();



        ImageView voltar = (ImageView) findViewById(R.id.image_voltar);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

}
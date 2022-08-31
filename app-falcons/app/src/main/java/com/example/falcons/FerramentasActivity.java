package com.example.falcons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import com.example.falcons.ui.PdfClass;
import com.example.falcons.ui.Ui;


public class FerramentasActivity extends AppCompatActivity implements View.OnClickListener {

    int pagPdfCompleto = 0;
    int pagEpi = 3;
    int pagCorte = 4;
    int pagMedicao = 9;
    int pagAperto = 18;
    int pagAlicates = 20;
    int pagFuradeiras = 21;
    int pagSolda = 24;
    //Nome do pdf do Manual de Ferramentas
    String pdfName = "Manual_de_Ferramentas.pdf";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ferramentas);

        View v = getWindow().getDecorView();
        Ui navbar = new Ui();
        navbar.hideNavBar(v, 900);


        CardView cardPdf = (CardView) findViewById(R.id.cardPdf);
        CardView cardEpi = (CardView) findViewById(R.id.cardEpi);
        CardView cardCorte = (CardView) findViewById(R.id.cardCorte);
        CardView cardMedicao = (CardView) findViewById(R.id.cardMedicao);
        CardView cardAperto = (CardView) findViewById(R.id.cardAperto);
        CardView cardAlicates = (CardView) findViewById(R.id.cardAlicates);
        CardView cardFuradeiras = (CardView) findViewById(R.id.cardFuradeiras);
        CardView cardSolda = (CardView) findViewById(R.id.cardSolda);
        ImageView voltar = (ImageView) findViewById(R.id.image_voltar);

        CardView[] click = new CardView[]{ cardPdf, cardEpi,cardCorte,cardMedicao,cardAperto,cardAlicates, cardFuradeiras, cardSolda};

        for(int i=0; i<click.length;i++){
            click[i].setOnClickListener(this);
        }

        voltar.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.cardPdf:

                PdfClass pdfClass1 = new PdfClass();
                pdfClass1.Callpdf(pagPdfCompleto,this,pdfName);
                break;
            case R.id.cardEpi:

                // Parametros enviados diretamente para o .DefaultPage
                PdfClass pdfClass2 = new PdfClass();
                pdfClass2.Callpdf(pagEpi,this,pdfName);


                break;
            case R.id.cardCorte:

                // Parametros enviados diretamente para o .DefaultPage
                PdfClass pdfClass3 = new PdfClass();
                pdfClass3.Callpdf(pagCorte,this,pdfName);

                break;
            case R.id.cardMedicao:

                // Parametros enviados diretamente para o .DefaultPage
                PdfClass pdfClass4 = new PdfClass();
                pdfClass4.Callpdf(pagMedicao,this,pdfName);


                break;
            case R.id.cardAperto:

                // Parametros enviados diretamente para o .DefaultPage
                PdfClass pdfClass5 = new PdfClass();
                pdfClass5.Callpdf(pagAperto,this,pdfName);


                break;
            case R.id.cardAlicates:

                // Parametros enviados diretamente para o .DefaultPage
                PdfClass pdfClass6 = new PdfClass();
                pdfClass6.Callpdf(pagAlicates,this,pdfName);


                break;
            case R.id.cardFuradeiras:

                // Parametros enviados diretamente para o .DefaultPage
                PdfClass pdfClass7 = new PdfClass();
                pdfClass7.Callpdf(pagFuradeiras,this,pdfName);


                break;
            case R.id.cardSolda:

                // Parametros enviados diretamente para o .DefaultPage
                PdfClass pdfClass8 = new PdfClass();
                pdfClass8.Callpdf(pagSolda,this,pdfName);


                break;

            case R.id.image_voltar:
                finish();
                Intent intent = new Intent(FerramentasActivity.this, MenuActivity.class);
                startActivity(intent);
                break;
        }
    }



}


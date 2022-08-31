package com.example.falcons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.falcons.ui.PdfClass;
import com.example.falcons.ui.Ui;

public class InstrucaoActivity extends AppCompatActivity  implements View.OnClickListener {

    // Paginas onde se encontram os níveis

    int pagPdfCompleto = 0;
    int pagNivel3 = 4;
    int pagNivel4 = 22;
    int pagNivel5 = 64;
    String pdfName = "Manual_de_Montagem.pdf";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrucao);

        View v = getWindow().getDecorView();
        Ui navbar = new Ui();
        navbar.hideNavBar(v, 900);


        CardView cardPdf = (CardView) findViewById(R.id.card2);
        CardView cardNivel3 = (CardView) findViewById(R.id.card3);
        CardView cardNivel4 = (CardView) findViewById(R.id.card4);
        CardView cardNivel5 = (CardView) findViewById(R.id.card5);
        ImageView voltar = (ImageView) findViewById(R.id.image_voltar);

        CardView[] click = new CardView[]{cardPdf,cardNivel3,cardNivel4,cardNivel5};

        for(int i=0; i<click.length;i++){
            click[i].setOnClickListener(this);
        }

        voltar.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.card2:
                PdfClass pdfClass1 = new PdfClass();
                pdfClass1.Callpdf(pagPdfCompleto,this,pdfName);

                break;
            case R.id.card3:

                // Parametros enviados diretamente para o .DefaultPage
                PdfClass pdfClass2 = new PdfClass();
                pdfClass2.Callpdf(pagNivel3,this,pdfName);


                break;
            case R.id.card4:

                // Parametros enviados diretamente para o .DefaultPage
                PdfClass pdfClass3 = new PdfClass();
                pdfClass3.Callpdf(pagNivel4,this,pdfName);

                break;
            case R.id.card5:

                // Parametros enviados diretamente para o .DefaultPage
                PdfClass pdfClass4 = new PdfClass();
                pdfClass4.Callpdf(pagNivel5,this,pdfName);
                break;

            case R.id.image_voltar:
                finish();
                onBackPressed();
                break;
        }
    }
}
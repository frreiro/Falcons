package com.example.falcons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import com.example.falcons.classesExtras.PdfClass;
import com.example.falcons.classesExtras.MudancasTela;


public class FerramentasActivity extends AppCompatActivity implements View.OnClickListener {

    //Define as páginas do docuemento para cada tópico
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

        //Coloca em tela cheia
        View v = getWindow().getDecorView();
        MudancasTela navbar = new MudancasTela();
        navbar.escondeBarraDeNavegacao(v, 900);

        //Traz os componentes do layout e transforma em variáveis
        CardView cardPdf = (CardView) findViewById(R.id.cardPdf);
        CardView cardEpi = (CardView) findViewById(R.id.cardEpi);
        CardView cardCorte = (CardView) findViewById(R.id.cardCorte);
        CardView cardMedicao = (CardView) findViewById(R.id.cardMedicao);
        CardView cardAperto = (CardView) findViewById(R.id.cardAperto);
        CardView cardAlicates = (CardView) findViewById(R.id.cardAlicates);
        CardView cardFuradeiras = (CardView) findViewById(R.id.cardFuradeiras);
        CardView cardSolda = (CardView) findViewById(R.id.cardSolda);
        ImageView voltar = (ImageView) findViewById(R.id.image_voltar);

        //Add os componentes em um array
        CardView[] click = new CardView[]{ cardPdf, cardEpi,cardCorte,cardMedicao,cardAperto,cardAlicates, cardFuradeiras, cardSolda};

        //Define que os compoenentes dentro do array são clicáveis
        for(int i=0; i<click.length;i++){
            click[i].setOnClickListener(this);
        }

        //Define que os compoenentes voltar é clicável
        voltar.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        //Ao clicar verifica pelo ID do componente
        //Se o caso existir, irá chamar a tela de pdf passando o pdf que terá que abrir e a página
        switch (view.getId()) {

            case R.id.cardPdf:
                PdfClass pdfClass1 = new PdfClass();
                pdfClass1.ChamarTelaPdf(pagPdfCompleto,this,pdfName);
                break;
            case R.id.cardEpi:

                PdfClass pdfClass2 = new PdfClass();
                pdfClass2.ChamarTelaPdf(pagEpi,this,pdfName);
                break;

            case R.id.cardCorte:

                PdfClass pdfClass3 = new PdfClass();
                pdfClass3.ChamarTelaPdf(pagCorte,this,pdfName);
                break;

            case R.id.cardMedicao:

                PdfClass pdfClass4 = new PdfClass();
                pdfClass4.ChamarTelaPdf(pagMedicao,this,pdfName);
                break;

            case R.id.cardAperto:

                PdfClass pdfClass5 = new PdfClass();
                pdfClass5.ChamarTelaPdf(pagAperto,this,pdfName);
                break;

            case R.id.cardAlicates:

                PdfClass pdfClass6 = new PdfClass();
                pdfClass6.ChamarTelaPdf(pagAlicates,this,pdfName);
                break;

            case R.id.cardFuradeiras:

                PdfClass pdfClass7 = new PdfClass();
                pdfClass7.ChamarTelaPdf(pagFuradeiras,this,pdfName);
                break;

            case R.id.cardSolda:
                PdfClass pdfClass8 = new PdfClass();
                pdfClass8.ChamarTelaPdf(pagSolda,this,pdfName);
                break;

            case R.id.image_voltar:
                finish();
                Intent intent = new Intent(FerramentasActivity.this, MenuActivity.class);
                startActivity(intent);
                break;
        }
    }



}


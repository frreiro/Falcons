package com.example.falcons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.falcons.classesExtras.PdfClass;
import com.example.falcons.classesExtras.MudancasTela;

public class InstrucaoActivity extends AppCompatActivity  implements View.OnClickListener {

    //Define as páginas do docuemento para cada tópico
    int pagPdfCompleto = 0;
    int pagNivel3 = 4;
    int pagNivel4 = 22;
    int pagNivel5 = 64;
    String pdfName = "Manual_de_Montagem.pdf";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrucao);

        //Coloca em tela cheia
        View v = getWindow().getDecorView();
        MudancasTela navbar = new MudancasTela();
        navbar.escondeBarraDeNavegacao(v, 900);

        //Traz os componentes do layout e transforma em variáveis
        CardView cardPdf = (CardView) findViewById(R.id.card2);
        CardView cardNivel3 = (CardView) findViewById(R.id.card3);
        CardView cardNivel4 = (CardView) findViewById(R.id.card4);
        CardView cardNivel5 = (CardView) findViewById(R.id.card5);
        ImageView voltar = (ImageView) findViewById(R.id.image_voltar);

        //Add os componentes em um array
        CardView[] click = new CardView[]{cardPdf,cardNivel3,cardNivel4,cardNivel5};

        //Define que os compoenentes dentro do array são clicáveis
        for(int i=0; i<click.length;i++){
            click[i].setOnClickListener(this);
        }

        //Define que os compoenentes voltar é clicável
        voltar.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //Ao clicar verifica pelo ID do componente
            //Se o caso existir, irá chamar a tela de pdf passando o pdf que terá que abrir e a página
            case R.id.card2:
                PdfClass pdfClass1 = new PdfClass();
                pdfClass1.ChamarTelaPdf(pagPdfCompleto,this,pdfName);

                break;
            case R.id.card3:

                // Parametros enviados diretamente para o .DefaultPage
                PdfClass pdfClass2 = new PdfClass();
                pdfClass2.ChamarTelaPdf(pagNivel3,this,pdfName);


                break;
            case R.id.card4:

                // Parametros enviados diretamente para o .DefaultPage
                PdfClass pdfClass3 = new PdfClass();
                pdfClass3.ChamarTelaPdf(pagNivel4,this,pdfName);

                break;
            case R.id.card5:

                // Parametros enviados diretamente para o .DefaultPage
                PdfClass pdfClass4 = new PdfClass();
                pdfClass4.ChamarTelaPdf(pagNivel5,this,pdfName);
                break;

            case R.id.image_voltar:
                finish();
                onBackPressed();
                break;
        }
    }
}
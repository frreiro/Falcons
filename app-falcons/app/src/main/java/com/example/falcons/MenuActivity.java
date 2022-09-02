package com.example.falcons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.falcons.checklist.CheckListMenu;
import com.example.falcons.manutencao.ManutencaoMenu;
import com.example.falcons.classesExtras.PdfClass;
import com.example.falcons.classesExtras.MudancasTela;
import com.unity3d.player.UnityPlayerActivity;

public class MenuActivity extends AppCompatActivity {

    String pdfName = "Manual_de_Ferramentas.pdf";
    String pdfNamePart = "Part_number.pdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Coloca em tela cheia
        View menudecorView = getWindow().getDecorView();
        MudancasTela navbar = new MudancasTela();
        navbar.escondeBarraDeNavegacao(menudecorView, 1500);


    }
        //As funções são chamadas diretamente do componente em res/layout/activity_menu.xml
        //O nome da função é passado pelo android:onClick='nomeDaFuncao'
        public void startRA(View view){
            Intent intent = new Intent(MenuActivity.this, UnityPlayerActivity.class);
            startActivity(intent);
        }
        public void startFerramentas(View view){
            Intent intent2 = new Intent(MenuActivity.this, FerramentasActivity.class);
            startActivity(intent2);
        }
        public void startMontagem(View view){
            Intent intent3 = new Intent(MenuActivity.this, InstrucaoActivity.class);
            startActivity(intent3);
        }
        public void startPart(View view){
            PdfClass pdfClass = new PdfClass();
            pdfClass.ChamarTelaPdf(0, this, pdfNamePart);
        }

        public void startManutencao(View view){
            Intent intent6 = new Intent(MenuActivity.this, ManutencaoMenu.class);
            startActivity(intent6);
        }


        public void startChecklist(View view){
            Intent intent4 = new Intent(MenuActivity.this, CheckListMenu.class);
            startActivity(intent4);
        }


}

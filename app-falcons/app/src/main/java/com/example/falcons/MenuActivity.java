package com.example.falcons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.falcons.check.CheckListMenu;
import com.example.falcons.manutencao.ManutencaoActivity;
import com.example.falcons.manutencao.ManutencaoSetores;
import com.example.falcons.ui.PdfClass;
import com.example.falcons.ui.Ui;
import com.unity3d.player.UnityPlayerActivity;

public class MenuActivity extends AppCompatActivity {

    String pdfName = "Manual_de_Ferramentas.pdf";
    String pdfNamePart = "Part_number.pdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        View menudecorView = getWindow().getDecorView();
        Ui navbar = new Ui();
        navbar.hideNavBar(menudecorView, 1500);


    }

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
            pdfClass.Callpdf(0, this, pdfNamePart);
        }

        public void startManutencao(View view){
            Intent intent6 = new Intent(MenuActivity.this, ManutencaoSetores.class);
            startActivity(intent6);
        }


        public void startChecklist(View view){
            Intent intent4 = new Intent(MenuActivity.this, CheckListMenu.class);
            startActivity(intent4);

        }


}

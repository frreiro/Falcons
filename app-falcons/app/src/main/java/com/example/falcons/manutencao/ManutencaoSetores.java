package com.example.falcons.manutencao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.falcons.MenuActivity;
import com.example.falcons.R;

public class ManutencaoSetores extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manutencao_setores_activity);

        CardView aerodinamica = (CardView) findViewById(R.id.aero_card_manutencao);
        CardView chassi = (CardView) findViewById(R.id.chassi_card_manutencao);
        CardView eletrica = (CardView) findViewById(R.id.eletrica_card_manutencao);
        CardView freio = (CardView) findViewById(R.id.freio_card_manutencao);
        CardView powertrain = (CardView) findViewById(R.id.powertrain_card_manutencao);
        CardView suspensao = (CardView) findViewById(R.id.suspensao_card_manutencao);
        ImageView voltar = (ImageView) findViewById(R.id.image_voltar);

        aerodinamica.setOnClickListener(this);
        chassi.setOnClickListener(this);
        eletrica.setOnClickListener(this);
        freio.setOnClickListener(this);
        powertrain.setOnClickListener(this);
        suspensao.setOnClickListener(this);
        voltar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.aero_card_manutencao:
                Intent intentAero = new Intent(ManutencaoSetores.this, ManutencaoActivity.class);
                intentAero.putExtra("setor","Aerodinâmica");
                startActivity(intentAero);
                break;
            case R.id.chassi_card_manutencao:
                Intent intentChassi = new Intent(ManutencaoSetores.this,  ManutencaoActivity.class);
                intentChassi.putExtra("setor","Chassi");
                startActivity(intentChassi);
                break;
            case R.id.eletrica_card_manutencao:
                Intent intentEletrica = new Intent(ManutencaoSetores.this, ManutencaoActivity.class);
                intentEletrica.putExtra("setor","Elétrica");
                startActivity(intentEletrica);
                break;
            case R.id.freio_card_manutencao:
                Intent intentFreio = new Intent(ManutencaoSetores.this,  ManutencaoActivity.class);
                intentFreio.putExtra("setor","Freio");
                startActivity(intentFreio);
                break;
            case R.id.powertrain_card_manutencao:
                Intent intentPower = new Intent(ManutencaoSetores.this,  ManutencaoActivity.class);
                intentPower.putExtra("setor","PowerTrain");
                startActivity(intentPower);
                break;
            case R.id.suspensao_card_manutencao:
                Intent intentSuspensao = new Intent(ManutencaoSetores.this,  ManutencaoActivity.class);
                intentSuspensao.putExtra("setor","Suspensão");
                startActivity(intentSuspensao);
                break;
            case R.id.image_voltar:
                Intent intentVoltar = new Intent(ManutencaoSetores.this,  MenuActivity.class);
                startActivity(intentVoltar);
                break;

        }
    }
}
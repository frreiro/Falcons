package com.example.falcons.classesExtras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.falcons.MenuActivity;
import com.example.falcons.R;

public class Load extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        //Pega o objeto View (controla a tela) e adiciona mudanças na tela, no caso, esconde a barra de navegação
        View decorView = getWindow().getDecorView();
        MudancasTela navbar = new MudancasTela();
        navbar.escondeBarraDeNavegacao(decorView, 900);

        ImageView imagem = (ImageView) findViewById(R.id.loading);

        //Começa a animação da imagem
        imagem.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fadeinout));

        //Depois de 5s, muda para a tela de menu
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Load.this, MenuActivity.class);
                startActivity(intent);
                finish();

            }
        }, 5000);
    }

}

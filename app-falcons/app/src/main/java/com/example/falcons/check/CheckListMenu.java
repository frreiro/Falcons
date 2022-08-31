package com.example.falcons.check;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.example.falcons.R;

public class CheckListMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist_menu);

    }

    public void continuar(View view) {
        Intent intent = new Intent(com.example.falcons.check.CheckListMenu.this, CheckListMotorista.class);
        Bundle dataDados = new Bundle();
        dataDados.putString("tipo", "continuar");
        intent.putExtras(dataDados);
        startActivity(intent);
    }

    public void comecar(View view) {
        Intent intent = new Intent(com.example.falcons.check.CheckListMenu.this, CheckListMotorista.class);
        Bundle dataDados = new Bundle();
        dataDados.putString("tipo", "começar");
        intent.putExtras(dataDados);
        startActivity(intent);
    }

    public void voltar(View view) {
        finish();
        onBackPressed();
    }
}
package com.example.falcons.checklist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.falcons.R;

public class CheckListMotorista extends AppCompatActivity {

    private EditText motorista;
    String tipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist_motorista_activity);


        Bundle bundle  = getIntent().getExtras();
        tipo = bundle.getString("tipo");
        motorista = findViewById(R.id.edtMotorista);

    }

    public void next(View view) {
        String piloto = motorista.getText().toString().toUpperCase(); // Nome recebido do input
        if( piloto.isEmpty() || piloto.equals(" ")){
            Toast toast = Toast.makeText(this, "O CARRO VAI ANDAR SOZINHO?", Toast.LENGTH_LONG);
            toast.show();
        }else {
            Intent intent = new Intent(CheckListMotorista.this, CheckListData.class);
            Bundle pilotoDados = new Bundle();
            pilotoDados.putString("piloto", piloto);
            pilotoDados.putString("tipo", tipo);
            intent.putExtras(pilotoDados);
            startActivity(intent);
        }
    }

    public void voltar(View view){
        finish();
        onBackPressed();
    }
}
package com.example.falcons.checklist;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.falcons.R;
import java.util.Calendar;

import static android.widget.Toast.LENGTH_LONG;

public class CheckListData extends AppCompatActivity {
    private EditText data;
    String nomePiloto;
    String tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist_data_activity);

        //Recebe os paraêmtros passados da tela de menu e motorista
        Bundle bundle  = getIntent().getExtras();
        nomePiloto = bundle.getString("piloto");
        tipo = bundle.getString("tipo");
        Log.i("TAG", "onCreate: "+tipo );
        data = findViewById(R.id.edtData);
        ajusteData(data);
    }
    public void next(View view) {
        String novaData = formatarData(data.getText().toString());
        if( novaData.isEmpty() || novaData.equals(" ")) {
            Toast toast = Toast.makeText(this, "Me fala que dia é hoje", LENGTH_LONG);
            toast.show();
        }else {
            Intent intent = new Intent(CheckListData.this, CheckListAntes.class);
            Bundle dataDados = new Bundle();
            dataDados.putString("data", novaData);
            dataDados.putString("piloto", nomePiloto);
            dataDados.putString("remetente", tipo);
            intent.putExtras(dataDados);
            startActivity(intent);
        }
    }

    //Formata a data para salvar no banco sem a "/"
    private String formatarData(String data){
        String novaData = data.replaceAll("/", "-");
        return novaData;
    }

    //Ajusta o editText para mostrar e adapta as condições de um calendário
    private void ajusteData (final EditText data){
        data.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private final String ddmmyyyy = "DDMMYYYY";
            private final Calendar cal = Calendar.getInstance();
            String clean;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    clean = s.toString().replaceAll("[^\\d.]|\\.", "");
                    String cleanC = current.replaceAll("[^\\d.]|\\.", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;

                    }
                    //Ajuste quando prssionar delete ao lado de uma barra
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8) {
                        clean = clean + ddmmyyyy.substring(clean.length());

                    } else {
                        //essa parte garante que quando terminarmos de inserir os numeros
                        //a data esta correta, ajustando de outra forma
                        int day = Integer.parseInt(clean.substring(0, 2));
                        int mon = Integer.parseInt(clean.substring(2, 4));
                        int year = Integer.parseInt(clean.substring(4, 8));

                        mon = mon < 1 ? 1 : Math.min(mon, 12);
                        cal.set(Calendar.MONTH, mon - 1);
                        year = (year < 1900) ? 1900 : Math.min(year, 2100);
                        cal.set(Calendar.YEAR, year);

                        // Primeiro defina o ano para que a linha abaixo funcione
                        //com ano bissextos - caso contrário a data por exemplo
                        // 29/02/2012 seria corrigido para 28/02/2012

                        day = Math.min(day, cal.getActualMaximum(Calendar.DATE));
                        clean = String.format("%02d%02d%02d", day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    if (cl == 0 ) {
                        current = "";
                    }else {
                        current = clean;
                    }
                    sel = Math.max(sel, 0);
                    data.setText(current);
                    data.setSelection(Math.min(sel, current.length()));
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

    }

    public void voltar(View view){
        finish();
        onBackPressed();
    }
}
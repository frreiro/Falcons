package com.example.falcons.manutencao;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.falcons.R;
import com.example.falcons.classesExtras.PdfClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ManutencaoActivity extends AppCompatActivity {

    //Define as variáveis globais
    private RecyclerView recyclerManu;
    private String setores;
    private String[] tituloArray;
    private String[] descriptionArray;
    private String[] periodicidadeArray;

    String pdfName = "Manual_de_Ferramentas.pdf";
    int pagSetorPdf = 0;


    List<Manutencao> listManu = new ArrayList<>();

    //Inicializa o firebase, pegando a instância (url) e cria o caminho em manutencao, ou seja, tudo será adicionado dentro de manutencao
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://falcons-ufformula-default-rtdb.firebaseio.com/");
    DatabaseReference myRef = database.getReference("manutencao");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manutencao_activity);


        // Pega a intent de ManutencaoSetores
        Intent it = getIntent();
        setores = it.getStringExtra("setor");

        // Troca o título da pagina de acordo com os setores
        TextView textSetor = (TextView) findViewById(R.id.text_setor_id);
        textSetor.setText(setores);
        textSetor.setAllCaps(true);

        // Abre a pagina do manual de manutenção de acordo com o setor
        ImageView infoPagina = (ImageView) findViewById(R.id.info_manutencao_setor);
        infoPagina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Cria um alertDialog para confirmar a transferencia de tela
                AlertDialog.Builder dialog = new AlertDialog.Builder(ManutencaoActivity.this);
                dialog.setTitle("Manual de Manutenção");
                dialog.setMessage("Você quer abrir o manual de manutenção preventiva na página do setor "+ setores + " ?");

                // Add os botões e suas funções
                dialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        // Abre o pdfClass com o arquivo do Manual de Manutenção

                        PdfClass pdfClass1 = new PdfClass();
                        pdfClass1.ChamarTelaPdf(pagSetorPdf,ManutencaoActivity.this,pdfName);
                    }
                });
                dialog.setPositiveButton("MELHOR DEPOIS", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                dialog.show();
                // FIM ALERTDIALOG

            }
        });



        // Inicia o adapter do RecyclerView
        recyclerManu = (RecyclerView) findViewById(R.id.ManuRecyclerId);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);
        recyclerManu.setLayoutManager(llm);
        //FIM DA INICIÇÃO DO RECYCLERVIEW

        //Preenche as variáveis tituloArray, descriptionArray, periodicidadeArray de acordo com setor (escolhido no menu)
        criaTitulo();

        //Preenche o objeto Manutencao com os arrays
        for (int i = 0; i < tituloArray.length; i++) {
            Manutencao manutencao = new Manutencao(tirarAcento(setores));
            manutencao.setTitulo(tituloArray[i]);
            manutencao.setPeriodicidade(periodicidadeArray[i]);
            manutencao.setDescription(descriptionArray[i]);
            //Pega a data -> dia/mes -> da ultima manutenção no banco de dados
            myRef.child(tirarAcento(setores)).child(tirarAcento(tituloArray[i])).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    }
                    else {
                        manutencao.setData(String.valueOf(task.getResult().getValue()));
                        listManu.add(manutencao);
                        //Aplica o adapter do recyclerView, escolhendo o ManutencaoAdapter como adapter, e passando a listManu (lista com os dados do objeto de manunteçao) como parâmetro
                        recyclerManu.setAdapter(new ManutencaoAdapter(listManu,ManutencaoActivity.this));
                    }
                }
            });
        }









    }

    private void criaTitulo (){
        switch (setores) {
            case "Aerodinâmica":

                //Alterar pagina de acordo com o manual em todos os cases
                pagSetorPdf = 0;

                // traz os itens-array criados no @string de acordo com seu respectivo setor
                tituloArray = getResources().getStringArray(R.array.aero_titulo_array);
                descriptionArray = getResources().getStringArray(R.array.aero_descricao_array);
                periodicidadeArray = getResources().getStringArray(R.array.aero_periodicidade_array);

                break;

            case "Chassi":

                //Alterar pagina de acordo com o manual em todos os cases
                pagSetorPdf = 1;

                // traz os itens-array criados no @string de acordo com seu respectivo setor
                tituloArray = getResources().getStringArray(R.array.chassi_titulo_array);
                descriptionArray = getResources().getStringArray(R.array.chassi_descricao_array);
                periodicidadeArray = getResources().getStringArray(R.array.chassi_periodicidade_array);
                break;


            case "Elétrica":

                //Alterar pagina de acordo com o manual em todos os cases
                pagSetorPdf = 1;

                // traz os itens-array criados no @string de acordo com seu respectivo setor
                tituloArray = getResources().getStringArray(R.array.eletrica_titulo_array);
                descriptionArray = getResources().getStringArray(R.array.eletrica_descricao_array);
                periodicidadeArray = getResources().getStringArray(R.array.eletrica_periodicidade_array);
                break;

            case "Freio":

                //Alterar pagina de acordo com o manual em todos os cases
                pagSetorPdf = 1;

                // traz os itens-array criados no @string de acordo com seu respectivo setor
                tituloArray = getResources().getStringArray(R.array.freio_titulo_array);
                descriptionArray = getResources().getStringArray(R.array.freio_descricao_array);
                periodicidadeArray = getResources().getStringArray(R.array.freio_periodicidade_array);
                break;

            case "PowerTrain":

                //Alterar pagina de acordo com o manual em todos os cases
                pagSetorPdf = 1;

                // traz os itens-array criados no @string de acordo com seu respectivo setor
                tituloArray = getResources().getStringArray(R.array.power_titulo_array);
                descriptionArray = getResources().getStringArray(R.array.power_descricao_array);
                periodicidadeArray = getResources().getStringArray(R.array.power_periodicidade_array);
                break;

            case "Suspensão":

                //Alterar pagina de acordo com o manual em todos os cases
                pagSetorPdf = 1;

                // traz os itens-array criados no @string de acordo com seu respectivo setor
                tituloArray = getResources().getStringArray(R.array.susp_titulo_array);
                descriptionArray = getResources().getStringArray(R.array.susp_descricao_array);
                periodicidadeArray = getResources().getStringArray(R.array.susp_periodicidade_array);
                break;
        }

    }

     public void voltar(View view){
         finish();
         onBackPressed();
     }

    public String tirarAcento(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("").toLowerCase();
    }
}


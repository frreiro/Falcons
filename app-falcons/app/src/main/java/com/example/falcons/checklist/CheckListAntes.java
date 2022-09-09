package com.example.falcons.checklist;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.falcons.checklist.checklistAdapters.ItemModel;
import com.example.falcons.checklist.checklistAdapters.ItemsAdapterAntes;

import com.example.falcons.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 *  activity do checklist c/ um fragment
 *
 */

public class CheckListAntes extends AppCompatActivity{

    //Define as variáveis globais
    String telaAntiga;
    String nomePiloto;
    String data;
    RecyclerView recyclerView;

    //Inicializa o firebase, pegando a instância (url) e cria o caminho em checklist, ou seja, tudo será adicionado dentro de checklist
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://falcons-ufformula-default-rtdb.firebaseio.com/");
    DatabaseReference myRef = database.getReference("checklist");

    FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist_antes_activity);

        //Recebe os parâmetros passados pelo menu
        Bundle bundle = getIntent().getExtras();
        telaAntiga = bundle.getString("remetente");
        nomePiloto = bundle.getString("piloto");
        data = bundle.getString("data");

        //Inicia uma list de arrays
        List<ItemModel> itemModels = new ArrayList<>();

        //Adiciona o fragmento -> checklist depois -> fragmento:  pedaço de uma tela ( no caso outra tela ) dinâmica
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.frameDepois, new com.example.falcons.checklist.CheckListDepois(nomePiloto, telaAntiga, data));
        ft.commit();    // add um fragment

        //Cria uma variável que guarda as informações de adapter do recycler view, chamando as configurações do adapter chamado ItemsAdapterAntes
        // e adicionando a lista itemModels dentro do adapter
        ItemsAdapterAntes adapterAntes = new ItemsAdapterAntes(itemModels);


        // add o reclyclerview
        recyclerView = (RecyclerView) findViewById(R.id.checkRecyclerId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        //Recebe o array de strings do @strings contendo as informações de check do checklist
        String[] stringsArray = getResources().getStringArray(R.array.array_list_check);

        //Verifica de onde veio, e adiciona as informações do array no objeto itemModel
        // em seguida, adiciona o objeto dentro da lista itemModels
        //configura o adapter para chamar o ItemsAdapterAntes
        if("começar".equals(telaAntiga)){
            for(int i=0; i < stringsArray.length; i++){
                ItemModel itemModel = new ItemModel(stringsArray[i], false);
                itemModel.setData(data);
                itemModel.setPiloto(nomePiloto);
                itemModel.setTelaAntiga(telaAntiga);
                itemModel.setFragmento("antes");
                itemModels.add(itemModel);
                Log.i("TAG", "onCreateView2: "+ itemModels);
            }
            recyclerView.setAdapter(adapterAntes);
        }

        //Realiza a mesma situação do de cima, porém verifica no banco de dados e adiciona os valores que estão lá
        if("continuar".equals(telaAntiga)){
            for(int i=0; i < stringsArray.length; i++){
                String nomePeca = stringsArray[i];
                myRef.child(nomePiloto).child(data).child("antes").child(nomePeca).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()) {
                            // não sucesso
                            Log.e("firebase", "Error getting data", task.getException());
                        } else {
                            //sucesso
                            if ((Boolean) task.getResult().getValue() != null) {
                                ItemModel itemModel = new ItemModel(nomePeca, (Boolean) task.getResult().getValue());
                                itemModel.setData(data);
                                itemModel.setPiloto(nomePiloto);
                                itemModel.setTelaAntiga(telaAntiga);
                                itemModel.setFragmento("antes");
                                itemModels.add(itemModel);
                            } else {
                                // CASO NÃO TENHA NENNHUM DADO NO BD
                                Log.i("TAG", "USUARIO NÃO ENCONTRADO: ");
                            }
                            recyclerView.setAdapter(adapterAntes);
                        }
                    }
                });
            }
        }


    }

    public void voltar(View view){
        finish();
        onBackPressed();
    }
}
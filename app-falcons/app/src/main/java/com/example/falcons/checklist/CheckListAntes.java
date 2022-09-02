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

    String telaAntiga;
    String nomePiloto;
    String data;
    RecyclerView recyclerView;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://falcons-ufformula-default-rtdb.firebaseio.com/");
    DatabaseReference myRef = database.getReference("checklist");

    FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist_antes_activity);

        Bundle bundle = getIntent().getExtras();
        telaAntiga = bundle.getString("remetente");
        nomePiloto = bundle.getString("piloto");
        data = bundle.getString("data");

        List<ItemModel> itemModels = new ArrayList<>();


        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.frameDepois, new com.example.falcons.checklist.CheckListDepois(nomePiloto, telaAntiga, data));
        ft.commit();    // add um fragment

        ItemsAdapterAntes adapterAntes = new ItemsAdapterAntes(itemModels);

        recyclerView = (RecyclerView) findViewById(R.id.checkRecyclerId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        // add o reclyclerview


        String[] stringsArray = getResources().getStringArray(R.array.array_list_check);
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

//    // abre o dialog
//    private void openDialog () {
//        ExempleDialog exempleDialog = new ExempleDialog();
//        exempleDialog.show(getSupportFragmentManager(), "exemplo dialog");
//
//    }
//
//    // receber o texto do edittext
//    @Override
//    public void aplicarTexto(String observacao) {
//        Toast.makeText(com.example.falcons.check.CheckListAntes.this, observacao  + " na posição: " + positionLongClick,Toast.LENGTH_LONG).show();
//        // vai receber o que tiver escrito no editText
//    }
    public void voltar(View view){
        finish();
        onBackPressed();
    }
}
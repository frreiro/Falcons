package com.example.falcons.checklist;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.falcons.check.backend.AdapterClass;
import com.example.falcons.checklist.checklistAdapters.ItemModel;
//import com.example.falcons.check.backend.Names;
import com.example.falcons.R;
//import com.example.falcons.check.backend.RecyclerItemClickListener;
import com.example.falcons.checklist.checklistAdapters.ItemsAdapterDepois;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link com.example.falcons.checklist.CheckListDepois#newInstance} factory method to
 * create an instance of this fragment.
 */


public class CheckListDepois extends Fragment {

    RecyclerView recyclerView;
    String nomePiloto;
    String telaAntiga;
    String data;

    //Inicializa o firebase, pegando a instância (url) e cria o caminho em checklist, ou seja, tudo será adicionado dentro de checklist
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://falcons-ufformula-default-rtdb.firebaseio.com/");
    DatabaseReference myRef = database.getReference("checklist");

    public CheckListDepois(String piloto,String telaAntiga, String data) {
        this.nomePiloto = piloto;
        this.telaAntiga = telaAntiga;
        this.data = data;
    }

    public static com.example.falcons.checklist.CheckListDepois newInstance() {
        com.example.falcons.checklist.CheckListDepois fragment = new com.example.falcons.checklist.CheckListDepois("", "", "");
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Pega o layout e adapta nesse fragmento
        View view = inflater.inflate(R.layout.checklist_depois_fragment, container, false);

        //Inicia uma list de arrays
        List<ItemModel> itemModels = new ArrayList<>();

        //Cria uma variável que guarda as informações de adapter do recycler view, chamando as configurações do adapter chamado ItemsAdapterAntes
        // e adicionando a lista itemModels dentro do adapter
        ItemsAdapterDepois adapterDepois = new ItemsAdapterDepois(itemModels);

        // add o reclyclerview
        recyclerView = (RecyclerView) view.findViewById(R.id.checkRecyclerId);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        //Recebe o array de strings do @strings contendo as informações de check do checklist
        String[] stringsArray = getResources().getStringArray(R.array.array_list_check);

        //Verifica de onde veio, e adiciona as informações do array no objeto itemModel
        // em seguida, adiciona o objeto dentro da lista itemModels
        //configura o adapter para chamar o ItemsAdapterDepois
        if("começar".equals(telaAntiga)){
            for(int i=0; i < stringsArray.length; i++){
                ItemModel itemModel = new ItemModel(stringsArray[i], false);
                itemModel.setData(data);
                itemModel.setPiloto(nomePiloto);
                itemModel.setTelaAntiga(telaAntiga);
                itemModel.setFragmento("depois");
                itemModels.add(itemModel);
                Log.i("TAG", "onCreateView2: "+ itemModels);
            }
            recyclerView.setAdapter(adapterDepois);
        }

        //Realiza a mesma situação do de cima, porém verifica no banco de dados e adiciona os valores que estão lá
        if("continuar".equals(telaAntiga)){
            for(int i=0; i < stringsArray.length; i++){
                String nomePeca = stringsArray[i];
                myRef.child(nomePiloto).child(data).child("depois").child(nomePeca).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
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
                                itemModel.setFragmento("depois");
                                itemModels.add(itemModel);
                            } else {
                                // CASO NÃO TENHA NENNHUM DADO NO BD
                                Log.i("TAG", "USUARIO NÃO ENCONTRADO: ");
                            }
                            recyclerView.setAdapter(adapterDepois);
                        }
                    }
                });
            }
        }

        return view;
    }
}
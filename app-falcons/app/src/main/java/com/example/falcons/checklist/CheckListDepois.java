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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.checklist_depois_fragment, container, false);

        List<ItemModel> itemModels = new ArrayList<>();
        Log.i("TAG", "onCreateView: "+ itemModels);

        ItemsAdapterDepois adapterDepois = new ItemsAdapterDepois(itemModels);

        recyclerView = (RecyclerView) view.findViewById(R.id.checkRecyclerId);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
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
                itemModel.setFragmento("depois");
                itemModels.add(itemModel);
                Log.i("TAG", "onCreateView2: "+ itemModels);
            }
            recyclerView.setAdapter(adapterDepois);
        }

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
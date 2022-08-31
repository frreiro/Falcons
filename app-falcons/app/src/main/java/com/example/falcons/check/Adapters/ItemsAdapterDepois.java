package com.example.falcons.check.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.falcons.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ItemsAdapterDepois extends RecyclerView.Adapter<ItemsAdapterDepois.ItemsAdapterDepoisViewHolder> {

    private final List<ItemModel> itemModels;
    String telaAnterior; // continuar ou começar
    String piloto; // nome do motorista
    String data; // data do teste
    String fragmento; // antes ou depois


    FirebaseDatabase database = FirebaseDatabase.getInstance("https://falcons-ufformula-default-rtdb.firebaseio.com/");
    DatabaseReference myRef = database.getReference("checklist");



    public ItemsAdapterDepois(List<ItemModel> itemModels) {
        this.itemModels = itemModels;
    }

    @NonNull
    @Override
    public ItemsAdapterDepoisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.checklist_item_view, parent,false);

        return new ItemsAdapterDepoisViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsAdapterDepoisViewHolder holder, int position) {
        ItemModel item = itemModels.get(position);
        holder.textSwitch.setText(item.text);
        holder.aSwitch.setChecked(item.on);

        Log.i("TAG", "onBindViewHolder2: " + item.text + item.on);
        if("começar".equals(telaAnterior)){
            escreverNomeFirebase();
            Log.i("TAG", "onBindViewHolder: "+ data);
        }

        telaAnterior = item.getTelaAntiga();
        data = item.getData();
        piloto =item.getPiloto();
        fragmento = item.getFragmento();

        holder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int adapterPosition = holder.getAdapterPosition();
                ItemModel tapped = itemModels.get(adapterPosition);
                itemModels.set(adapterPosition, new ItemModel(tapped.text, isChecked));
                mudarOsDadosFirbase(tapped.text, isChecked);
            }
        });
    }

    @Override
    public void onViewRecycled(@NonNull ItemsAdapterDepoisViewHolder holder) {
        super.onViewRecycled(holder);
        holder.aSwitch.setOnCheckedChangeListener(null);

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return itemModels.size();
    }

    static class  ItemsAdapterDepoisViewHolder extends RecyclerView.ViewHolder{

        private TextView textSwitch;
        private SwitchCompat aSwitch;

        public  ItemsAdapterDepoisViewHolder(View itemView){
            super(itemView);

            textSwitch = itemView.findViewById(R.id.switch1);
            aSwitch = (SwitchCompat) itemView.findViewById(R.id.switch1);
        }


    }


    /* --- coloca todos os valores como falso (valores inicial) ---*/
    public void escreverNomeFirebase(){
        for(int i=0; i < itemModels.size(); i++){
            String pecaNome = itemModels.get(i).text;
            myRef.child(piloto).child(String.valueOf(data)).child(fragmento).child(pecaNome).setValue(false);
        }
    }

    /* --- Grava os valores no firebase ---*/
    private void mudarOsDadosFirbase(String name ,boolean value){
        myRef.child(piloto).child(data).child(fragmento).child(name).setValue(value);
        Log.d("TAG", "mudarOsDadosFirbase: " +name+": " + value);
    }




}

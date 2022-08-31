package com.example.falcons.manutencao;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.falcons.R;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ManutencaoAdapter extends RecyclerView.Adapter<ManutencaoAdapter.ManuViewHolder>{

    private final List<Manutencao> manutencaoList;
    private final Context context;

    public ManutencaoAdapter(List<Manutencao> manutencaoList, Context context) {
        this.manutencaoList = manutencaoList;
        this.context = context;
    }


    FirebaseDatabase database = FirebaseDatabase.getInstance("https://falcons-ufformula-default-rtdb.firebaseio.com/");
    DatabaseReference myRef = database.getReference("manutencao");

    @NonNull
    @Override
    public ManuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manu, parent, false);

        return new ManuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ManuViewHolder holder, int position) {
        String titulo = manutencaoList.get(position).getTitulo();
        String setor = manutencaoList.get(position).getSetor();
        holder.textTitulo.setText(titulo);
        holder.textDescripition.setText(manutencaoList.get(position).getDescription());
        holder.textPeriodicidade.setText(manutencaoList.get(position).getPeriodicidade());
        holder.textdata.setText(manutencaoList.get(position).getData());
        holder.textdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Inicia o aletDialog dentro da View
                AlertDialog.Builder data = new AlertDialog.Builder(context);
                data.setTitle("Altere a data");
                data.setMessage("Coloque a data desejada");

                //Cria o layout FrameLayout
                FrameLayout container = new FrameLayout(context);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.leftMargin = context.getResources().getDimensionPixelSize(R.dimen.dialog_margin_left);
                params.rightMargin = context.getResources().getDimensionPixelSize(R.dimen.dialog_margin_right);

                // Cria o EditText
                final EditText input = new EditText(context);
                input.setHint("Ex: 26/06");
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                input.setSingleLine();
                input.setLayoutParams(params);

                // Cria a mascara
                SimpleMaskFormatter smf = new SimpleMaskFormatter("NN/NN");
                MaskTextWatcher mtw = new MaskTextWatcher(input,smf);
                input.addTextChangedListener(mtw);
                //FIM DA MASCARA

                // Add o editText na View
                container.addView(input);
                //FIM DO EDITTEXT

                // Add o layout no alertDialog
                data.setView(container);
                //FIM DO LAYOUT

                // Add os botões e suas funções
                data.setPositiveButton("PRONTO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(!input.getText().toString().trim().equals("") && input.getText().toString().trim().length() == 5){
                            myRef.child(setor).child(titulo.toLowerCase()).setValue(input.getText().toString().trim());
                            holder.textdata.setText(input.getText().toString().trim());
                        }else {
                            Toast.makeText(context, "Data inválida", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                data.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                data.show();
                //FIM DO ALERTDIALOG
            }
        });
    }

    @Override
    public int getItemCount() {
        return (manutencaoList != null && manutencaoList.size() > 0) ? manutencaoList.size() :  0 ;
    }

    static class ManuViewHolder extends RecyclerView.ViewHolder{

        private final TextView textTitulo;
        private final TextView textDescripition;
        private final TextView textPeriodicidade;
        private final TextView textdata;


        public ManuViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitulo = itemView.findViewById(R.id.title_Id);
            textDescripition = itemView.findViewById(R.id.descrition_Id);
            textPeriodicidade = itemView.findViewById(R.id.periodicidade_Id);
            textdata = itemView.findViewById(R.id.data_Id);

        }
    }
}






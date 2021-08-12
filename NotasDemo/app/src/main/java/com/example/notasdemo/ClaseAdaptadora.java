package com.example.notasdemo;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notasdemo.model.Note;
import com.example.notasdemo.model.UserWithNote;
import com.example.notasdemo.util.Cypher;

import java.util.List;

public class ClaseAdaptadora extends RecyclerView.Adapter<ClaseAdaptadora.ViewHolder> {

    private List<Note> dataList;
    private Activity context;
    private RoomBD baseDeDatos;

    public ClaseAdaptadora(Activity context, List<Note>dataList){
        this.context = context;
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_notas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClaseAdaptadora.ViewHolder holder, int position) {
        Note nota = dataList.get(position);
        baseDeDatos = RoomBD.getInstance(context);

        try {
            holder.txtNota.setText(Cypher.decrypt(nota.getText()));
        } catch (Exception exception) {

        }

        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note note = dataList.get(holder.getAdapterPosition());

                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialogo_actualizar);
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setLayout(width,height);
                dialog.show();

                final EditText editText = (EditText) dialog.findViewById(R.id.edtEscribirNota);
                Button btUpdate = (Button) dialog.findViewById(R.id.btnActualizar);

                try {
                    editText.setText(Cypher.decrypt(note.getText()));
                } catch (Exception exception) {

                }

                btUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        String uText = "";
                        try {
                            uText = Cypher.encrypt(editText.getText().toString().trim());
                        } catch (Exception exception) {

                        }
                        baseDeDatos.notaDao().update(note.getID(), uText);
                        dataList.clear();
                        UserWithNote userNote = baseDeDatos.userDao().getAllNote(note.getIdUser());
                        dataList.addAll(userNote.notas);
                        notifyDataSetChanged();
                    }
                });
            }
        });

        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note d = dataList.get(holder.getAdapterPosition());
                baseDeDatos.notaDao().delete(d);

                int position = holder.getAdapterPosition();
                dataList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, dataList.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void notifyDataSetChanged(List<Note> dataList) {
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtNota;
        ImageView btnEditar, btnEliminar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNota = (TextView) itemView.findViewById(R.id.txtNota);
            btnEditar= (ImageView) itemView.findViewById(R.id.btnEdit);
            btnEliminar = (ImageView) itemView.findViewById(R.id.btnBorrar);
        }
    }
}

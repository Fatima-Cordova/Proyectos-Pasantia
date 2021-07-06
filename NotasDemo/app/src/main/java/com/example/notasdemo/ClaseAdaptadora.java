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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClaseAdaptadora.ViewHolder holder, int position) {
        Note data = dataList.get(position);
        baseDeDatos = RoomBD.getInstance(context);
        holder.txtNota.setText(data.getText());

        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note d = dataList.get(holder.getAdapterPosition());
                int sID = d.getID();
                String sText = d.getText();

                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_update);
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setLayout(width,height);
                dialog.show();

                final EditText editText = (EditText) dialog.findViewById(R.id.edtEscribirNota);
                Button btUpdate = (Button) dialog.findViewById(R.id.btnActualizar);

                editText.setText(sText);

                btUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        String uText = editText.getText().toString().trim();
                        baseDeDatos.notaDao().update(sID, uText);
                        dataList.clear();
                        dataList.addAll(baseDeDatos.notaDao().getAll());
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

package com.sev.examen_final;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sev.examen_final.Entidad.Cliente;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorPersonalizadoClientes extends RecyclerView.Adapter<AdaptadorPersonalizadoClientes.MiViewHolderCliente> {
    private Context context;
    private List<Cliente> listaCliente = new ArrayList<>();

    public AdaptadorPersonalizadoClientes(Context context, List<Cliente> listaCliente){
        this.context = context;
        this.listaCliente = listaCliente;
    }

    @NonNull
    @Override
    public AdaptadorPersonalizadoClientes.MiViewHolderCliente onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vista = inflater.inflate(R.layout.lista_clientes,parent,false);
        return new MiViewHolderCliente(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPersonalizadoClientes.MiViewHolderCliente holder, @SuppressLint("RecyclerView") int position) {
        holder.txtListaRuc.setText(listaCliente.get(position).getRazon()+"");
        holder.txtListaRazon.setText(listaCliente.get(position).getRuc()+"");
        holder.txtListaNumero.setText(listaCliente.get(position).getTelefono()+"");
        holder.cv.setOnLongClickListener(v -> {
            Intent intent = new Intent(context,MainActivity.class);
            intent.putExtra("paramId", listaCliente.get(position).getId()+"");
            intent.putExtra("paramRuc", listaCliente.get(position).getRuc()+"");
            intent.putExtra("paramSocial", listaCliente.get(position).getRazon()+"");
            intent.putExtra("paramDireccion", listaCliente.get(position).getDireccion()+"");
            intent.putExtra("paramTelefono", listaCliente.get(position).getTelefono()+"");
            context.startActivity(intent);
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return listaCliente.size();
    }

    public class MiViewHolderCliente extends RecyclerView.ViewHolder{
        TextView txtListaRuc, txtListaRazon, txtListaNumero;
        CardView cv;
        public MiViewHolderCliente(@NonNull View itemView) {
            super(itemView);
            txtListaRuc = itemView.findViewById(R.id.txtListaRuc);
            txtListaRazon = itemView.findViewById(R.id.txtListaRazon);
            txtListaNumero = itemView.findViewById(R.id.txtListaNumero);
            cv = itemView.findViewById(R.id.cv);
        }
    }
}

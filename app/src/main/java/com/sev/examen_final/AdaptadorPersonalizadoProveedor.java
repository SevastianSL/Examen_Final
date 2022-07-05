package com.sev.examen_final;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sev.examen_final.Entidad.Proveedor;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorPersonalizadoProveedor extends RecyclerView.Adapter<AdaptadorPersonalizadoProveedor.MiViewHolderProveedor>{
    private Context context;
    private List<Proveedor> listaProveedor = new ArrayList<>();

    public AdaptadorPersonalizadoProveedor(Context context, List<Proveedor> listaProveedor){
        this.context = context;
        this.listaProveedor = listaProveedor;
    }

    @NonNull
    @Override
    public AdaptadorPersonalizadoProveedor.MiViewHolderProveedor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vista = inflater.inflate(R.layout.lista_proveedor,parent,false);
        return new MiViewHolderProveedor(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPersonalizadoProveedor.MiViewHolderProveedor holder, @SuppressLint("RecyclerView") int position) {
        holder.txtListaRuc_Proveedor.setText(listaProveedor.get(position).getRazon_proveedor()+"");
        holder.txtListaRazon_Proveedor.setText(listaProveedor.get(position).getRuc_proveedor()+"");
        holder.txtListaNumero_Proveedor.setText(listaProveedor.get(position).getTelefono_proveedor()+"");
        holder.pve.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(context,ProveedorActivity.class);
                intent.putExtra("paramId", listaProveedor.get(position).getId()+"");
                intent.putExtra("paramRuc", listaProveedor.get(position).getRuc_proveedor()+"");
                intent.putExtra("paramRazon", listaProveedor.get(position).getRazon_proveedor()+"");
                intent.putExtra("paramDireccion", listaProveedor.get(position).getDireccion_proveedor()+"");
                intent.putExtra("paramTelefono", listaProveedor.get(position).getTelefono_proveedor()+"");
                context.startActivity(intent);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaProveedor.size();
    }

    public class MiViewHolderProveedor extends RecyclerView.ViewHolder{
        TextView txtListaRuc_Proveedor, txtListaRazon_Proveedor, txtListaNumero_Proveedor;
        CardView pve;
        public MiViewHolderProveedor(@NonNull View itemView) {
            super(itemView);
            txtListaRuc_Proveedor = itemView.findViewById(R.id.txtListaRuc_Proveedor);
            txtListaRazon_Proveedor = itemView.findViewById(R.id.txtListaRazon_Proveedor);
            txtListaNumero_Proveedor = itemView.findViewById(R.id.txtListaNumero_Proveedor);
            pve = itemView.findViewById(R.id.pve);
        }
    }
}

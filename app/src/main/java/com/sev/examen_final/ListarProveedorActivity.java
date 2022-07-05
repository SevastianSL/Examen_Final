package com.sev.examen_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sev.examen_final.Entidad.Proveedor;

import java.util.ArrayList;
import java.util.List;

public class ListarProveedorActivity extends AppCompatActivity {
    RecyclerView ListaProveedor;
    FloatingActionButton btnNuevoProveedor;
    ImageButton btnAtras;

    FirebaseDatabase database;
    DatabaseReference reference;

    private List<Proveedor> listaProveedor = new ArrayList<>();
    AdaptadorPersonalizadoProveedor adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_proveedor);
        asignarReferencias();
        inicializarFirebase();
        cargarDatos();
    }

    private void cargarDatos(){
        reference.child("Proveedor").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaProveedor.clear();
                for(DataSnapshot item:snapshot.getChildren()){
                    Proveedor p = item.getValue(Proveedor.class);
                    listaProveedor.add(p);
                }
                adaptador = new AdaptadorPersonalizadoProveedor(ListarProveedorActivity.this,listaProveedor);
                ListaProveedor.setAdapter(adaptador);
                ListaProveedor.setLayoutManager(new LinearLayoutManager(ListarProveedorActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void asignarReferencias(){
        ListaProveedor = findViewById(R.id.ListaProveedores);
        btnNuevoProveedor = findViewById(R.id.btnNuevoProveedor);
        btnAtras = findViewById(R.id.btnAtras);
        btnNuevoProveedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarProveedorActivity.this,ProveedorActivity.class);
                startActivity(intent);
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarProveedorActivity.this,PaginaPrincipalActivity.class);
                startActivity(intent);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                String id = listaProveedor.get(pos).getId();
                listaProveedor.remove(pos);
                adaptador.notifyDataSetChanged();
                reference.child("Proveedor").child(id).removeValue();
            }
        }).attachToRecyclerView(ListaProveedor);

    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
    }
}
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
import com.sev.examen_final.Entidad.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ListarActivity extends AppCompatActivity {
    RecyclerView ListaCliente;
    FloatingActionButton btnNuevo;
    ImageButton btnAtras;

    FirebaseDatabase database;
    DatabaseReference reference;

    private List<Cliente> listaClientes = new ArrayList<>();
    AdaptadorPersonalizadoClientes adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        asignarReferencias();
        inicializarFirebase();
        cargarDatos();
    }
    private void cargarDatos(){
        reference.child("Cliente").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaClientes.clear();
                for(DataSnapshot item:snapshot.getChildren()){
                    Cliente c = item.getValue(Cliente.class);
                    listaClientes.add(c);
                }
                adaptador = new AdaptadorPersonalizadoClientes(ListarActivity.this,listaClientes);
                ListaCliente.setAdapter(adaptador);
                ListaCliente.setLayoutManager(new LinearLayoutManager(ListarActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void asignarReferencias(){
        ListaCliente = findViewById(R.id.ListaCliente);
        btnNuevo = findViewById(R.id.btnNuevo);
        btnAtras = findViewById(R.id.btnAtras);
        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarActivity.this,PaginaPrincipalActivity.class);
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
                String id = listaClientes.get(pos).getId();
                listaClientes.remove(pos);
                adaptador.notifyDataSetChanged();
                reference.child("Cliente").child(id).removeValue();
            }
        }).attachToRecyclerView(ListaCliente);

    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
    }
}
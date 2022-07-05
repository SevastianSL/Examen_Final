package com.sev.examen_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sev.examen_final.Entidad.Proveedor;
import com.sev.examen_final.Entidad.Reporte;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ReporteActivity extends AppCompatActivity {
    EditText EmisiontEdit, VencimientoEdit, TipoEdit, SerieEdit, NumDocEdit, BaseEdit, IGVEdit;
    TextInputLayout EmisionInput, VencimientoInput, TipoInput, SerieInput, NumDocInput, BaseInput, IGVInput;
    TextView ImporteTotal;
    Button btnRegistrarReporte;
    Reporte objReporte;
    FirebaseDatabase database;
    DatabaseReference reference;

    private List<Proveedor> listaProveedor = new ArrayList<>();
    AdaptadorPersonalizadoProveedor adaptador;

    boolean registra = true;
    String id;
    HashMap map = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);
        inicializarFirebase();
        cargarDatos();
        asignarReferencia();
        seleccionarProveedor();
    }

    public void asignarReferencia(){
        EmisiontEdit = findViewById(R.id.EmisiontEdit);
        EmisionInput = findViewById(R.id.EmisionInput);
        VencimientoEdit = findViewById(R.id.VencimientoEdit);
        VencimientoInput = findViewById(R.id.VencimientoInput);
        TipoEdit = findViewById(R.id.TipoEdit);
        TipoInput = findViewById(R.id.TipoInput);
        SerieEdit = findViewById(R.id.SerieEdit);
        SerieInput = findViewById(R.id.SerieInput);
        NumDocEdit = findViewById(R.id.NumDocEdit);
        NumDocInput = findViewById(R.id.NumDocInput);
        BaseEdit = findViewById(R.id.BaseEdit);
        BaseInput = findViewById(R.id.BaseInput);
        IGVEdit = findViewById(R.id.IGVEdit);
        IGVInput = findViewById(R.id.IGVInput);
        ImporteTotal = findViewById(R.id.ImporteTotal);
        btnRegistrarReporte = findViewById(R.id.btnRegistrarReporte);
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
                adaptador = new AdaptadorPersonalizadoProveedor(ReporteActivity.this,listaProveedor);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
    }

    public void seleccionarProveedor(){
        ArrayAdapter<Proveedor> adapter = new ArrayAdapter<>(this,R.layout.drop_down_item, listaProveedor);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.filled_exposed);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ReporteActivity.this, autoCompleteTextView.getText().toString(), Toast.LENGTH_SHORT);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
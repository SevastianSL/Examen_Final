package com.sev.examen_final;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sev.examen_final.Entidad.Cliente;
import com.sev.examen_final.Entidad.Proveedor;

import java.util.HashMap;
import java.util.UUID;

public class ProveedorActivity extends AppCompatActivity {
    EditText RucEdit_Proveedor, SocialEdit_Proveedor, DireccionEdit_Proveedor, TelefonoEdit_Proveedor;
    TextInputLayout RucInput_Proveedor, SocialInput_Proveedor, DireccionInput_Proveedor, TelefonoInput_Proveedor;
    Button btnRegistrar_Proveedor;
    Proveedor objProveedor;
    TextView texto_proveedor;
    ImageButton btnAtras;

    FirebaseDatabase database;
    DatabaseReference reference;

    boolean registra = true;
    String id;
    HashMap map = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proveedor);
        asignarReferencia();
        inicializarFirebase();
        obtenerValores();
    }

    public void obtenerValores(){
        if(getIntent().hasExtra("paramId")){
            registra = false;
            id = getIntent().getStringExtra("paramId");
            RucEdit_Proveedor.setText(getIntent().getStringExtra("paramRuc"));
            SocialEdit_Proveedor.setText(getIntent().getStringExtra("paramRazon"));
            DireccionEdit_Proveedor.setText(getIntent().getStringExtra("paramDireccion"));
            TelefonoEdit_Proveedor.setText(getIntent().getStringExtra("paramTelefono"));

        }
    }

    public void asignarReferencia(){
        texto_proveedor = findViewById(R.id.texto_proveedor);
        RucEdit_Proveedor = findViewById(R.id.RucEdit_Proveedor);
        RucInput_Proveedor = findViewById(R.id.RucInput_Proveedor);
        SocialEdit_Proveedor = findViewById(R.id.SocialEdit_Proveedor);
        SocialInput_Proveedor = findViewById(R.id.SocialInput_Proveedor);
        DireccionEdit_Proveedor = findViewById(R.id.DireccionEdit_Proveedor);
        DireccionInput_Proveedor = findViewById(R.id.DireccionInput_Proveedor);
        TelefonoEdit_Proveedor = findViewById(R.id.TelefonoEdit_Proveedor);
        TelefonoInput_Proveedor = findViewById(R.id.TelefonoInput_Proveedor);
        btnRegistrar_Proveedor = findViewById(R.id.btnRegistrar_Proveedor);
        btnAtras = findViewById(R.id.btnAtras);

        btnRegistrar_Proveedor.setOnClickListener(v -> {
            if(capturarDatos()){
                String mensaje = "";

                if(registra){
                    reference.child("Proveedor").child(objProveedor.getId()).setValue(objProveedor);
                    mensaje = "Proveedor registrado correctamente";
                }else{
                    reference.child("Proveedor").child(id).updateChildren(map);
                    mensaje = "Proveedor actualizado";
                }

                AlertDialog.Builder ventana = new AlertDialog.Builder(ProveedorActivity.this);
                ventana.setTitle("REGISTRO DE PROVEEDOR");
                ventana.setMessage(mensaje);
                ventana.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                ventana.create().show();
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProveedorActivity.this, ListarProveedorActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean capturarDatos(){
        boolean valida = true;
        String ruc_proveedor, razon_proveedor, direccion_proveedor, telefono_proveedor;
        ruc_proveedor = RucEdit_Proveedor.getText().toString();
        razon_proveedor = SocialEdit_Proveedor.getText().toString();
        direccion_proveedor = DireccionEdit_Proveedor.getText().toString();
        telefono_proveedor = TelefonoEdit_Proveedor.getText().toString();
        if(ruc_proveedor.isEmpty()){
            RucInput_Proveedor.setError("Ruc Requerido");
            valida = false;
        }else{
            RucInput_Proveedor.setErrorEnabled(false);
        }
        if(razon_proveedor.isEmpty()){
            SocialInput_Proveedor.setError("Razón Social Requerida");
            valida = false;
        }else{
            SocialInput_Proveedor.setErrorEnabled(false);
        }
        if(direccion_proveedor.isEmpty()){
            DireccionInput_Proveedor.setError("Dirección Requerida");
            valida = false;
        }else{
            DireccionInput_Proveedor.setErrorEnabled(false);
        }
        if(telefono_proveedor.isEmpty()){
            TelefonoInput_Proveedor.setError("Teléfono Requerido");
            valida = false;
        }else{
            TelefonoInput_Proveedor.setErrorEnabled(false);
        }
        if(valida){
            if(registra){
                objProveedor = new Proveedor(UUID.randomUUID().toString(),ruc_proveedor,razon_proveedor,direccion_proveedor,telefono_proveedor);
            }else{
                map.put("ruc_proveedor", ruc_proveedor);
                map.put("razon_proveedor", razon_proveedor);
                map.put("direccion_proveedor", direccion_proveedor);
                map.put("telefono_proveedor", telefono_proveedor);
            }
        }else{
            Toast.makeText(this, "Por favor rellenar los campos", Toast.LENGTH_SHORT).show();
        }
        return valida;
    }

    public void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
    }
}
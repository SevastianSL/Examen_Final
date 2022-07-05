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

import java.util.HashMap;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    EditText RucEdit, SocialEdit, DireccionEdit, TelefonoEdit;
    TextInputLayout RucInput, SocialInput, DireccionInput, TelefonoInput;
    Button btnRegistrar;
    Cliente objCliente;
    TextView texto_cliente;
    ImageButton btnAtras;

    FirebaseDatabase database;
    DatabaseReference reference;

    boolean registra = true;
    String id;
    HashMap map = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asignarReferencia();
        inicializarFirebase();
        obtenerValores();
    }

    public void obtenerValores(){
        if(getIntent().hasExtra("paramId")){
            registra = false;
            id = getIntent().getStringExtra("paramId");
            RucEdit.setText(getIntent().getStringExtra("paramRuc"));
            SocialEdit.setText(getIntent().getStringExtra("paramSocial"));
            DireccionEdit.setText(getIntent().getStringExtra("paramDireccion"));
            TelefonoEdit.setText(getIntent().getStringExtra("paramTelefono"));

        }
    }

    public void asignarReferencia(){
        texto_cliente = findViewById(R.id.texto_cliente);
        RucEdit = findViewById(R.id.RucEdit);
        RucInput = findViewById(R.id.RucInput);
        SocialEdit = findViewById(R.id.SocialEdit);
        SocialInput = findViewById(R.id.SocialInput);
        DireccionEdit = findViewById(R.id.DireccionEdit);
        DireccionInput = findViewById(R.id.DireccionInput);
        TelefonoEdit = findViewById(R.id.TelefonoEdit);
        TelefonoInput = findViewById(R.id.TelefonoInput);
        btnRegistrar =findViewById(R.id.btnRegistrar);
        btnAtras = findViewById(R.id.btnAtras);
        btnRegistrar.setOnClickListener(v -> {
            if(capturarDatos()){
                String mensaje = "";

                if(registra){
                    reference.child("Cliente").child(objCliente.getId()).setValue(objCliente);
                    mensaje = "Cliente registrado correctamente";
                }else{
                    reference.child("Cliente").child(id).updateChildren(map);
                    mensaje = "Cliente actualizado";
                }

                AlertDialog.Builder ventana = new AlertDialog.Builder(MainActivity.this);
                ventana.setTitle("REGISTRO DE CLIENTE");
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
                Intent intent = new Intent(MainActivity.this, ListarActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean capturarDatos(){
        boolean valida = true;
        String ruc, social, direccion, telefono;
        ruc = RucEdit.getText().toString();
        social = SocialEdit.getText().toString();
        direccion = DireccionEdit.getText().toString();
        telefono = TelefonoEdit.getText().toString();
        if(ruc.isEmpty()){
            RucInput.setError("Ruc Requerido");
            valida = false;
        }else{
            RucInput.setErrorEnabled(false);
        }
        if(social.isEmpty()){
            SocialInput.setError("Razón Social Requerida");
            valida = false;
        }else{
            SocialInput.setErrorEnabled(false);
        }
        if(direccion.isEmpty()){
            DireccionInput.setError("Dirección Requerida");
            valida = false;
        }else{
            DireccionInput.setErrorEnabled(false);
        }
        if(telefono.isEmpty()){
            TelefonoInput.setError("Teléfono Requerido");
            valida = false;
        }else{
            TelefonoInput.setErrorEnabled(false);
        }
        if(valida){
            if(registra){
                objCliente = new Cliente(UUID.randomUUID().toString(),ruc,social,direccion,telefono);
            }else{
                map.put("ruc", ruc);
                map.put("razon", social);
                map.put("direccion", direccion);
                map.put("telefono", telefono);
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
package com.sev.examen_final;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class PaginaPrincipalActivity extends AppCompatActivity {
    ImageButton btnCliente, btnProveedor, btnReportes, btnMapa;
    TextView name, mail;
    ImageView logout;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    boolean valida_logout =true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_principal);
        name = findViewById(R.id.name);
        mail = findViewById(R.id.mail);
        logout = findViewById(R.id.logout);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        gsc = GoogleSignIn.getClient(this,gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account!=null){
            String Name = account.getDisplayName();
            String Mail = account.getEmail();

            name.setText(Name);
            mail.setText(Mail);
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(valida_logout){
                    String mensaje = "¿Desea salir de la aplicación? ...";
                    AlertDialog.Builder ventana = new AlertDialog.Builder(PaginaPrincipalActivity.this);
                    ventana.setTitle("CERRAR SESIÓN");
                    ventana.setMessage(mensaje);
                    ventana.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            SignOut();
                            finish();
                        }
                    });
                    ventana.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    ventana.create().show();
                }
            }
        });

        asignarReferencia();
    }

    private boolean SignOut() {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                finish();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });
        return valida_logout;
    }

    public void asignarReferencia(){
        btnCliente = findViewById(R.id.btnCliente);
        btnProveedor = findViewById(R.id.btnProveedor);
        btnReportes = findViewById(R.id.btnReportes);
        btnMapa = findViewById(R.id.btnMapa);
        btnCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaginaPrincipalActivity.this, ListarActivity.class);
                startActivity(intent);
            }
        });

        btnProveedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaginaPrincipalActivity.this, ListarProveedorActivity.class);
                startActivity(intent);
            }
        });

        btnMapa.setOnClickListener(v -> {
            Intent intent = new Intent(PaginaPrincipalActivity.this, MapActivity.class);
            startActivity(intent);
        });

        btnReportes.setOnClickListener(v -> {
            Intent intent = new Intent(PaginaPrincipalActivity.this, ReporteActivity.class);
            startActivity(intent);
        });
    }


}
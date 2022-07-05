package com.sev.examen_final.Entidad;

public class Proveedor {
    String id;
    String ruc_proveedor,razon_proveedor,direccion_proveedor,telefono_proveedor;

    public Proveedor() {
    }

    public Proveedor(String id, String ruc_proveedor, String razon_proveedor, String direccion_proveedor, String telefono_proveedor) {
        this.id = id;
        this.ruc_proveedor = ruc_proveedor;
        this.razon_proveedor = razon_proveedor;
        this.direccion_proveedor = direccion_proveedor;
        this.telefono_proveedor = telefono_proveedor;
    }

    @Override
    public String toString() {
        return razon_proveedor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRuc_proveedor() {
        return ruc_proveedor;
    }

    public void setRuc_proveedor(String ruc_proveedor) {
        this.ruc_proveedor = ruc_proveedor;
    }

    public String getRazon_proveedor() {
        return razon_proveedor;
    }

    public void setRazon_proveedor(String razon_proveedor) {
        this.razon_proveedor = razon_proveedor;
    }

    public String getDireccion_proveedor() {
        return direccion_proveedor;
    }

    public void setDireccion_proveedor(String direccion_proveedor) {
        this.direccion_proveedor = direccion_proveedor;
    }

    public String getTelefono_proveedor() {
        return telefono_proveedor;
    }

    public void setTelefono_proveedor(String telefono_proveedor) {
        this.telefono_proveedor = telefono_proveedor;
    }
}

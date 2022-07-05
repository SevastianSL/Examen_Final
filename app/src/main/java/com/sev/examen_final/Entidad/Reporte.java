package com.sev.examen_final.Entidad;

import java.text.DateFormat;

public class Reporte{
    String id, tipo, ser, num_compro_pago, proveedor, fecha_emision, fecha_VMTO;
    Double base_imponible, igv, impor_total;

    public Reporte() {
    }

    public Reporte(String id, String tipo, String ser, String num_compro_pago, String proveedor, String fecha_emision, String fecha_VMTO, Double base_imponible, Double igv, Double impor_total) {
        this.id = id;
        this.tipo = tipo;
        this.ser = ser;
        this.num_compro_pago = num_compro_pago;
        this.proveedor = proveedor;
        this.fecha_emision = fecha_emision;
        this.fecha_VMTO = fecha_VMTO;
        this.base_imponible = base_imponible;
        this.igv = igv;
        this.impor_total = impor_total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSer() {
        return ser;
    }

    public void setSer(String ser) {
        this.ser = ser;
    }

    public String getNum_compro_pago() {
        return num_compro_pago;
    }

    public void setNum_compro_pago(String num_compro_pago) {
        this.num_compro_pago = num_compro_pago;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(String fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public String getFecha_VMTO() {
        return fecha_VMTO;
    }

    public void setFecha_VMTO(String fecha_VMTO) {
        this.fecha_VMTO = fecha_VMTO;
    }

    public Double getBase_imponible() {
        return base_imponible;
    }

    public void setBase_imponible(Double base_imponible) {
        this.base_imponible = base_imponible;
    }

    public Double getIgv() {
        return igv;
    }

    public void setIgv(Double igv) {
        this.igv = igv;
    }

    public Double getImpor_total() {
        return impor_total;
    }

    public void setImpor_total(Double impor_total) {
        this.impor_total = impor_total;
    }
}
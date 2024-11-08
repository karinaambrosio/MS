package com.ka.ms.factura;
public class Factura {
    private int id;
    private String descripcion;
    private double monto;
    private int clienteId;
//entity
    public Factura() {}
    public Factura(int id, String descripcion, double monto, int clienteId) {
        this.id = id;
        this.descripcion = descripcion;
        this.monto = monto;
        this.clienteId = clienteId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }
}

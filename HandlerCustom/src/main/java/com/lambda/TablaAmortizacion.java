package com.lambda;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.List;

@DynamoDBTable(tableName = "AmortizacionBIM")
public class TablaAmortizacion {

    @DynamoDBHashKey
    private int id;

    @DynamoDBAttribute
    private int idCliente;

    @DynamoDBAttribute
    private int idCredito;

    @DynamoDBTypeConvertedJson
    private List<Amortizacion> amortizacionList;

    public TablaAmortizacion() {}
    public TablaAmortizacion(int id, int idCliente, int idCredito, List<Amortizacion> amortizacionList) {
        this.id = id;
        this.idCliente = idCliente;
        this.idCredito = idCredito;
        this.amortizacionList = amortizacionList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdCredito() {
        return idCredito;
    }

    public void setIdCredito(int idCredito) {
        this.idCredito = idCredito;
    }

    public List<Amortizacion> getAmortizacionList() {
        return amortizacionList;
    }

    public void setAmortizacionList(List<Amortizacion> amortizacionList) {
        this.amortizacionList = amortizacionList;
    }
}

package com.lambda;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "CreditBIM")
public class Credito {
    @DynamoDBHashKey
    private int id;

    @DynamoDBAttribute
    private int idCliente;

    @DynamoDBAttribute
    private Double tasaAnual;

    @DynamoDBAttribute
    private Double montoCredito;

    @DynamoDBAttribute
    private int plazo;

    public Credito(){}
    public Credito(int id, int idCliente, Double tasaAnual, Double montoCredito, int plazo) {
        this.id = id;
        this.idCliente = idCliente;
        this.tasaAnual = tasaAnual;
        this.montoCredito = montoCredito;
        this.plazo = plazo;
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

    public Double getTasaAnual() {
        return tasaAnual;
    }

    public void setTasaAnual(Double tasaAnual) {
        this.tasaAnual = tasaAnual;
    }

    public Double getMontoCredito() {
        return montoCredito;
    }

    public void setMontoCredito(Double montoCredito) {
        this.montoCredito = montoCredito;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }
}

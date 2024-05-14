package com.lambda;

public class Amortizacion {

    private int idPeriodo;
    private Double saldoInicial;
    private Double capital;
    private Double interes;
    private Double pagoMensual;
    private Double saldoFinal;

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public Double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(Double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public Double getCapital() {
        return capital;
    }

    public void setCapital(Double capital) {
        this.capital = capital;
    }

    public Double getInteres() {
        return interes;
    }

    public void setInteres(Double interes) {
        this.interes = interes;
    }

    public Double getPagoMensual() {
        return pagoMensual;
    }

    public void setPagoMensual(Double pagoMensual) {
        this.pagoMensual = pagoMensual;
    }

    public Double getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(Double saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    public Double calcularTasaMensual(Double tasaAnual){
        return tasaAnual/12;
    }

    public  Double calcularPagoMensual(Double montoCredito, Double tasaMensual, int plazo){
        return (montoCredito*tasaMensual)/(1-(Math.pow((1+tasaMensual),-plazo)));
    }

    //Interés del Periodo=Saldo Insoluto del Periodo Anterior x Tasa Mensual
    public Double calcularInteresDelPeriodo(Double saldoDelPeriodo, Double tasaMensual){
        return saldoDelPeriodo*tasaMensual;
    }

    //Capital Amortizado del Periodo=Saldo Insoluto del Periodo Anterior-Interés del Periodo**
    public Double calcularCapitalAmortizado(Double saldoDelPeriodo, Double interesPeriodo){
        return saldoDelPeriodo - interesPeriodo;
    }

    //Saldo Insoluto=Saldo Insoluto del Periodo Anterior - Capital Amortizado en el Periodo**
    public Double calcularSaldoInsoluto(Double saldoDelPeriodo, Double capitalAmortizado){
        return saldoDelPeriodo - capitalAmortizado;
    }
}

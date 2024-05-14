package com.lambda;

public class Request {

    private int id;
    private String httpMethod;
    private Customer customer;
    private Credito credito;

    public Request(){}

    public Request(int id, String httpMethod, Customer customer) {
        this.id = id;
        this.httpMethod = httpMethod;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Credito getCredito() {
        return credito;
    }

    public void setCredito(Credito credito) {
        this.credito = credito;
    }
}

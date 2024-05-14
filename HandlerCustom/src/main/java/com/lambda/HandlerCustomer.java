package com.lambda;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class HandlerCustomer implements RequestHandler<Request, Object>{

    @Override
    public Object handleRequest(Request event, Context context)
    {
        LambdaLogger logger = context.getLogger();
        logger.log("String found: ");
        Customer customer= null;
        Credito credito = null;

        AmazonDynamoDB db = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDBMapper mapper = new DynamoDBMapper(db);

        switch (event.getHttpMethod()){
            case "GET":
                if (event.getId() == 0){
                    List<Customer> customers = new ArrayList<>();
                    customers = mapper.scan(Customer.class, new DynamoDBScanExpression());
                    return customers;
                }else{
                    customer = mapper.load(Customer.class, event.getId());
                    return customer;
                }
            case "PUT":
                customer = event.getCustomer();
                mapper.save(customer);
                return customer;
            case "POST":
                customer = event.getCustomer();
                credito = event.getCredito();
                credito.setIdCliente(customer.getId());
                mapper.save(credito);

                Amortizacion amortizacion =  new Amortizacion();
                List<Amortizacion> amortizacionList = new ArrayList<>();

                System.out.println("Calcular Tasa Mensual");
                Double tasaMensual = amortizacion.calcularTasaMensual(credito.getTasaAnual());
                System.out.println("Calcular Pago Mensual");
                Double pagoMensual = amortizacion.calcularPagoMensual(credito.getMontoCredito(), tasaMensual,  credito.getPlazo());

                for(int i = 1; i <= credito.getPlazo(); i++){
                    Amortizacion amortizacionItem = new Amortizacion();
                    amortizacionItem.setIdPeriodo(i);
                    amortizacionItem.setPagoMensual(pagoMensual);

                    if (i == 1){
                        amortizacionItem.setSaldoInicial(credito.getMontoCredito());
                   }else{
                        amortizacionItem.setSaldoInicial(amortizacionList.get(i-2).getSaldoFinal());
                    }
                    System.out.println("Calcular Interes del Periodo");
                    amortizacionItem.setInteres(amortizacion.calcularInteresDelPeriodo(amortizacionItem.getSaldoInicial(), tasaMensual));
                    System.out.println("Calcular Capital Amortizado del Periodo");
                    amortizacionItem.setCapital(amortizacion.calcularCapitalAmortizado(amortizacionItem.getSaldoInicial(), amortizacionItem.getInteres()));
                    //Para el primer periodo, el Saldo Insoluto del Periodo Anterior es el Importe del Crédito
                    System.out.println("Calcular Saldo Insoluto");
                    amortizacionItem.setSaldoFinal(amortizacion.calcularSaldoInsoluto(amortizacionItem.getSaldoInicial(), amortizacionItem.getCapital()));

                    amortizacionList.add(amortizacionItem);
                    System.out.println("Calculado periodo " + i);
                }

                TablaAmortizacion tablaAmortizacion = new TablaAmortizacion(1+customer.getId(), customer.getId(), credito.getId(), amortizacionList);
                mapper.save(tablaAmortizacion);
                return amortizacionList;
        }

        System.out.println("Done!");

        return "Hello " + event.getCustomer().getNombre();
    }
}
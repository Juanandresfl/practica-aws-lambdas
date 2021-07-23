package com.pragma.aws.lambdas;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.pragma.aws.Cliente;

import java.util.ArrayList;
import java.util.List;

public class HandlerListar implements RequestHandler<Cliente,Object> {
    @Override
    public Object handleRequest(Cliente cliente, Context context) {
        AmazonDynamoDB db = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDBMapper mapper = new DynamoDBMapper(db);
        if(cliente.getIdentificacion() != null){
            Cliente c = mapper.load(Cliente.class, cliente.getIdentificacion());
            return c;
        }
        List<Cliente> clientes = new ArrayList<>();
        clientes = mapper.scan(Cliente.class, new DynamoDBScanExpression());
        return clientes;
    }
}

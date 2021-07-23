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

public class HandlerEliminar implements RequestHandler<Cliente,Object> {
    @Override
    public Object handleRequest(Cliente cliente, Context context) {
        AmazonDynamoDB db = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDBMapper mapper = new DynamoDBMapper(db);
        Cliente c = mapper.load(Cliente.class, cliente.getIdentificacion());
        if(c != null){
            mapper.delete(c);
        }
        return c;
    }
}

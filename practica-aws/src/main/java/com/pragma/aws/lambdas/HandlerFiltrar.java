package com.pragma.aws.lambdas;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.pragma.aws.Cliente;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandlerFiltrar implements RequestHandler<Cliente,Object> {
    @Override
    public Object handleRequest(Cliente cliente, Context context) {
        AmazonDynamoDB db = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDBMapper mapper = new DynamoDBMapper(db);

        if(cliente.getEdad() == null){
            return null;
        }
        Map<String, AttributeValue> valueMap = new HashMap<String, AttributeValue>();
        valueMap.put(":age", new AttributeValue().withN(String.valueOf(cliente.getEdad())));
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("edad >= :age").withExpressionAttributeValues(valueMap);
        List<Cliente> clientes = mapper.scan(Cliente.class, scanExpression);
        return clientes;
    }
}

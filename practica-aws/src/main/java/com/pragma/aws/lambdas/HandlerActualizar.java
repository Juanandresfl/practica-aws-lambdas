package com.pragma.aws.lambdas;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.pragma.aws.Cliente;

public class HandlerActualizar implements RequestHandler<Cliente,Object> {
    @Override
    public Object handleRequest(Cliente cliente, Context context) {
        AmazonDynamoDB db = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDBMapper mapper = new DynamoDBMapper(db);
        Cliente c = mapper.load(Cliente.class,cliente.getIdentificacion());
        if(c != null){
            c.setNombre(cliente.getNombre());
            c.setApellido(cliente.getApellido());
            c.setEdad(cliente.getEdad());
            c.setCiudad(cliente.getCiudad());
            c.setTipoIdentificacion(cliente.getTipoIdentificacion());
            mapper.save(c);
           return c;
        }

        return "El cliente no existe";
    }
}

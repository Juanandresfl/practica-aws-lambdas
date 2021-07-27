package com.pragma.aws.lambdas;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.pragma.aws.Cliente;

public class HandlerRegistrar implements RequestHandler<Cliente,Object> {
    @Override
    public Object handleRequest(Cliente cliente, Context context) {
        AmazonDynamoDB db = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDBMapper mapper = new DynamoDBMapper(db);

        if(cliente.getIdentificacion().isBlank() || cliente.getNombre().isBlank() || cliente.getApellido().isBlank()
                || cliente.getEdad() == null || cliente.getCiudad().isBlank() || cliente.getTipoIdentificacion().getNombre().isBlank()
                || cliente.getTipoIdentificacion().getDescripcion().isBlank()){
            return "Asegurese de llenar completamente los datos";
        }
        Cliente client = mapper.load(Cliente.class,cliente.getIdentificacion());
        if(client != null){
            return "El cliente ya existe";
        }

        mapper.save(cliente);
        return cliente;
    }
}

package com.pragma.aws;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@DynamoDBTable(tableName = "cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {
    @DynamoDBHashKey
    private String identificacion;

    @DynamoDBAttribute
    private TipoIdentificacion tipoIdentificacion;

    @DynamoDBAttribute
    private String nombre;

    @DynamoDBAttribute
    private String apellido;

    @DynamoDBAttribute
    private Integer edad;

    @DynamoDBAttribute
    private String ciudad;

}


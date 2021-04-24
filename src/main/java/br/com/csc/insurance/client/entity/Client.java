package br.com.csc.insurance.client.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "client")
@Data
@Builder
public class Client {

    @Id
    private String id;
    private String name;
    private String cpf;
    private String city;
    @Field("federation_unity")
    private String federationUnity;
}

package br.com.fiap.mongoteste.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Parkingmeter {

    @Id
    private String code;
    private String street;
    private Integer number;
    private String zipCode;
    private String city;
    private String uf;

}

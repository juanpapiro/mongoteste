package br.com.fiap.mongoteste.dto;

import br.com.fiap.mongoteste.model.Parkingmeter;
import br.com.fiap.mongoteste.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderParkingRequest {

    private String carPlate;
    private Integer parkingTime;
    private String payment;
    private Parkingmeter parkingmeter;

}

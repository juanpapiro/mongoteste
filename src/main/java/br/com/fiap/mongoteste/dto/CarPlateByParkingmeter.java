package br.com.fiap.mongoteste.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarPlateByParkingmeter {

    private String carPlate;
    private LocalDateTime dateFinal;
    private Integer parkingTime;

}

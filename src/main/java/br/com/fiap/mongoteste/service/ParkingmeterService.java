package br.com.fiap.mongoteste.service;

import br.com.fiap.mongoteste.model.Parkingmeter;
import org.springframework.http.ResponseEntity;

public interface ParkingmeterService {

    ResponseEntity<Parkingmeter> createParkingmeter(Parkingmeter parkingmeter);

}

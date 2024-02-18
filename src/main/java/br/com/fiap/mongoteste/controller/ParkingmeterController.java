package br.com.fiap.mongoteste.controller;

import br.com.fiap.mongoteste.model.Parkingmeter;
import br.com.fiap.mongoteste.service.ParkingmeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parkingmeter")
public class ParkingmeterController {

    @Autowired
    private ParkingmeterService parkingmeterService;

    @PostMapping
    public ResponseEntity<Parkingmeter> createParkimeter(
            @RequestBody Parkingmeter parkingmeter) {
        return parkingmeterService.createParkingmeter(parkingmeter);
    }

}

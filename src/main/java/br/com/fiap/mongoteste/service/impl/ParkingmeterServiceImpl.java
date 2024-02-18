package br.com.fiap.mongoteste.service.impl;

import br.com.fiap.mongoteste.model.Parkingmeter;
import br.com.fiap.mongoteste.repository.ParkingmeterRepository;
import br.com.fiap.mongoteste.service.ParkingmeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ParkingmeterServiceImpl implements ParkingmeterService {

    @Autowired
    private ParkingmeterRepository parkingmeterRepository;

    @Override
    public ResponseEntity<Parkingmeter> createParkingmeter(Parkingmeter parkingmeter) {
        return ResponseEntity.ok(parkingmeterRepository.save(parkingmeter));
    }
}

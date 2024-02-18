package br.com.fiap.mongoteste.repository;

import br.com.fiap.mongoteste.model.Parkingmeter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParkingmeterRepository extends MongoRepository<Parkingmeter, String> {
}

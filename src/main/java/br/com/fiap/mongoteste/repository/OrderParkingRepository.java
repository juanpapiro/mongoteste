package br.com.fiap.mongoteste.repository;

import br.com.fiap.mongoteste.model.OrderParking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderParkingRepository extends MongoRepository<OrderParking, String> {
}

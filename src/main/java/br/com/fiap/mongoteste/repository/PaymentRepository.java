package br.com.fiap.mongoteste.repository;

import br.com.fiap.mongoteste.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment, String> {
}

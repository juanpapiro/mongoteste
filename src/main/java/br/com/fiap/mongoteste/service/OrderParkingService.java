package br.com.fiap.mongoteste.service;

import br.com.fiap.mongoteste.dto.CarPlateByParkingmeter;
import br.com.fiap.mongoteste.dto.OrderParkingRequest;
import br.com.fiap.mongoteste.model.OrderParking;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderParkingService {

    ResponseEntity<OrderParking> createOrder(OrderParkingRequest request);
    ResponseEntity<OrderParking> updateAddTime(String id, OrderParkingRequest request);
    ResponseEntity<List<OrderParking>> findOrders();
    ResponseEntity<List<CarPlateByParkingmeter>> findOrdersByDateFinalAndParkingmeterId(LocalDateTime dateFinal, String id);
    ResponseEntity<List<CarPlateByParkingmeter>> findOrdersByCarPlate(LocalDateTime dateFinal,String carPlate);
}

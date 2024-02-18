package br.com.fiap.mongoteste.controller;

import br.com.fiap.mongoteste.dto.CarPlateByParkingmeter;
import br.com.fiap.mongoteste.dto.OrderParkingRequest;
import br.com.fiap.mongoteste.model.OrderParking;
import br.com.fiap.mongoteste.service.OrderParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/parking")
public class OrderParkingController {

    @Autowired
    private OrderParkingService orderParkingService;

    @GetMapping
    public ResponseEntity<List<OrderParking>> findCustomers() {
        return orderParkingService.findOrders();
    }

    @PostMapping("/order")
    public ResponseEntity<OrderParking> createOrderParking(
            @RequestBody OrderParkingRequest request) {
        return orderParkingService.createOrder(request);
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<OrderParking> updateAddTime(
            @PathVariable(name = "id") String id,
            @RequestBody OrderParkingRequest request) {
        return orderParkingService.updateAddTime(id, request);
    }

    @GetMapping("/order/{parkingmeterId}")
    public ResponseEntity<List<CarPlateByParkingmeter>> findOrdersByDateFinalAndParkingmeterId(
            @PathVariable(name = "parkingmeterId") String id,
            @RequestParam(name = "dateFinal") LocalDateTime dateFinal){
        return orderParkingService.findOrdersByDateFinalAndParkingmeterId(dateFinal, id);
    }

    @GetMapping("/order/active/{carPlate}")
    public ResponseEntity<List<CarPlateByParkingmeter>> findOrdersByCarPlate(
            @PathVariable(name = "carPlate") String carPlate,
            @RequestParam(name = "dateFinal") LocalDateTime dateFinal) {
        return orderParkingService.findOrdersByCarPlate(dateFinal, carPlate);
    }

}

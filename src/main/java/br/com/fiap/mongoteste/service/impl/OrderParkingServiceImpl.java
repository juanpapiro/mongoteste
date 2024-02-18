package br.com.fiap.mongoteste.service.impl;

import br.com.fiap.mongoteste.dto.CarPlateByParkingmeter;
import br.com.fiap.mongoteste.dto.OrderParkingRequest;
import br.com.fiap.mongoteste.enums.PaymentType;
import br.com.fiap.mongoteste.model.OrderParking;
import br.com.fiap.mongoteste.model.Parkingmeter;
import br.com.fiap.mongoteste.model.Payment;
import br.com.fiap.mongoteste.repository.OrderParkingRepository;
import br.com.fiap.mongoteste.service.OrderParkingService;
import br.com.fiap.mongoteste.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderParkingServiceImpl implements OrderParkingService {

    private MongoTemplate mongoTemplate;

    public OrderParkingServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Autowired
    private OrderParkingRepository orderParkingRepository;

    @Autowired
    private PaymentService paymentService;


    @Override
    public ResponseEntity<OrderParking> createOrder(OrderParkingRequest request) {
        try {
            Payment payment = paymentService.createPayment(request.getParkingTime(),
                    PaymentType.findPaymentType(request.getPayment()));
            OrderParking orderParking = new OrderParking(request, payment);
            orderParking = orderParkingRepository.save(orderParking);
            return ResponseEntity.ok(orderParking);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Transactional
    @Override
    public ResponseEntity<OrderParking> updateAddTime(String id, OrderParkingRequest request) {
        try {
            Optional<OrderParking> order = orderParkingRepository.findById(id);
            if (order.isPresent()) {
                Payment payment = paymentService.createPayment(request.getParkingTime(),
                        PaymentType.findPaymentType(request.getPayment()));
                OrderParking orderLocate = order.get();
                orderLocate.setParkingTime(orderLocate.getParkingTime() + request.getParkingTime());
                orderLocate.setDateFinal(orderLocate.getDateStart().plusMinutes(orderLocate.getParkingTime()));
                orderLocate.getPayment().add(payment);
                return ResponseEntity.ok(orderParkingRepository.save(orderLocate));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<List<OrderParking>> findOrders() {
        try{
            return ResponseEntity.ok(orderParkingRepository.findAll());
        } catch(Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<List<CarPlateByParkingmeter>> findOrdersByDateFinalAndParkingmeterId(
            LocalDateTime dateFinal, String id) {
        Criteria criteria = Criteria.where("dateFinal")
                .gte(dateFinal)
                .and("parkingmeter.code").is(id);
        Query query = new Query(criteria);

        TypedAggregation<OrderParking> aggregation = Aggregation.newAggregation(
                OrderParking.class,
                Aggregation.match(criteria),
                Aggregation.sort(Sort.by("dateFinal").ascending()),
                Aggregation.project("carPlate")
                        .and("parkingTime").as("parkingTime")
                        .and("parkingmeter._id").as("parkingmeter._id")
                        .and("dateFinal").as("dateFinal"));
        try {
            List<CarPlateByParkingmeter> result = mongoTemplate.aggregate(
                    aggregation, CarPlateByParkingmeter.class).getMappedResults();
            return ResponseEntity.ok(result);
            /*return ResponseEntity.ok(mongoTemplate.find(query, OrderParking.class));*/
        } catch(Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<List<CarPlateByParkingmeter>> findOrdersByCarPlate(
            LocalDateTime dateFinal, String carPlate) {
        Criteria criteria = Criteria
                .where("carPlate").is(carPlate)
                .and("dateFinal").gte(dateFinal);
        TypedAggregation<OrderParking> aggregate = Aggregation.newAggregation(
                OrderParking.class,
                Aggregation.match(criteria),
                Aggregation.sort(Sort.by("dateFinal").descending())
        );
        try {
            List<CarPlateByParkingmeter> result = mongoTemplate.aggregate(
                    aggregate, CarPlateByParkingmeter.class).getMappedResults();
            return result.isEmpty() ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(result);
        } catch(Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


}

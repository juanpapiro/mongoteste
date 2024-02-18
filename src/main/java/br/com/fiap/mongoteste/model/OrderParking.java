package br.com.fiap.mongoteste.model;

import br.com.fiap.mongoteste.dto.OrderParkingRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class OrderParking {

    @Id
    private String code;
    private String carPlate;
    private Integer parkingTime;
    private LocalDateTime dateStart;
    private LocalDateTime dateFinal;
    @DBRef
    private List<Payment> payment;
    @DBRef
    private Parkingmeter parkingmeter;
    @Version
    private Long version;

    public OrderParking(OrderParkingRequest request, Payment payment) {
        this.carPlate = request.getCarPlate();
        this.parkingTime = request.getParkingTime();
        this.dateStart = LocalDateTime.now();
        this.dateFinal = dateStart.plusMinutes(request.getParkingTime());
        this.payment = Arrays.asList(payment);
        this.parkingmeter = request.getParkingmeter();
    }

}

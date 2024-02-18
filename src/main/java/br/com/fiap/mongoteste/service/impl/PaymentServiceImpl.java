package br.com.fiap.mongoteste.service.impl;

import br.com.fiap.mongoteste.enums.PaymentType;
import br.com.fiap.mongoteste.model.Payment;
import br.com.fiap.mongoteste.repository.PaymentRepository;
import br.com.fiap.mongoteste.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    private static final BigDecimal valueByMinute = BigDecimal.valueOf(0.5);

    public Payment createPayment(Integer time, PaymentType paymentType) {
        Payment payment = new Payment();
        payment.setPaymentType(paymentType.name());
        payment.setTime(time);
        payment.setAmount(valueByMinute.multiply(BigDecimal.valueOf(time)));
        return paymentRepository.save(payment);
    }

}

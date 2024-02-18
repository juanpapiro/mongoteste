package br.com.fiap.mongoteste.service;

import br.com.fiap.mongoteste.enums.PaymentType;
import br.com.fiap.mongoteste.model.Payment;

public interface PaymentService {

    Payment createPayment(Integer time, PaymentType paymentType);

}

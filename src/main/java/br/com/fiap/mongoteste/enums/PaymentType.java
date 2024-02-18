package br.com.fiap.mongoteste.enums;

import java.util.stream.Stream;

public enum PaymentType {
    CREDIT,
    DEBIT,
    PIX;

    public static PaymentType findPaymentType(String type) {
        return Stream.of(PaymentType.values())
                .filter(p -> p.name().equals(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tipo de pagamento inválido"));
    }
}

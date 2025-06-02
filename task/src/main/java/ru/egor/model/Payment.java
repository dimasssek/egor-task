package ru.egor.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Payment {

    private UUID id;

    private String payer;

    private Double amount;

    private LocalDateTime paymentDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Payment(UUID id, String payer, Double amount, LocalDateTime paymentDate) {
        this.id = id;
        this.payer = payer;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public Payment() {
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", payer='" + payer + '\'' +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                '}';
    }
}

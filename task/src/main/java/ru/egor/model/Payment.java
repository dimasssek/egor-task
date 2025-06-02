package ru.egor.model;

import java.time.LocalDate;

public class Payment {

    private Integer id;

    private String payer;

    private Double amount;

    private LocalDate paymentDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Payment(Integer id, String payer, Double amount, LocalDate paymentDate) {
        this.id = id;
        this.payer = payer;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }
}

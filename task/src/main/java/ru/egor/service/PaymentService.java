package ru.egor.service;

import ru.egor.model.Payment;
import ru.egor.repository.PaymentsDatabaseRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class PaymentService {

    /**
     * Репозиторий.
     */
    private final PaymentsDatabaseRepository repository;

    public PaymentService(PaymentsDatabaseRepository repository) {
        this.repository = repository;
    }

    public List<Payment> findAll() {
        try {
            return repository.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Payment payment) {
        try {
            repository.create(payment);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(UUID id) {
        try {
            repository.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAll() {
        try {
            repository.deleteAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

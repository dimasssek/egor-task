package ru.egor.repository;

import ru.egor.model.Payment;

/**
 * Определяет поведения репозитория для БД {@link Payment}.
 */
public interface PaymentsDatabaseRepository extends DatabaseRepository<Payment> {
    /**
     * Название таблицы.
     */
    String TABLE_NAME = "payments";
}
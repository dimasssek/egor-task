package ru.egor.repository;

import java.sql.SQLException;
import java.util.List;

/**
 * Необходимо реализовать операции чтения и записи.
 * @param <T>
 */
public interface DatabaseRepository<T> {

    /**
     * Возвращает все объекты из базы.
     * @return список объектов
     */
    List<T> findAll() throws SQLException;

    /**
     * Добавляет в базу новый объект.
     */
    void create(T object) throws SQLException;

    /**
     * Очищает базу данных.
     */
    void delete() throws SQLException;

    /**
     * Очищает базу данных.
     */
    void deleteAll() throws SQLException;
}
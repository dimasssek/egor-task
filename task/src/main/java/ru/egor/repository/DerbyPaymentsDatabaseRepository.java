package ru.egor.repository;

import ru.egor.model.Payment;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class DerbyPaymentsDatabaseRepository implements PaymentsDatabaseRepository{

    /**
     * Источник данных.
     */
    private final DataSource dataSource;

    /**
     * Создаёт {@link DerbyPaymentsDatabaseRepository} и инициализирует таблицу.
     * @param dataSource источник данных
     */
    public DerbyPaymentsDatabaseRepository(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        initTable();
    }

    @Override
    public List<Payment> findAll() throws SQLException {
        return List.of();
    }

    @Override
    public void create(Payment object) throws SQLException {

    }

    @Override
    public void delete() throws SQLException {

    }

    @Override
    public void deleteAll() throws SQLException {

    }

    /**
     * Создание и инициализация таблицы.
     */
    private void initTable() throws SQLException {
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement();
             var resultSet = connection.getMetaData().getTables(
                     null,
                     null,
                     PaymentsDatabaseRepository.TABLE_NAME.toUpperCase(),
                     new String[]{"TABLE"})) {
            if (resultSet.next()) {
            } else {
                statement.executeUpdate(
                        "CREATE TABLE "
                                + PaymentsDatabaseRepository.TABLE_NAME
                                + " ("
                                + "id INT PRIMARY KEY, "
                                + "payer VARCHAR NOT NULL, "
                                + "amount DOUBLE, "
                                + "paymentDate TIMESTAMP NOT NULL"
                                + ")");
            }
        } catch (SQLException exception) {
            throw exception;
        }
    }

}

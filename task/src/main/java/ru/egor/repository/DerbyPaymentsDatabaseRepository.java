package ru.egor.repository;

import ru.egor.model.Payment;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        var sqlQuery = String.format("SELECT * FROM %s", PaymentsDatabaseRepository.TABLE_NAME);
        return sampleQuery(sqlQuery);
    }

    @Override
    public void create(Payment payment) throws SQLException {
        var sqlQuery = String.format("INSERT INTO %s VALUES (?,?,?,?)", PaymentsDatabaseRepository.TABLE_NAME);
        var paymentId = UUID.randomUUID();
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(
                    1,
                    paymentId.toString());
            statement.setString(
                    2,
                    payment.getPayer()
            );
            statement.setDouble(
                    3,
                    payment.getAmount()
            );
            statement.setTimestamp(
                    4,
                    Timestamp.valueOf(payment.getPaymentDate())
            );
            statement.execute();
        } catch (SQLException exception) {
            throw exception;
        }

    }

    @Override
    public void delete(UUID id) throws SQLException {
        var sqlQuery = String.format("DELETE FROM %s WHERE id = ?", PaymentsDatabaseRepository.TABLE_NAME);
        try (var connection = dataSource.getConnection(); var statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, id.toString());
            statement.execute();
        } catch (SQLException exception) {
            throw exception;
        }
    }

    @Override
    public void deleteAll() throws SQLException {
        var sqlQuery = String.format("DELETE FROM %s", PaymentsDatabaseRepository.TABLE_NAME);
        voidQuery(sqlQuery);
    }

    /**
     * Возвращает результат выполнения SQL запроса. Используется c SELECT в запросе.
     *
     * @param sqlQuery SQL запрос
     * @return результат запроса
     */
    private List<Payment> sampleQuery(String sqlQuery) throws SQLException {
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(sqlQuery);
             var resultSet = statement.executeQuery()) {
            var accidents = new ArrayList<Payment>();
            while (resultSet.next()) {
                accidents.add(new Payment(
                        UUID.fromString(resultSet.getString("id")),
                        resultSet.getString("payer"),
                        resultSet.getDouble("amount"),
                        resultSet.getTimestamp("payment_date").toLocalDateTime()
                ));
            }
            return accidents;
        } catch (SQLException exception) {
            throw exception;
        }
    }

    /**
     * Выполняет запрос SQL, результат выполнения которого ничего не возвращает.
     *
     * @param sqlQuery SQL запрос
     */
    private void voidQuery(String sqlQuery) throws SQLException {
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(sqlQuery)) {
            statement.execute();
        } catch (Exception exception) {
            throw exception;
        }
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
                                + "id VARCHAR(40) PRIMARY KEY, "
                                + "payer VARCHAR(40) NOT NULL, "
                                + "amount DOUBLE, "
                                + "payment_date TIMESTAMP NOT NULL"
                                + ")");
            }
        } catch (SQLException exception) {
            throw exception;
        }
    }

}

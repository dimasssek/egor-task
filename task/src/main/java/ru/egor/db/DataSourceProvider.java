package ru.egor.db;

import javax.sql.DataSource;

/**
 * Определяет провайдера источника данных.
 */
public interface DataSourceProvider {
    /**
     * Получение источника данных.
     *
     * @return источник данных.
     */
    DataSource getDataSource();
}
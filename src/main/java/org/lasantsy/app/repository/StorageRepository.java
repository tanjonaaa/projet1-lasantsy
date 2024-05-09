package org.lasantsy.app.repository;

import lombok.AllArgsConstructor;
import org.lasantsy.app.entity.Storage;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Instant;

@Repository
@AllArgsConstructor
public class StorageRepository {
    private final Connection connection;

    public PreparedStatement createStorageCreationStatement(Storage storage) throws SQLException {
        String sql = "INSERT INTO \"storage\" (value, storage_datetime, id_station, id_product) " +
                "VALUES (?, ?, ?, ?)";
        PreparedStatement statement = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setDouble(1, storage.getValue());
        statement.setTimestamp(2, Timestamp.from(storage.getStorageDatetime()));
        statement.setInt(3, storage.getIdStation());
        statement.setInt(4, storage.getIdProduct());
        return statement;
    }

    public Storage getStorage(Instant datetime, Integer idStation, Integer idProduct) throws SQLException {
        String sql = "SELECT * FROM \"storage\" " +
                "WHERE storage_datetime <= ? AND id_station = ? AND id_product = ? " +
                "ORDER BY storage_datetime DESC LIMIT 1";
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setTimestamp(1, Timestamp.from(datetime));
        statement.setInt(2, idStation);
        statement.setInt(3, idProduct);
        ResultSet resultSet = statement.executeQuery();
        if(!resultSet.next())
            return null;
        return StorageRepository.mapResultSet(resultSet);
    }

    public static Storage mapResultSet(ResultSet resultSet) throws SQLException{
        return Storage.builder()
                .id(resultSet.getInt("id"))
                .value(resultSet.getDouble("value"))
                .storageDatetime(resultSet.getTimestamp("storage_datetime").toInstant())
                .idProduct(resultSet.getInt("id_product"))
                .idStation(resultSet.getInt("id_station"))
                .build();
    }
}

package org.lasantsy.app.repository;

import lombok.AllArgsConstructor;
import org.lasantsy.app.model.Storage;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
@AllArgsConstructor
public class StorageRepository {
    private final Connection connection;

    public Storage createStorage(Storage storage) throws SQLException {
        String sql = "INSERT INTO \"storage\" (value, storage_datetime, id_station, id_product)";
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setDouble(1, storage.getValue());
        statement.setTimestamp(2, Timestamp.from(storage.getStorageDatetime()));
        statement.setInt(3, storage.getIdStation());
        statement.setInt(4, storage.getIdProduct());
        statement.execute();
        ResultSet resultSet = statement.getGeneratedKeys();
        if(!resultSet.next())
            return null;
        return this.mapResultSet(resultSet);
    }

    private Storage mapResultSet(ResultSet resultSet) throws SQLException{
        return Storage.builder()
                .id(resultSet.getInt("id"))
                .value(resultSet.getDouble("value"))
                .storageDatetime(resultSet.getTimestamp("storage_datetime").toInstant())
                .idProduct(resultSet.getInt("id_product"))
                .idStation(resultSet.getInt("id_station"))
                .build();
    }
}

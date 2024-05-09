package org.lasantsy.app.repository;

import lombok.AllArgsConstructor;
import org.lasantsy.app.entity.EvaporationRate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@AllArgsConstructor
public class EvaporationRateRepository {
    private final Connection connection;

    public EvaporationRate save(EvaporationRate evaporationRate) throws SQLException {
        String sql = (evaporationRate.getId() == null) ?
                "INSERT INTO \"evaporation_rate\" (value, id_product, id_station) VALUES (?, ?, ?)"
                : "UPDATE \"evaporation_rate\" SET value = ?, id_product = ?, id_station = ? " +
                "WHERE id = ?";
        PreparedStatement statement = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setDouble(1, evaporationRate.getValue());
        statement.setInt(2, evaporationRate.getIdProduct());
        statement.setInt(3, evaporationRate.getIdStation());
        if(evaporationRate.getId() != null)
            statement.setInt(4, evaporationRate.getId());
        statement.execute();
        this.connection.commit();
        ResultSet resultSet = statement.getGeneratedKeys();
        if(!resultSet.next())
            return null;
        return EvaporationRateRepository.mapResultSet(resultSet);
    }

    public EvaporationRate getEvaporationRate(Integer stationId, Integer productId) throws SQLException {
        String sql = "SELECT * FROM \"evaporation_rate\" WHERE id_station = ? AND id_product = ?";
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setInt(1, stationId);
        statement.setInt(2, productId);
        ResultSet resultSet = statement.executeQuery();
        this.connection.commit();
        if(!resultSet.next())
            return null;
        return EvaporationRateRepository.mapResultSet(resultSet);
    }

    static EvaporationRate mapResultSet(ResultSet resultSet) throws SQLException{
        return EvaporationRate.builder()
                .id(resultSet.getInt("id"))
                .value(resultSet.getDouble("value"))
                .idProduct(resultSet.getInt("id_product"))
                .idStation(resultSet.getInt("id_station"))
                .build();
    }
}

package org.lasantsy.app.repository;

import lombok.AllArgsConstructor;
import org.lasantsy.app.entity.Movement;
import org.lasantsy.app.entity.enums.MovementType;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
@AllArgsConstructor
public class MovementRepository {
    private final Connection connection;

    public PreparedStatement createMovementCreationStatement(Movement movement) throws SQLException {
        String sql = "INSERT INTO \"movement\" (value, movement_type, movement_datetime, id_station, id_product) " +
                "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setDouble(1, movement.getValue());
        statement.setString(2, movement.getMovementType().toString());
        statement.setTimestamp(3, Timestamp.from(movement.getMovementDatetime()));
        statement.setInt(4, movement.getIdStation());
        statement.setInt(5, movement.getIdProduct());
        return statement;
    }

    static Movement mapResultSet(ResultSet resultSet) throws SQLException {
        return Movement.builder()
                .id(resultSet.getInt("id"))
                .value(resultSet.getDouble("value"))
                .movementType(MovementType.valueOf(resultSet.getString("movement_type")))
                .movementDatetime(resultSet.getTimestamp("movement_datetime").toInstant())
                .idStation(resultSet.getInt("id_station"))
                .idProduct(resultSet.getInt("id_product"))
                .build();
    }
}

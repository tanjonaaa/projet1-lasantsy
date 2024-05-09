package org.lasantsy.app.repository;

import lombok.AllArgsConstructor;
import org.lasantsy.app.entity.Movement;
import org.lasantsy.app.entity.Storage;
import org.lasantsy.app.entity.enums.MovementType;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@AllArgsConstructor
public class StationRepository {
    private final Connection connection;
    private final StorageRepository storageRepository;
    private final MovementRepository movementRepository;

    public Storage makeMovement(Movement movement) throws SQLException {
        PreparedStatement movementStatement = this.movementRepository.createMovementCreationStatement(movement);
        Storage currentStorage = this.storageRepository.getStorage(
                movement.getMovementDatetime(),
                movement.getIdStation(),
                movement.getIdProduct()
        );
        Double currentValue = (currentStorage == null) ? 0.0 : currentStorage.getValue();
        Storage.StorageBuilder storageBuilder = Storage.builder();
        storageBuilder.value(
                movement.getMovementType().equals(MovementType.SALE)
                        ? (currentValue - movement.getValue())
                        : (currentValue + movement.getValue())
        );
        storageBuilder.storageDatetime(movement.getMovementDatetime());
        storageBuilder.idStation(movement.getIdStation());
        storageBuilder.idProduct(movement.getIdProduct());
        PreparedStatement storageStatement = this.storageRepository.createStorageCreationStatement(storageBuilder.build());
        movementStatement.execute();
        storageStatement.execute();
        this.connection.commit();
        ResultSet storageResultSet = storageStatement.getGeneratedKeys();
        if(!storageResultSet.next())
            return null;
        return StorageRepository.mapResultSet(storageResultSet);
    }
}

package org.lasantsy.app.service;

import lombok.AllArgsConstructor;
import org.lasantsy.app.entity.EvaporationRate;
import org.lasantsy.app.entity.Movement;
import org.lasantsy.app.entity.Storage;
import org.lasantsy.app.entity.enums.MovementType;
import org.lasantsy.app.repository.EvaporationRateRepository;
import org.lasantsy.app.repository.StationRepository;
import org.lasantsy.app.repository.StorageRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;

@Service
@AllArgsConstructor
public class StationService {
    private final StationRepository stationRepository;
    private final StorageRepository storageRepository;
    private final EvaporationRateRepository evaporationRateRepository;

    public Storage makeProductMovement(Movement movement){
        if(movement.getMovementDatetime() == null)
            movement.setMovementDatetime(Instant.now());
        try{
            if(
                    movement.getMovementType().equals(MovementType.SALE)
            ){
                Storage currentStorage = this.getQuantityOfProduct(
                        movement.getIdStation(),
                        movement.getIdProduct(),
                        movement.getMovementDatetime()
                );

                if(currentStorage.getValue() < movement.getValue())
                    throw new RuntimeException("Not enough storage to perform the movement");
            }
            return this.stationRepository.makeMovement(movement);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Storage getQuantityOfProduct(Integer stationId, Integer productId, Instant datetime){
        if(datetime == null)
            datetime = Instant.now();
        try{
            Storage storage = this.storageRepository.getStorage(
                    datetime,
                    stationId,
                    productId
            );
            EvaporationRate evaporationRate = this.evaporationRateRepository.getEvaporationRate(
                    stationId,
                    productId
            );

            long daysBetween = Duration.between(datetime, storage.getStorageDatetime()).toDays();

            System.out.println(daysBetween);

            storage.setValue(storage.getValue() - (evaporationRate.getValue() * Math.abs(daysBetween)));

            return storage;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}

package org.lasantsy.app.endpoint;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.lasantsy.app.model.Storage;
import org.lasantsy.app.repository.StorageRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/station")
@AllArgsConstructor
public class StationController {
    private final StorageRepository storageRepository;

    @PostMapping("/{stationId}/storage")
    public List<Storage> createNewStorages(@PathVariable Integer stationId, @RequestBody List<Storage> storages){
            return storages.stream()
                    .map(storage -> {
                        storage.setIdStation(stationId);
                        try {
                            return this.storageRepository.createStorage(storage);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }).collect(Collectors.toList());
    }
}

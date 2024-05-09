package org.lasantsy.app.endpoint;

import java.time.Instant;
import lombok.AllArgsConstructor;
import org.lasantsy.app.entity.Movement;
import org.lasantsy.app.entity.Storage;
import org.lasantsy.app.entity.enums.MovementType;
import org.lasantsy.app.service.StationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stations")
@AllArgsConstructor
public class StationController {
    private final StationService stationService;

    @PostMapping("/{stationId}/restock")
    public Storage restockStation(@PathVariable Integer stationId, @RequestBody Movement movement){
        movement.setMovementType(MovementType.RESTOCKING);
        movement.setIdStation(stationId);
        return this.stationService.makeProductMovement(movement);
    }

    @PostMapping("/{stationId}/sell")
    public Storage sellStationProduct(@PathVariable Integer stationId, @RequestBody Movement movement){
        movement.setMovementType(MovementType.SALE);
        movement.setIdStation(stationId);
        return this.stationService.makeProductMovement(movement);
    }

    @GetMapping("/{stationId}/product-quantity")
    public Storage getQuantityOfProduct(
            @PathVariable Integer stationId,
            @RequestParam(name = "id-product") Integer productId,
            @RequestParam(name = "datetime", required = false)  Instant datetime
    ){
        return this.stationService.getQuantityOfProduct(stationId, productId, datetime);
    }
}

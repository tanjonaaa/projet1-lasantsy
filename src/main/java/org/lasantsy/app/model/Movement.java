package org.lasantsy.app.model;

import java.time.Instant;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.lasantsy.app.model.enums.MovementType;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class Movement {
    private Integer id;
    private Double value;
    private MovementType movementType;
    private Instant movementDatetime;
    private Integer idStation;
    private Integer idProduct;
}

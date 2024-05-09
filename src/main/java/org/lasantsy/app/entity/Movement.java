package org.lasantsy.app.entity;

import java.time.Instant;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.lasantsy.app.entity.enums.MovementType;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Movement {
    private Integer id;
    private Double value;
    private MovementType movementType;
    private Instant movementDatetime;
    private Integer idStation;
    private Integer idProduct;
}

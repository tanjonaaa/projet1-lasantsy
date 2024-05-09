package org.lasantsy.app.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EvaporationRate {
    private Integer id;
    //L/J
    private Double value;
    private Integer idProduct;
    private Integer idStation;
}

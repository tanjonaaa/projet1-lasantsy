package org.lasantsy.app.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.Instant;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Storage {
    private Integer id;
    private Double value;
    private Instant storageDatetime;
    private Integer idStation;
    private Integer idProduct;
}

package org.lasantsy.app.model;

import lombok.*;

import java.time.Instant;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Storage {
    private Integer id;
    private Double value;
    private Instant storageDatetime;
    private Integer idStation;
    private Integer idProduct;
}

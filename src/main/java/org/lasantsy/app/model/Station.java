package org.lasantsy.app.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class Station {
    private Integer id;
    private String name;
    private String longitude;
    private String latitude;
    private Integer employeesNumber;
}
package org.lasantsy.app.service;

import lombok.AllArgsConstructor;
import org.lasantsy.app.entity.EvaporationRate;
import org.lasantsy.app.repository.EvaporationRateRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@AllArgsConstructor
public class ProductService {
    private final EvaporationRateRepository evaporationRateRepository;

    public EvaporationRate saveEvaporationRate(Integer productId, EvaporationRate evaporationRate){
        try{
            evaporationRate.setIdProduct(productId);
            return this.evaporationRateRepository.save(evaporationRate);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}

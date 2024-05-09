package org.lasantsy.app.endpoint;

import lombok.AllArgsConstructor;
import org.lasantsy.app.entity.EvaporationRate;
import org.lasantsy.app.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PutMapping("/{productId}/evaporation-rate")
    public EvaporationRate saveEvaporationRate(
            @PathVariable Integer productId,
            @RequestBody EvaporationRate evaporationRate
    ){
        return this.productService.saveEvaporationRate(productId, evaporationRate);
    }
}

package org.lasantsy.app;

import lombok.AllArgsConstructor;
import org.junit.runner.RunWith;
import org.lasantsy.app.service.StationService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AllArgsConstructor
public class StationRestControllerIT {
    private final StationService stationService;
}

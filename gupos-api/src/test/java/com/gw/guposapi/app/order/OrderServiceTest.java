package com.gw.guposapi.app.order;

import com.gw.guposapi.app.order.application.in.OrderUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(value = "local")
public class OrderServiceTest {
    @Autowired
    private OrderUseCase orderUseCase;

    @Test
    void orderTest() {

    }
}

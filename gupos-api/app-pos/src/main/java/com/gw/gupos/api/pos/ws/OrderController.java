package com.gw.gupos.api.ws;

import com.gw.gupos.api.ws.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final SimpMessagingTemplate template;

    /**
     * Message 엔드포인트로 데이터와 함께 호출을 하면 "/sub/message"를 수신하는 사용자에게 메시지를 전달합니다.
     *
     * @param order
     * @return
     */
    @PostMapping("/api/order")
    public OrderDto send2(@RequestBody OrderDto order) {
        // 구독중인 모든 사용자에게 메시지를 전달합니다.
        template.convertAndSend("/sub/store/" + order.getStoreId(), order);
        return order;
    }
}

package com.example.orderservice.messagequeue;

import com.example.orderservice.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;


    List<Field> fields = Arrays.asList(new Field("string", true, "order_id"),
            new Field("string", true, "user_id"),
            new Field("string", true, "product_id"),
            new Field("int", true, "qty"),
            new Field("int", true, "unit_price"),
            new Field("int", true, "total_price"),
            new Field("string", true, "order_at"),
            new Field("string", true, "order_status"));

    Schema schema = Schema.builder()
            .type("struct")
            .fields(fields)
            .optional(false)
            .name("orders")
            .build();

    // kafka로 주문 데이터 전송 메서드
    public OrderDto send(String topic, OrderDto orderDto) {
        Payload payload = Payload.builder()
                .order_id(orderDto.getOrderId())
                .user_id(orderDto.getUserId())
                .product_id(orderDto.getProductId())
                .qty(orderDto.getQty())
                .unit_price(orderDto.getUnitPrice())
                .total_price(orderDto.getTotalPrice())
                .build();

        KafkaOrderDto kafkaOrderDto = new KafkaOrderDto(schema,payload);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        // 전달시에는 문자열로 전달하고, 해당 문자열이 json 형태로 전달되어야 함
        try {
            jsonString = mapper.writeValueAsString(kafkaOrderDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        kafkaTemplate.send(topic, jsonString); // 전송
        log.info("Order Producer sent data from the Order Service : {}", kafkaOrderDto);

        return orderDto;
    }
}

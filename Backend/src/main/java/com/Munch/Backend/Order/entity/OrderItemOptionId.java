package com.Munch.Backend.Order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderItemOptionId implements Serializable {

    @Column(name = "order_item_id", nullable = false)
    private UUID orderItemId;

    @Column(name = "option_id", nullable = false)
    private UUID optionId;
}


package com.Munch.Backend.Promo.entity;

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
public class OrderPromoId implements Serializable {

    @Column(name = "order_id", nullable = false)
    private UUID orderId;

    @Column(name = "promo_id", nullable = false)
    private UUID promoId;
}


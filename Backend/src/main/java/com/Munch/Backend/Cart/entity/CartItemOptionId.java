package com.Munch.Backend.Cart.entity;

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
public class CartItemOptionId implements Serializable {

    @Column(name = "cart_item_id", nullable = false)
    private UUID cartItemId;

    @Column(name = "option_id", nullable = false)
    private UUID optionId;
}


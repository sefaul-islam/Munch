package com.Munch.Backend.Promo.entity;

import com.Munch.Backend.Order.entity.Order;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "order_promo",
        indexes = {
                @Index(name = "idx_order_promo_order_id", columnList = "order_id"),
                @Index(name = "idx_order_promo_promo_id", columnList = "promo_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderPromo {

    @EmbeddedId
    private OrderPromoId id;

    @NotNull(message = "Order is required")
    @MapsId("orderId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @NotNull(message = "Promo code is required")
    @MapsId("promoId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "promo_id", nullable = false)
    private PromoCode promoCode;

    @NotNull(message = "Applied discount is required")
    @DecimalMin(value = "0.0", message = "Applied discount must be zero or positive")
    @Digits(integer = 10, fraction = 2, message = "Applied discount format is invalid")
    @Column(name = "applied_discount", nullable = false, precision = 12, scale = 2)
    private BigDecimal appliedDiscount;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}


package com.Munch.Backend.Order.entity;

import com.Munch.Backend.Restaurant.entity.Address;
import com.Munch.Backend.Restaurant.entity.Restaurant;
import com.Munch.Backend.User.Entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "orders",
        indexes = {
                @Index(name = "idx_orders_user_id", columnList = "user_id"),
                @Index(name = "idx_orders_restaurant_id", columnList = "restaurant_id"),
                @Index(name = "idx_orders_status", columnList = "status")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "User is required")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull(message = "Restaurant is required")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @NotNull(message = "Address is required")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @NotNull(message = "Order status is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private OrderStatus status;

    @NotNull(message = "Subtotal is required")
    @DecimalMin(value = "0.0", message = "Subtotal must be zero or positive")
    @Digits(integer = 10, fraction = 2, message = "Subtotal format is invalid")
    @Column(name = "subtotal", nullable = false, precision = 12, scale = 2)
    private BigDecimal subtotal;

    @NotNull(message = "Delivery fee is required")
    @DecimalMin(value = "0.0", message = "Delivery fee must be zero or positive")
    @Digits(integer = 10, fraction = 2, message = "Delivery fee format is invalid")
    @Column(name = "delivery_fee", nullable = false, precision = 12, scale = 2)
    private BigDecimal deliveryFee;

    @NotNull(message = "Discount is required")
    @DecimalMin(value = "0.0", message = "Discount must be zero or positive")
    @Digits(integer = 10, fraction = 2, message = "Discount format is invalid")
    @Column(name = "discount", nullable = false, precision = 12, scale = 2)
    private BigDecimal discount;

    @NotNull(message = "Total is required")
    @DecimalMin(value = "0.0", message = "Total must be zero or positive")
    @Digits(integer = 10, fraction = 2, message = "Total format is invalid")
    @Column(name = "total", nullable = false, precision = 12, scale = 2)
    private BigDecimal total;

    @Column(name = "special_instructions", length = 1000)
    private String specialInstructions;

    @Column(name = "placed_at", nullable = false, updatable = false)
    private LocalDateTime placedAt;

    @Column(name = "delivered_at")
    private LocalDateTime deliveredAt;

    @PrePersist
    void onCreate() {
        if (this.status == null) {
            this.status = OrderStatus.PENDING;
        }
        if (this.subtotal == null) {
            this.subtotal = BigDecimal.ZERO;
        }
        if (this.deliveryFee == null) {
            this.deliveryFee = BigDecimal.ZERO;
        }
        if (this.discount == null) {
            this.discount = BigDecimal.ZERO;
        }
        if (this.total == null) {
            this.total = this.subtotal.add(this.deliveryFee).subtract(this.discount);
        }
        if (this.placedAt == null) {
            this.placedAt = LocalDateTime.now();
        }
    }

    public enum OrderStatus {
        PENDING,
        CONFIRMED,
        PREPARING,
        READY,
        PICKED_UP,
        DELIVERED,
        CANCELLED
    }
}


package com.Munch.Backend.Order.entity;

import com.Munch.Backend.Restaurant.entity.FoodItemOption;
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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "order_item_option",
        indexes = {
                @Index(name = "idx_order_item_option_order_item_id", columnList = "order_item_id"),
                @Index(name = "idx_order_item_option_option_id", columnList = "option_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemOption {

    @EmbeddedId
    private OrderItemOptionId id;

    @NotNull(message = "Order item is required")
    @MapsId("orderItemId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_item_id", nullable = false)
    private OrderItem orderItem;

    @NotNull(message = "Food item option is required")
    @MapsId("optionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "option_id", nullable = false)
    private FoodItemOption option;

    @NotBlank(message = "Option name snapshot is required")
    @Column(name = "option_name_snapshot", nullable = false, length = 120)
    private String optionNameSnapshot;

    @NotNull(message = "Extra price snapshot is required")
    @DecimalMin(value = "0.0", message = "Extra price snapshot must be zero or positive")
    @Digits(integer = 10, fraction = 2, message = "Extra price snapshot format is invalid")
    @Column(name = "extra_price_snapshot", nullable = false, precision = 12, scale = 2)
    private BigDecimal extraPriceSnapshot;

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


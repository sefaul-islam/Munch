package com.Munch.Backend.Restaurant.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
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
        name = "food_item_option",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_food_item_option_group_name",
                        columnNames = {"food_item_option_group_id", "name"}
                )
        },
        indexes = {
                @Index(name = "idx_food_item_option_group_id", columnList = "food_item_option_group_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodItemOption {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Option name is required")
    @Column(name = "name", nullable = false, length = 120)
    private String name;

    @NotNull(message = "Extra price is required")
    @DecimalMin(value = "0.0", message = "Extra price must be zero or positive")
    @Digits(integer = 10, fraction = 2, message = "Extra price format is invalid")
    @Column(name = "extra_price", nullable = false, precision = 12, scale = 2)
    private BigDecimal extraPrice;

    @NotNull(message = "Food item option group is required")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "food_item_option_group_id", nullable = false)
    private FoodItemOptionGroup foodItemOptionGroup;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
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

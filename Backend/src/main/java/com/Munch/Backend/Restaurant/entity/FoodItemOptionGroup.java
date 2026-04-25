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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "food_item_option_group",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_food_item_option_group_food_item_name",
                        columnNames = {"food_item_id", "name"}
                )
        },
        indexes = {
                @Index(name = "idx_food_item_option_group_food_item_id", columnList = "food_item_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodItemOptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Option group name is required")
    @Column(name = "name", nullable = false, length = 120)
    private String name;

    @Column(name = "is_required", nullable = false)
    private boolean isRequired;

    @NotNull(message = "Max selections is required")
    @Positive(message = "Max selections must be greater than zero")
    @Column(name = "max_selections", nullable = false)
    private Integer maxSelections;

    @NotNull(message = "Food item is required")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "food_item_id", nullable = false)
    private FoodItem foodItem;

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

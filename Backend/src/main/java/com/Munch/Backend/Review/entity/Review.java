package com.Munch.Backend.Review.entity;

import com.Munch.Backend.Delivery.entity.Rider;
import com.Munch.Backend.Order.entity.Order;
import com.Munch.Backend.Restaurant.entity.Restaurant;
import com.Munch.Backend.User.Entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "review",
        indexes = {
                @Index(name = "idx_review_reviewer_id", columnList = "reviewer_id"),
                @Index(name = "idx_review_restaurant_id", columnList = "restaurant_id"),
                @Index(name = "idx_review_rider_id", columnList = "rider_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "Order is required")
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private Order order;

    @NotNull(message = "Reviewer is required")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reviewer_id", nullable = false)
    private User reviewer;

    @NotNull(message = "Restaurant is required")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @NotNull(message = "Rider is required")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rider_id", nullable = false)
    private Rider rider;

    @Min(value = 1, message = "Restaurant rating must be at least 1")
    @Max(value = 5, message = "Restaurant rating must be at most 5")
    @Column(name = "restaurant_rating", nullable = false)
    private int restaurantRating;

    @Min(value = 1, message = "Rider rating must be at least 1")
    @Max(value = 5, message = "Rider rating must be at most 5")
    @Column(name = "rider_rating", nullable = false)
    private int riderRating;

    @Column(name = "comment", length = 1000)
    private String comment;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
}


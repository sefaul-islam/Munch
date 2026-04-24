package com.Munch.Backend.Restaurant.repository;

import com.Munch.Backend.Restaurant.entity.Restaurant;
import com.Munch.Backend.User.Entity.User;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {

    List<Restaurant> findAllByOwner(User owner);

    boolean existsByNameAndOwner_Id(String name, UUID ownerId);
}



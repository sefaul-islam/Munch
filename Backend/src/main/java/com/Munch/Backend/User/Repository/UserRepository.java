package com.Munch.Backend.User.Repository;

import com.Munch.Backend.User.Entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("select u from User u left join fetch u.roles where u.email = :email")
    Optional<User> findByEmailWithRoles(@Param("email") String email);

    @Query("select u from User u left join fetch u.roles where u.id = :id")
    Optional<User> findByIdWithRoles(@Param("id") UUID id);
}

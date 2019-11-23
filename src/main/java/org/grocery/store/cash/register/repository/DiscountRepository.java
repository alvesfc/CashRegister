package org.grocery.store.cash.register.repository;

import org.grocery.store.cash.register.repository.entity.Discount;
import org.grocery.store.cash.register.repository.entity.DiscountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    Optional<Discount> findByType(@Param("type") DiscountType type);
}

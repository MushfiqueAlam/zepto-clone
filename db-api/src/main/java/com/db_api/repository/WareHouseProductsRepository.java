package com.db_api.repository;

import com.db_api.models.WareHouseProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WareHouseProductsRepository extends JpaRepository<WareHouseProducts, UUID> {
}

package com.db_api.repository;

import com.db_api.models.WareHouseProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WareHouseProductsRepository extends JpaRepository<WareHouseProducts, UUID> {

    @Query(value = "select * from ware_house_products where wid=:wid",nativeQuery = true)
    List<WareHouseProducts> getWareHouseProductsByWid(UUID wid);

}

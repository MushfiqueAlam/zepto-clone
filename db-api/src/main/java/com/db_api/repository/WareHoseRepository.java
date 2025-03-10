package com.db_api.repository;

import com.db_api.models.WareHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface WareHoseRepository extends JpaRepository<WareHouse, UUID> {
}

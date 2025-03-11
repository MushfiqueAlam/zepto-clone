package com.db_api.repository;

import com.db_api.models.AppOrder;
import com.db_api.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, UUID> {
    AppUser findByEmail(String pinCode);
    @Query(value = "select * from app_user where user_type='regular' and pin_code=:pinCode", nativeQuery = true)
    public List<AppUser> findByPinCode(@Param("pinCode") int pinCode);

}

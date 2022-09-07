package com.example.demo.repository;

import com.example.demo.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
    @Query("SELECT u FROM Promotion u WHERE u.code_name = ?1")
    Promotion findPromotionByCode_name(String codeName);

    @Transactional
    @Modifying
    @Query(value = "delete from Promotion p where p.id=?", nativeQuery = true)
    void deletePromoId(Integer id);
}

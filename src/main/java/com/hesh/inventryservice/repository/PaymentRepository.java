package com.hesh.inventryservice.repository;

import com.hesh.inventryservice.entity.All_Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<All_Orders,Integer> {
}
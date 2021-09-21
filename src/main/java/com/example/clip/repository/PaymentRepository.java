package com.example.clip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.clip.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

	public List<Payment> findByStatus(String status);
	
}

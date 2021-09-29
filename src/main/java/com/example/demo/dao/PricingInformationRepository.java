package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dao.entity.PricingInformation;

public interface PricingInformationRepository extends JpaRepository<PricingInformation, String> {

}

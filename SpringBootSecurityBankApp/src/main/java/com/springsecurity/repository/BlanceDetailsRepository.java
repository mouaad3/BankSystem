package com.springsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springsecurity.model.Blance_Details;


@Repository
public interface BlanceDetailsRepository extends JpaRepository<Blance_Details, Long> {
	
	Blance_Details findFirstByOrderByIdDesc();
	@Query(value = "SELECT  * FROM blance_details  WHERE blance_details.blance_details_id= :blance_details_id", nativeQuery = true)
	List<Blance_Details> findByForainKey(Long blance_details_id);
}

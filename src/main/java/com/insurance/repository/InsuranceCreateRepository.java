package com.insurance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.insurance.model.InsuranceCreateData;

@Repository
public interface InsuranceCreateRepository extends JpaRepository<InsuranceCreateData, Integer>{

	@Query(value = "select * from insurance_create \r\n"
			 	 + "left join user_data on  insurance_create.user_cid = user_data.user_id \r\n"
			 	 + "right join insurance_data on  insurance_create.user_cid = insurance_data.insurance_id \r\n", nativeQuery = true)
	List<InsuranceCreateData> findAllData();
	
	@Query(value = "select * from insurance_create \r\n"
				 + "left join user_data on  insurance_create.user_cid = user_data.user_id \r\n"
				 + "right join insurance_data on  insurance_create.user_cid = insurance_data.insurance_id \r\n"
				 + "where insurance_create.status = :status", nativeQuery = true)
	List<InsuranceCreateData> findAllByStatus(String status);
	
	@Query(value = "select * from insurance_create \r\n"
				 + "left join user_data on  insurance_create.user_cid = user_data.user_id \r\n"
				 + "right join insurance_data on  insurance_create.user_cid = insurance_data.insurance_id \r\n"
				 + "where insurance_create.month_period <= :monthPeriod", nativeQuery = true)
	List<InsuranceCreateData> findAllByMonthPeriod(int monthPeriod);
	
	@Query(value = "select * from insurance_create \r\n"
				 + "left join user_data on  insurance_create.user_cid = user_data.user_id \r\n"
				 + "right join insurance_data on  insurance_create.user_cid = insurance_data.insurance_id \r\n"
				 + "where user_data.full_name = :fullName", nativeQuery = true)
	List<InsuranceCreateData> findAllInsuranceForUser(String fullName);
}

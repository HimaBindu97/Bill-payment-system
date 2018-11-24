package com.cts.billpay.vendor.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cts.billpay.vendor.entities.Vendor;
public interface VendorDao extends JpaRepository<Vendor, Long> {
	
	@Query(value="SELECT * FROM vendor.vendortable vt, vendor.vendorandvendortype vvt WHERE vt.vendorId = vvt.vendorId  AND vvt.vendortype = :vType",nativeQuery=true)
	List<Vendor> getVendorNames(@Param("vType") String vType);
}

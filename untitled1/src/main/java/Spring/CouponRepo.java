package Spring;


import java.util.ArrayList;
import java.util.Date;


import Spring.entities.Coupon;
import Spring.enums.CouponType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;




@Repository   
public interface CouponRepo extends CrudRepository<Coupon, Integer> {
	
	/**
	 * Remove Coupon by Id and compid
	 * @param compid
	 * @param coupid
	 */
	@Transactional
	@Modifying 
	@Query("DELETE FROM Coupon coup WHERE coup.id = :coupid AND coup.company.id = :compid")
	void removeCoupon(@Param("compid") int compid ,@Param("coupid") int coupid);
	
	/**
	 * Remove Coupon by expired date
	 *
	 */
	@Transactional
	@Modifying 
	@Query("DELETE FROM Coupon coup WHERE coup.endDate < :endDate")
	void removeExpiredCoupon(@Param("endDate") Date endDate);
	
	
	
	/**
	 * update coupon
	 * @param endDate
	 * @param price
	 * @param coupId
	 * @param compId
	 */
	@Transactional
	@Modifying
	@Query("UPDATE Coupon coup SET coupons.END_DATE = :END_DATE , coupons.PRICE = :price WHERE coupons.ID = :coupId AND coupons.COMPANY_ID = :compId")
	void updateCoupon(@Param("END_DATE") Date endDate ,@Param("price") double price ,@Param("coupId") int coupId ,@Param("compId") int compId);
	
	/***
	 * Getting Company's Coupon
	 * @param coupId
	 * @param compId
	 * @return Company's Coupon
	 */
	@Query("SELECT coup FROM Coupon coup WHERE coup.id = :coupId AND coup.company.id = :compId ")
	Coupon getCompanyCoupon(@Param("coupId") int coupId , @Param("compId") int compId);
	
	/***
	 * Get Coupon by type
	 * @param type
	 * @return Coupon
	 */
	ArrayList<Coupon> findCouponByType(CouponType type);
	
	/**
	 * Get Coupon by title
	 * @param title
	 * @return Coupon
	 */
	Coupon findCouponByTitle(String title);
	
	/***
	 * Getting Company's Coupon by type
//	 * @param coupId
	 * @param compId
	 * @param type
	 * @return Company's Coupon
	 */
	@Query("SELECT coup FROM coupons coup WHERE coup.COMPANY_ID = :compId AND coup.CATEGORY_ID = :type")
	ArrayList<Coupon> getCompanyCouponByType(@Param("compId") int compId , @Param("type") CouponType type);

	/***
	 * Getting Company's Coupons
	 * @param compId
	 * @return Company's Coupons
	 */
	@Query("SELECT coup FROM coupons coup WHERE  coup.COMPANY_ID = :compId ")
	ArrayList<Coupon> getCompanyCoupons(@Param("compId") int compId);
	
	/***
	 * Updating amount for purchase
	 * @param coupId
	 */
	@Transactional
	@Modifying
	@Query("UPDATE coupons coup set coup.AMOUNT = coup.amount-1 WHERE coup.ID = :coupId")
	void updateAmount(@Param("coupId") int coupId);
	
	/***
	 * Get all Customer Coupons
	 * @param customerId
	 * @return ArrayList<Coupon>
	 */
	ArrayList<Coupon> getCustomerCoupons(@Param("customerId") int customerId);
	
	/***
	 * Get all Customer Coupons by type
	 * @param type
	 * @param customerId
	 * @return ArrayList<Coupon>
	 */
	@Query("SELECT coup FROM coupons coup WHERE coup.CATEGORY_ID = :type AND coup.ID IN (SELECT coup.ID FROM coupons_vs_customers  WHERE coupons_vs_customers.CUSTOMER_ID = :customerId)")
	ArrayList<Coupon> getCustomerCouponsByType(@Param("type")CouponType type ,@Param("customerId") int customerId);

	/***
	 * Get all Customer Coupons by price
//	 * @param type
	 * @param customerId
	 * @return ArrayList<Coupon>
	 */
	@Query("SELECT coup FROM coupons coup WHERE coup.PRICE < :price AND coup.ID IN (SELECT coup.ID FROM coupons_vs_customers  WHERE coupons_vs_customers.CUSTOMER_ID = :customerId)")
	ArrayList<Coupon> getCustomerCouponsByPrice(@Param("price")double price ,@Param("customerId") int customerId);
	
	/***
	 * Removing Company Coupon
	 * @param companyId
	 */
	@Transactional
	@Modifying
	@Query("DELETE FROM coupons coup WHERE coup.COMPANY_ID= :companyId")
	void removeAllCompanyCoupons(@Param("companyId") int companyId);


	/***
	 * Getting Company Coupons by type
	 * @param companyId
	 * @param type
	 * @return ArrayList<Coupon>
	 */
	ArrayList<Coupon> findByCompanyIdAndType(int companyId , CouponType type);
	
	/***
	 * Get Customer Coupon by ID
//	 * @param customerID
	 * @return Coupon
	 */
	Coupon findByidAndCustomersId(int couponId , int customerId);
	
}


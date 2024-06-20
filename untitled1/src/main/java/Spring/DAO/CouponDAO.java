package Spring.DAO;

import java.util.ArrayList;
import java.util.Date;

import Spring.entities.Coupon;
import Spring.enums.CouponType;
import Spring.exceptions.CompanyNotFoundException;
import Spring.exceptions.CouponExistException;
import Spring.exceptions.CouponNotFoundException;
import Spring.exceptions.CouponsNotFoundException;
import org.springframework.stereotype.Service;



@Service
public interface CouponDAO {
	
	
	void createCoupon(Coupon coupon , int companyId) throws CouponExistException, CompanyNotFoundException;
	
	void removeCoupon(int couponId , int companyId) throws CouponNotFoundException,  CompanyNotFoundException;
	
	void updateCoupon(Date endDate , double price , int couponId , int companyId ) throws CouponNotFoundException , CompanyNotFoundException;
	
	Coupon getCoupon(int couponId) throws CouponNotFoundException;
	
	ArrayList<Coupon> getAllCoupons() throws CouponsNotFoundException;

	ArrayList<Coupon> getCouponsByType(CouponType type) throws CouponsNotFoundException;
	
	
	

}

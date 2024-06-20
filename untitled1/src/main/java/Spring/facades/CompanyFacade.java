package Spring.facades;

import java.util.ArrayList;
import java.util.Date;

import Spring.DBDAO.CompanyDBDAO;
import Spring.DBDAO.CouponDBDAO;
import Spring.entities.*;
import Spring.enums.ClientType;
import Spring.enums.CouponType;
import Spring.exceptions.*;
import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@ToString
@Component
public class CompanyFacade implements CouponClientFacade {
	/**
	 * @author ismael
	 * @author saber
	 * @author bahaa
	 *
	 */

	// Object's members
	@Getter
	private Company loggedIn;

	@Autowired
	private CompanyDBDAO compdb;

	@Autowired
	private CouponDBDAO coupdb;




	/****
	 * Login method for Company
	 */
	@Override
	public AdminFacade login(String name, String password, ClientType type) {

		// Checking type
		if (!type.equals(ClientType.COMPANY)) {
			return null;
		}

		// Checking name & password

		if (!compdb.login(name, password)) {
			return null;
		}

		loggedIn = compdb.getCompanyByNameAndPassword(name, password);
		return this;

	}

	/****
	 * Creating new Coupon
//	 *
//	 * @param coupon
//	 * @param companyId
//	 * @throws CouponExistException
//	 * @throws CompanyNotFoundException
	 */
	public synchronized void createCoupon(Coupon coupon) throws CouponExistException, CompanyNotFoundException {

		// Checking if Coupon exist
		Coupon couponCheck = coupdb.getCouponByTitle(coupon.getTitle());
		if (couponCheck != null) {
			throw new CouponExistException("Coupon " + coupon.getTitle() + " allready exist");
		}

		if (loggedIn == null) {
			throw new CompanyNotFoundException("Company with the ID: " + loggedIn.getId() + " doesn't exist");
		}
		// Success - creating Coupon
		coupdb.createCoupon(coupon, loggedIn.getId());
	}

	/***
	 * Removing Company's Coupon
	 * 
	 * @param couponId
	 * @throws CompanyNotFoundException
	 * @throws CouponNotFoundException
	 */
	public void removeCoupon(int couponId) throws CompanyNotFoundException, CouponNotFoundException {

		// Coupon to remove ( must be Company's Coupon)
		Coupon couponCheck = coupdb.getCompanyCoupon(couponId, loggedIn.getId());

		if (loggedIn == null) {
			throw new CompanyNotFoundException("Company doesn't login please relogin");
		}

		if (couponCheck == null) {
			throw new CouponNotFoundException("Coupon with the ID: " + couponId + " does not exist");
		}

		// Success - removing Coupon
		coupdb.removeCoupon(couponId, loggedIn.getId());

	}

	/***
	 * Update Company's Coupon
	 * 
	 * @param endDate
	 * @param price
	 * @param couponId
	 * @throws CompanyNotFoundException
	 * @throws CouponNotFoundException
	 */
	public void updateCoupon(Date endDate, double price, int couponId)
			throws CompanyNotFoundException, CouponNotFoundException {

		// Coupon to update ( must be Company's Coupon)
		Coupon couponCheck = coupdb.getCompanyCoupon(couponId, loggedIn.getId());

		if (loggedIn == null) {
			throw new CompanyNotFoundException("Company doesn't login please relogin");
		}

		if (couponCheck == null) {
			throw new CouponNotFoundException("Coupon with the ID: " + couponId + " does not exist");
		}

		// Success - updating Coupon
		coupdb.updateCoupon(endDate, price, couponId, loggedIn.getId());
	}

	/***
	 * Get Company Coupon
	 * 
	 * @param couponId
	 * @return Coupon
	 * @throws CompanyNotFoundException
	 * @throws CouponNotFoundException
	 */
	public Coupon getCompanyCoupon(int couponId) throws CompanyNotFoundException, CouponNotFoundException {

		// Coupon to update ( must be Company's Coupon)
		Coupon couponCheck = coupdb.getCompanyCoupon(couponId, loggedIn.getId());

		if (loggedIn == null) {
			throw new CompanyNotFoundException("Company doesn't login please relogin");
		}

		if (couponCheck == null) {
			throw new CouponNotFoundException("Coupon with the ID: " + couponId + " does not exist");
		}

		// Success - providing Coupon
		return couponCheck;
	}

	/***
	 * Getting Company's Coupons
	 * 
	 * @return
	 * @throws CouponsNotFoundException
	 * @throws CompanyNotFoundException
	 */
	public ArrayList<Coupon> getCompanyCoupons() throws CouponsNotFoundException, CompanyNotFoundException {
		// Company Coupons
		ArrayList<Coupon> companyCoupons = compdb.getCoupons(loggedIn.getId());

		// Checking Company
		if (loggedIn == null) {
			throw new CompanyNotFoundException("Company doesn't login please relogin");
		}
		// Checking Array List of Coupons
		if (companyCoupons == null) {
			throw new CouponsNotFoundException("There is no Coupons yet");
		}
		// Success - providing Company Coupons
		return companyCoupons;
	}

	/***
	 * Get Company Coupons by type
	 * @param type
	 * @return ArrayList<Coupon>
	 * @throws CouponsNotFoundException
	 * @throws CompanyNotFoundException
	 */
	public ArrayList<Coupon> getCompanyCouponsByType(CouponType type) throws CouponsNotFoundException, CompanyNotFoundException {
		// Company Coupons
		ArrayList<Coupon> companyCoupons = compdb.getCouponsByType(loggedIn.getId(), type);

		// Checking Company
		if (loggedIn == null) {
			throw new CompanyNotFoundException("Company doesn't login please relogin");
		}
		// Checking Array List of Coupons
		if (companyCoupons == null) {
			throw new CouponsNotFoundException("There is no Coupons yet");
		}
		
		// Success - providing Coupons by type
		return companyCoupons;
	}

}

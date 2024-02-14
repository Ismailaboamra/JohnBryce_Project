package DAO;

import JavaBeans.Coupon;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CouponsDAO {
    void addCoupon(Coupon coupon) throws SQLException;

    void updateCoupon(Coupon coupon) throws SQLException;

    void deleteCoupon(int couponID);

    ArrayList<Coupon> getAllCoupons() throws SQLException;

    Coupon getOneCoupon(int couponID) throws SQLException;

    void addCouponPurchase(int customerlD, int couponID);

    void deleteCouponPurchase(int customerlD, int couponID);
}

package Spring.DAO;

import Spring.JavaBeans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;


@Repository
public interface CouponsDAO extends JpaRepository<Coupon,Long>{
    void addCoupon(Coupon coupon) throws SQLException;

    void updateCoupon(Coupon coupon) throws SQLException;

    void deleteCoupon(int couponID);

    ArrayList<Coupon> getAllCoupons() throws SQLException;

    Coupon getOneCoupon(int couponID) throws SQLException;

    void addCouponPurchase(int customerlD, int couponID) throws SQLException;

    void deleteCouponPurchase(int customerlD, int couponID);
}

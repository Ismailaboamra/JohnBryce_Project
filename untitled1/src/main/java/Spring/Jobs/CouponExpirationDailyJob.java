package Spring.Jobs;

import Spring.DBDAO.CouponsDBDAO;
import Spring.JavaBeans.Coupon;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CouponExpirationDailyJob implements Runnable{

    private CouponsDBDAO couponsDAO;

    public CouponExpirationDailyJob() {
    }


    private boolean quit;
    @Override
    public void run() {
        this.quit = true;
        LocalDate currectDate = LocalDate.now();
        this.couponsDAO = new CouponsDBDAO();
        try {
            while (quit) {
                ArrayList<Coupon> arrayList = couponsDAO.getAllCoupons();

                    for (Coupon coupon : arrayList) {
                        if (coupon.getEndDate().toLocalDate().isBefore(currectDate)) {
                            this.couponsDAO.deleteCoupon(coupon.getId());
                            System.out.println("The coupon deleted by CouponExpirationDailyJob thread.");
                        }
                    }
                }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }
    public void stop(){
        this.quit = false;
    }
}

package Spring.Jobs;

import Spring.DBDAO.CouponDBDAO;
import Spring.entities.Coupon;
import Spring.exceptions.CouponsNotFoundException;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CouponExpirationDailyJob implements Runnable{

    private CouponDBDAO couponsDAO;

    public CouponExpirationDailyJob() {
    }


    private boolean quit;
    @Override
    public void run() {
        this.quit = true;
        LocalDate currectDate = LocalDate.now();
        this.couponsDAO = new CouponDBDAO();
        try {
            while (quit) {
                ArrayList<Coupon> arrayList = couponsDAO.getAllCoupons();

                    for (Coupon coupon : arrayList) {
                        if (true) {
                            this.couponsDAO.removeCompanyCoupons(coupon.getId());
                            System.out.println("The coupon deleted by CouponExpirationDailyJob thread.");
                        }
                    }
                }

        } catch (CouponsNotFoundException e) {
            throw new RuntimeException(e);

        }

    }
    public void stop(){
        this.quit = false;
    }
}

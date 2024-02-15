package Facade;

import DBDAO.CompaniesDBDAO;
import DBDAO.CouponsDBDAO;
import DBDAO.CustomersDBDAO;
import Facade.ClientFacade;
import JavaBeans.Category;
import JavaBeans.Coupon;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerFacade extends ClientFacade {

    private int customerID;

    public CustomerFacade(String email, String password) throws SQLException {
        if (login(email, password)) {
            super.customersDAO = new CustomersDBDAO();
            super.couponsDAO = new CouponsDBDAO();
            this.setCustomerID(((CustomersDBDAO) (super.customersDAO)).get_ID(email, password));
            System.out.println("LogIn Successfully :Customer - " + getCustomerID());

        } else {
            throw new IllegalArgumentException("Invalid email or password");


        }

    }

    @Override
    public boolean login(String email, String password) throws SQLException {
        super.customersDAO = new CustomersDBDAO();
        return super.customersDAO.isCustomerExists(email, password);
    }

    public void purchaseCoupon(Coupon coupon) throws SQLException {
        // check if the customer is parched the coupon.
        if (((CustomersDBDAO) (super.customersDAO)).checkCustomers_vs_coupons(getCustomerID(), coupon.getId())) {
            System.out.println("You can't buy same coupon twice");
            // check if the coupon amount != 0.
        } else if (coupon.getAmount() == 0) {
            System.out.println("You can't buy coupon ,the coupon amount = 0.");
            // check if the coupon EndDate is expired.
        } else if (coupon.getEndDate().toLocalDate().isBefore(LocalDate.now())) {
            System.out.println("the coupon you want to buy is expired.");
        } else {
            coupon.setAmount(coupon.getAmount() - 1);
            super.couponsDAO.updateCoupon(coupon);
            super.couponsDAO.addCouponPurchase(getCustomerID(), coupon.getId());
        }

    }

    public ArrayList<Coupon> getCustomerCoupons() throws SQLException {
        ArrayList<Coupon> coupons = new ArrayList<>();
        List<Integer> coupons_num = ((CustomersDBDAO) (super.customersDAO)).getAllCouponsNumbers(this.getCustomerID());
        for (int i = 0; i < coupons_num.size(); i++) {
            int couponID = coupons_num.get(i);
            Coupon coupon = ((CouponsDBDAO) (super.couponsDAO)).getOneCoupon(couponID);
            coupons.add(coupon);
        }
        return coupons;

    }

    public ArrayList<Coupon> getCustomerCoupons(Category category) throws SQLException {
        ArrayList<Coupon> coupons = new ArrayList<>();
        List<Integer> coupons_num = ((CustomersDBDAO) (super.customersDAO)).getAllCouponsNumbers(this.getCustomerID());
        for (int i = 0; i < coupons_num.size(); i++) {
            int couponID = coupons_num.get(i);
            Coupon coupon = ((CouponsDBDAO) (super.couponsDAO)).getOneCoupon(couponID);
            if (coupon.getCategory() == category) {
                coupons.add(coupon);
            }
        }
        return coupons;

    }
   public ArrayList<Coupon> getCustomerCoupons(double maxPrice) throws SQLException {

       ArrayList<Coupon> coupons = new ArrayList<>();
       List<Integer> coupons_num = ((CustomersDBDAO) (super.customersDAO)).getAllCouponsNumbers(this.getCustomerID());
       for (int i = 0; i < coupons_num.size(); i++) {
           int couponID = coupons_num.get(i);
           Coupon coupon = ((CouponsDBDAO) (super.couponsDAO)).getOneCoupon(couponID);
           if (coupon.getPrice() <= maxPrice) {
               coupons.add(coupon);
           }
       }
       return coupons;
   }
//
//    getCustomerDetails(): Customer

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}

package Facade;

import DBDAO.CompaniesDBDAO;
import DBDAO.CouponsDBDAO;
import DBDAO.CustomersDBDAO;
import Facade.ClientFacade;
import JavaBeans.Category;
import JavaBeans.Company;
import JavaBeans.Coupon;

import java.sql.SQLException;
import java.util.ArrayList;

public class CompanyFacede extends ClientFacade {
    private int companyID;


    public CompanyFacede(String email, String password) throws SQLException {
        if (login(email, password)) {
            super.customersDAO = new CustomersDBDAO();
            super.couponsDAO = new CouponsDBDAO();
            this.companyID = ((CompaniesDBDAO) (super.companiesDAO)).get_ID(email, password);
            System.out.println("LogIn Successfully :Company - " + getCompanyID());

        } else {
            throw new IllegalArgumentException("Invalid email or password");


        }
    }

    public int getCompanyID() {
        return companyID;
    }

    @Override
    public boolean login(String email, String password) throws SQLException {
        super.companiesDAO = new CompaniesDBDAO();
        return super.companiesDAO.isCompanyExists(email, password);
    }

    public void addCoupon(Coupon coupon) throws SQLException {

        if (this.companyID == coupon.getCompanyID()) {
            boolean flag = false;
            ArrayList<Coupon> coupons = ((CouponsDBDAO) (super.couponsDAO)).getAllCoupons(coupon.getCompanyID());
            for (int i = 0; i < coupons.size(); i++) {
                flag = coupons.get(i).getTitle() == coupon.getTitle();
            }
            if (!flag) {
                super.couponsDAO.addCoupon(coupon);
            } else {
                System.out.println("Error - this company have coupon with same title.");

            }
        } else {
            System.out.println("The coupon you want to add don't matches with the companyID.");
        }
    }

    public void updateCoupon(Coupon coupon) throws SQLException {
        super.couponsDAO.updateCoupon(coupon);
    }

    public void deleteCoupon(int couponID) {
        super.couponsDAO.deleteCoupon(couponID);
    }

    public ArrayList<Coupon> getCompanyCoupons() throws SQLException {
        return ((CouponsDBDAO) (super.couponsDAO)).getAllCoupons(this.getCompanyID());
    }

    public ArrayList<Coupon> getCompanyCoupons(Category category) throws SQLException {
        ArrayList<Coupon> allCoupons = getCompanyCoupons();
        ArrayList<Coupon> coupons_category = new ArrayList<>();
        for (Coupon coupon : allCoupons) {
            if (coupon.getCategory() == category) {
                coupons_category.add(coupon);
            }
        }
        return coupons_category;
    }


    public ArrayList<Coupon> getCompanyCoupons(double maxPrice) throws SQLException {
        ArrayList<Coupon> allCoupons = getCompanyCoupons();
        ArrayList<Coupon> coupons_category = new ArrayList<>();
        for (Coupon coupon : allCoupons) {
            if (coupon.getPrice() < maxPrice) {
                coupons_category.add(coupon);
            }
        }
        return coupons_category;
    }

    public Company getCompanyDetails() throws SQLException {
        return super.companiesDAO.getOneCompany(this.companyID);
    }
}


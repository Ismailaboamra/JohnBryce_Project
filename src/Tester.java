import CLS.ConnectionPool;
import DBDAO.*;
import Facade.Clients.AdminFacade;
import Facade.Clients.CompanyFacede;
import JavaBeans.Category;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Tester {
    public static void main(String[] args) throws InterruptedException, SQLException {

//        AdminFacade admin = new AdminFacade("admin@admin.com","admin");
        CompanyFacede companyFacede = new CompanyFacede("10","9");

//        CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
//        CustomersDBDAO customersDBDAO = new CustomersDBDAO();
//        CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
//        Category category = Category.Food;
//        Coupon coupon = new Coupon(2, 3, category, "2", "2", new Date(2, 2, 2), new Date(2, 2, 2), 2, 2.00, "2");
//        couponsDBDAO.addCoupon(coupon);
//        couponsDBDAO.updateCoupon(coupon);
//        couponsDBDAO.deleteCoupon(2);
//        System.out.println(couponsDBDAO.getAllCoupons());
//        System.out.println(couponsDBDAO.getOneCoupon(1));
//        couponsDBDAO.addCouponPurchase(1,1);
//        couponsDBDAO.deleteCouponPurchase(1,1);

//        ArrayList<Coupon> coupons = new ArrayList<>();
//        coupons.add(couponsDBDAO.getOneCoupon(1));
//        coupons.add(coupon);

//        Customer customer = new Customer(2,"update","update","update","2",coupons);
//        admin.addCustomer(customer);
//        admin.updateCustomer(customer);
//        admin.deleteCustomer(2);
//        System.out.println(admin.getAllCustomers());
//        System.out.println(admin.getOneCustomer(1));
//


//        admin.addCompany(company);
//        admin.updateCompany(company);
//        admin.deleteCompany(1);
//        System.out.println(admin.getAllCompanies());
//        System.out.println(admin.getOneCompany(1));




    }

}

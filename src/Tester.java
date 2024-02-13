import CLS.ConnectionPool;
import DBDAO.*;
import Facade.Clients.AdminFacade;
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

        AdminFacade admin = new AdminFacade("admin@admin.com","admin");

//        CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
//        CustomersDBDAO customersDBDAO = new CustomersDBDAO();
        CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
        Category category = Category.Electricity;
//        Coupon coupon = new Coupon(1, 1, category, "1", "update", new Date(1, 1, 1), new Date(2, 2, 2), 1, 2.00, "2");
//        couponsDBDAO.addCoupon(coupon);
//        couponsDBDAO.updateCoupon(coupon);
//        couponsDBDAO.deleteCoupon(2);
//        System.out.println(couponsDBDAO.getAllCoupons());
//        System.out.println(couponsDBDAO.getOneCoupon(1));
//        couponsDBDAO.addCouponPurchase(1,1);
//        couponsDBDAO.deleteCouponPurchase(1,1);

        ArrayList<Coupon> coupons = new ArrayList<>();
        coupons.add(couponsDBDAO.getOneCoupon(1));
        Customer customer = new Customer(2,"new","new","new@new","2",coupons);
        admin.addCustomer(customer);
//        customersDBDAO.updateCustomer(customer);
//        customersDBDAO.deleteCustomer(2);
//        System.out.println(customersDBDAO.getAllCustomers());
//        System.out.println(customersDBDAO.getOneCustomers(2));
//        companiesDBDAO.addCompany(new Company(2,"1","1","1",new ArrayList<>()));
//        Company company = new Company(5, "5", "8", "9", new ArrayList<>());


//        admin.addCompany(company);
//        admin.updateCompany(company);
//        admin.deleteCompany(1);
//        System.out.println(admin.getAllCompanies());
//        System.out.println(admin.getOneCompany(1));




    }

}

import DBDAO.*;
import Facade.Clients.CustomerFacade;
import JavaBeans.Category;
import JavaBeans.Coupon;

import java.sql.SQLException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Tester {
    public static void main(String[] args) throws InterruptedException, SQLException {

//        CustomersDBDAO customersDBDAO = new CustomersDBDAO();
//        CouponsDBDAO couponsDBDAO = new CouponsDBDAO();

//        AdminFacade admin = new AdminFacade("admin@admin.com","admin");
//        CompanyFacede companyFacede = new CompanyFacede("10","9");
        CustomerFacade customerFacade = new CustomerFacade("1","1");
//        customerFacade.purchaseCoupon(couponsDBDAO.getOneCoupon(4));
        System.out.println(customerFacade.getCustomerCoupons());
        System.out.println(customerFacade.getCustomerCoupons(Category.Food));
        System.out.println(customerFacade.getCustomerCoupons(1.0));










//        companyFacede.addCoupon(coupon);
//        companyFacede.updateCoupon(coupon);
//        companyFacede.deleteCoupon(3);
//        System.out.println(companyFacede.getCompanyCoupons());
//        System.out.println(companyFacede.getCompanyCoupons(Category.Food));
//        System.out.println(companyFacede.getCompanyCoupons(1.0));
//        System.out.println(companyFacede.getCompanyDetails());




//        CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
//        Category category = Category.Food;
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

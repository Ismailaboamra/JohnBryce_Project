import Clients.ClientType;
import Clients.LoginManager;
import DBDAO.CompaniesDBDAO;
import DBDAO.CouponsDBDAO;
import DBDAO.CustomersDBDAO;
import Facade.AdminFacade;
import Facade.CompanyFacede;
import Facade.CustomerFacade;
import Facade.CustomerFacade;
import JavaBeans.Category;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.Customer;
import Jobs.CouponExpirationDailyJob;

import javax.swing.plaf.IconUIResource;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws SQLException{
        testAll();
    }

    public static void testAll() throws SQLException {

        CouponExpirationDailyJob couponExpirationDailyJob =new CouponExpirationDailyJob();
        Thread job = new Thread(couponExpirationDailyJob);
        job.start();


        CustomersDBDAO customersDBDAO = new CustomersDBDAO();
        CouponsDBDAO couponsDBDAO = new CouponsDBDAO();

        AdminFacade admin = new AdminFacade("admin@admin.com","admin");
        CompanyFacede companyFacede = new CompanyFacede("1@1.com","111");
        LoginManager loginManager = LoginManager.getInstance();

        CompanyFacede companyFacade = (CompanyFacede) loginManager.login("1@1.com", "111", ClientType.Company);
        Coupon coupon1 = new Coupon(4,1,Category.Vacation,"4","new4",new Date(2024,1,1),new Date(2024,2,2),5,50.0,"3");
        companyFacade.addCoupon(coupon1);
//        customerFacade.purchaseCoupon(couponsDBDAO.getOneCoupon(4));
//        System.out.println(((CustomerFacade) (customerFacede)).getCustomerCoupons());
//
//        System.out.println(customerFacade.getCustomerCoupons(Category.Food));
//        System.out.println(customerFacade.getCustomerCoupons(1.0));


        companyFacede.addCoupon(coupon1);
        companyFacede.updateCoupon(coupon1);
        companyFacede.deleteCoupon(3);
        System.out.println(companyFacede.getCompanyCoupons());
        System.out.println(companyFacede.getCompanyCoupons(Category.Food));
        System.out.println(companyFacede.getCompanyCoupons(1.0));
        System.out.println(companyFacede.getCompanyDetails());


        CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
        Category category = Category.Food;
        couponsDBDAO.addCoupon(coupon1);
        couponsDBDAO.updateCoupon(coupon1);
        couponsDBDAO.deleteCoupon(2);
        System.out.println(couponsDBDAO.getAllCoupons());
        System.out.println(couponsDBDAO.getOneCoupon(1));
        couponsDBDAO.addCouponPurchase(1,1);
        couponsDBDAO.deleteCouponPurchase(1,1);

        ArrayList<Coupon> coupons = new ArrayList<>();
        coupons.add(couponsDBDAO.getOneCoupon(1));
        coupons.add(coupon1);

        Customer customer = new Customer(2,"update","update","update","2",coupons);
        admin.addCustomer(customer);
        admin.updateCustomer(customer);
        admin.deleteCustomer(2);
        System.out.println(admin.getAllCustomers());
        System.out.println(admin.getOneCustomer(1));
//


        Company company=new Company(2,"isa","as","123",coupons);
        admin.addCompany(company);
        admin.updateCompany(company);
        admin.deleteCompany(1);
        System.out.println(admin.getAllCompanies());
        System.out.println(admin.getOneCompany(1));


    }
}

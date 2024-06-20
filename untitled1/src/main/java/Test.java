import Spring.Clients.LoginManager;

import Spring.Jobs.CouponExpirationDailyJob;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import Spring.DBDAO.*;
import Spring.entities.Coupon;
import Spring.entities.Customer;
import Spring.exceptions.CompanyNotFoundException;
import Spring.exceptions.CouponExistException;
import Spring.exceptions.CouponNotFoundException;
import Spring.exceptions.CouponsNotFoundException;
import Spring.facades.AdminFacade;
import Spring.facades.CompanyFacade;
import Spring.enums.*;

public class Test {
    public static void main(String[] args) throws SQLException, CouponsNotFoundException, CompanyNotFoundException, CouponExistException, CouponNotFoundException {
        testAll();
    }

    public static void testAll() throws SQLException, CompanyNotFoundException, CouponExistException, CouponNotFoundException, CouponsNotFoundException {

        CouponExpirationDailyJob couponExpirationDailyJob =new CouponExpirationDailyJob();
        Thread job = new Thread(couponExpirationDailyJob);
        job.start();


        CustomerDBDAO customersDBDAO = new CustomerDBDAO();
        CouponDBDAO couponsDBDAO = new CouponDBDAO();

        AdminFacade admin = new AdminFacade("admin@admin.com","admin");
        CompanyFacade companyFacede = new CompanyFacade();
        LoginManager loginManager = LoginManager.getInstance();

        CompanyFacade companyFacade = (CompanyFacade) loginManager.login("2@2.com", "222", ClientType.COMPANY);
        Coupon coupon1 = new Coupon("4",new Date(2024,1,1),new Date(2024,2,2),5,CouponType.CAMPING,"",50.0,"");
        companyFacade.createCoupon(coupon1);
//        customerFacade.purchaseCoupon(couponsDBDAO.getOneCoupon(4));
//        System.out.println(((CustomerFacade) (customerFacede)).getCustomerCoupons());
//
//        System.out.println(customerFacade.getCustomerCoupons(Category.Food));
//        System.out.println(customerFacade.getCustomerCoupons(1.0));


        companyFacede.createCoupon(coupon1);
        companyFacede.updateCoupon(coupon1.getEndDate(), coupon1.getPrice(), coupon1.getId());
        companyFacede.removeCoupon(3);
        System.out.println(companyFacede.getCompanyCoupons());
//        System.out.println(companyFacede.getCompanyCoupons(CouponType.FOOD));
//        System.out.println(companyFacede.getCompanyCoupons(1));
        System.out.println(companyFacede.toString());


        CompanyDBDAO companiesDBDAO = new CompanyDBDAO();
        CouponType category = CouponType.FOOD;
        couponsDBDAO.createCoupon(coupon1,1);
        couponsDBDAO.updateCoupon(coupon1.getEndDate(), coupon1.getPrice(), coupon1.getId(),1);
        couponsDBDAO.removeCompanyCoupons(2);
        System.out.println(couponsDBDAO.getAllCoupons());
        System.out.println(couponsDBDAO.getCoupon(1));
//        couponsDBDAO.addCouponPurchase(1,1);
//        couponsDBDAO.deleteCouponPurchase(1,1);

        ArrayList<Coupon> coupons = new ArrayList<>();
        coupons.add(couponsDBDAO.getCoupon(1));
        coupons.add(coupon1);

//        Customer customer = new Customer(2,"update","update","update","2",coupons);
//        admin.addCustomer(customer);
//        admin.updateCustomer(customer);
//        admin.deleteCustomer(2);
//        System.out.println(admin.getAllCustomers());
//        System.out.println(admin.getOneCustomer(1));
////
//
//
//        Company company=new Company(2,"isa","as","123",coupons);
//        admin.addCompany(company);
//        admin.updateCompany(company);
//        admin.deleteCompany(1);
//        System.out.println(admin.getAllCompanies());
//        System.out.println(admin.getOneCompany(1));


    }
}

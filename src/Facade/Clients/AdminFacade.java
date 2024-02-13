package Facade.Clients;

import DBDAO.CompaniesDBDAO;
import DBDAO.CouponsDBDAO;
import DBDAO.CustomersDBDAO;
import Facade.ClientFacade;
import JavaBeans.Company;
import JavaBeans.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminFacade extends ClientFacade {


    public AdminFacade(String email, String Password) {
        if (login(email, Password)) {
            System.out.println("LogIn Successfully.");
            super.companiesDAO = new CompaniesDBDAO();
            super.customersDAO = new CustomersDBDAO();
            super.couponsDAO = new CouponsDBDAO();
        } else {
            throw new IllegalArgumentException("Invalid email or password");


        }

    }

    @Override
    public boolean login(String email, String password) {
        return (email.equals("admin@admin.com")) && (password.equals("admin"));
    }

    public void addCompany(Company company) throws SQLException {
        super.companiesDAO.addCompany(company);
    }

    public void updateCompany(Company company) throws SQLException {
        super.companiesDAO.updateCompany(company);
    }

    public void deleteCompany(int companyID) throws SQLException {
        super.companiesDAO.deleteCompany(companyID);
    }

    public ArrayList<Company> getAllCompanies() throws SQLException {
        return super.companiesDAO.getAllCompanies();
    }

    public Company getOneCompany(int companyID) throws SQLException {
        return super.companiesDAO.getOneCompany(companyID);
    }

    public void addCustomer(Customer customer) throws SQLException {
        super.customersDAO.addCustomer(customer);
    }
//+ update Customer(Customer customer)
//+ deleteCustomer(int customerID)
//+ getAllCustomers(): ArrayList<Customer>
//+ getOneCustomer(int customerlD): Customer
}

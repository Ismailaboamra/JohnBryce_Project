package Facade.Clients;

import DBDAO.CompaniesDBDAO;
import DBDAO.CouponsDBDAO;
import DBDAO.CustomersDBDAO;
import Facade.ClientFacade;

import java.sql.SQLException;

public class CompanyFacede extends ClientFacade {
    private int companyID;



    public CompanyFacede(String email, String password) throws SQLException {
        if (login(email, password)) {
            super.customersDAO = new CustomersDBDAO();
            super.couponsDAO = new CouponsDBDAO();
            this.companyID = ((CompaniesDBDAO)(super.companiesDAO)).get_ID(email,password);
            System.out.println("LogIn Successfully :Company - "+getCompanyID());

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
        return super.companiesDAO.isCompanyExists(email,password);
    }
}

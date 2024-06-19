package Spring.Facade;

import Spring.DAO.CompaniesDAO;
import Spring.DAO.CouponsDAO;
import Spring.DAO.CustomersDAO;

import java.sql.SQLException;

public abstract class ClientFacade {
    protected CompaniesDAO companiesDAO;
    protected CustomersDAO customersDAO;
    protected CouponsDAO couponsDAO;

    public abstract boolean login(String email,String password) throws SQLException;

}

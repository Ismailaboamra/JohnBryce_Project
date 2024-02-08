package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import JavaBeans.*;

public interface CompaniesDAO {
    boolean isCompanyExists(String email, String password) throws SQLException;

    void addCompany(Company company);

    void updateCompany(Company company);

    void deleteCompany(int companyID);

    ArrayList<Company> getAllCompanies();

    Company getOneCompany(int companyID);
}

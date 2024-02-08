import CLS.ConnectionPool;
import DBDAO.*;
import JavaBeans.Company;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Tester {
    public static void main(String[] args) throws InterruptedException {
        CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
        try {
            System.out.println(companiesDBDAO.isCompanyExists("vm@vm.com","123"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        companiesDBDAO.addCompany(new Company(3,"1","1","1",new ArrayList<>()));

        }

    }

import CLS.ConnectionPool;
import DBDAO.*;
import JavaBeans.Company;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Tester {
    public static void main(String[] args) throws InterruptedException, SQLException {
        CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
        try {
            System.out.println(companiesDBDAO.isCompanyExists("vm@vm.com","123"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        companiesDBDAO.addCompany(new Company(2,"1","1","1",new ArrayList<>()));
        Company company = new Company(2,"9","10","9",new ArrayList<>());
//        companiesDBDAO.updateCompany(company);
//        companiesDBDAO.deleteCompany(3);
        System.out.println(companiesDBDAO.getAllCompanies());
        System.out.println(companiesDBDAO.getOneCompany(2));

//        try {
//            if (companiesDBDAO.isCompanyExists(company.getEmail(),company.getPassword())){
//                System.out.println(" the Company : "+company.getEmail()+" is Exists.");
//            }else {
//                System.out.println("the Company : " +company.getEmail()+ " is  not Exists.");
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }


    }

    }

package Spring.DBDAO;


import Spring.JavaBeans.Company;
import Spring.JavaBeans.Coupon;
import Spring.CLS.ConnectionPool;
import Spring.CLS.DBtools;
import Spring.DAO.CompaniesDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CompaniesDBDAO implements CompaniesDAO {

    private ConnectionPool connectionPool;


    public int get_ID(String email, String password) throws SQLException {
        final String EXISTS = "SELECT * FROM `jb_project`.`companies`;";
        Map<Integer, Object> params = new HashMap<>();
        ResultSet result = DBtools.runQueryForResult(EXISTS, params);

        while (result.next()) {
            int ID = result.getInt(1);
            String email_ = result.getNString(3);
            String pass_ = result.getNString(4);

            if (email.equals(email_) && password.equals(pass_)) {
                return ID;
            }
        }
        return -1;
    }

    @Override
    public boolean isCompanyExists(String email, String password) throws SQLException {
        boolean flag = false;
        final String EXISTS = "SELECT * FROM `jb_project`.`companies`;";
        Map<Integer, Object> params = new HashMap<>();
        ResultSet result = DBtools.runQueryForResult(EXISTS, params);

        while (result.next()) {
            String email_ = result.getNString(3);
            String pass_ = result.getNString(4);

            if (email.equals(email_) && password.equals(pass_)) {
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public void addCompany(Company company) throws SQLException {

        if (checkNameIsExists(company.getName())) {
            System.out.println(" company name already exists.");
        } else if (checkEmailIsExists(company.getEmail())) {
            System.out.println(" company email already exists.");
        } else {
            final String ADD_COMPANY = "INSERT INTO `jb_project`.`companies` (`id`, `NAME`, `EMAIL`, `PASSWORD`) VALUES (?,?,?,?);";
            Map<Integer, Object> params = new HashMap<>();
            params.put(1, company.getID());
            params.put(2, company.getName());
            params.put(3, company.getEmail());
            params.put(4, company.getPassword());
            boolean flag = DBtools.runQuery(ADD_COMPANY, params);
            if (flag)
                System.out.println("The Company has been added successfully.");
            else
                System.out.println("ERROR :The Company not added.");
        }


    }

    @Override
    public void updateCompany(Company company) throws SQLException {


        if (checkIdIsExists(company.getID()) && checkNameIsExists(company.getName())) {
            final int id = company.getID();
            final String name = company.getName();
            final String UPDATE = "UPDATE `jb_project`.`companies` SET `EMAIL` = ?, `PASSWORD` = ? WHERE `id` = " + id + ";";
            Map<Integer, Object> params = new HashMap<>();

            params.put(1, company.getEmail());
            params.put(2, company.getPassword());


            boolean flag = DBtools.runQuery(UPDATE, params);
            if (flag) {
                System.out.println("The Company has been Updated successfully.");
            } else {
                System.out.println("Field Updated company.");

            }
        } else {
            System.out.println("The Company not exists.");
        }


    }

    @Override
    public void deleteCompany(int companyID) throws SQLException {
        if (checkIdIsExists(companyID)) {
            final String DELETE_COMPANY = "DELETE FROM `jb_project`.`companies` WHERE id = " + companyID + ";";
            final String DELETE_COUPONS = "DELETE FROM `jb_project`.`coupons` WHERE COMPANY_ID = " + companyID + ";";
            boolean deleteCoupons = DBtools.runQuery(DELETE_COUPONS);
            boolean deleteCompany = DBtools.runQuery(DELETE_COMPANY);
            if (deleteCompany && deleteCoupons)
                System.out.println("The Company deleted successfully.");
            else
                System.out.println("ERROR ,the company not deleted.");
        } else {
            System.out.println("The Company not exists.");
        }

    }

    @Override
    public ArrayList<Company> getAllCompanies() throws SQLException {
        final String ALL_COMPANIES = "SELECT * FROM `jb_project`.`companies`;";
        Map<Integer, Object> params = new HashMap<>();
        ResultSet resultSet = DBtools.runQueryForResult(ALL_COMPANIES, params);
        ArrayList<Company> companies = new ArrayList<>();
        while (resultSet.next()) {
            int ID = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String email = resultSet.getString(3);
            String pass = resultSet.getString(4);
            ArrayList<Coupon> coupons = (new CouponsDBDAO()).getAllCouponsWithSameCompanyID(ID);

            companies.add(new Company(ID, name, email, pass, coupons));
        }
        return companies;

    }

    @Override
    public Company getOneCompany(int companyID) throws SQLException {
        if (checkIdIsExists(companyID)) {
            final String ONE_COMPANIES = "SELECT * FROM `jb_project`.`companies` WHERE id = " + companyID + ";";
            Map<Integer, Object> params = new HashMap<>();
            ResultSet resultSet = DBtools.runQueryForResult(ONE_COMPANIES, params);
            while (resultSet.next()) {

                int ID = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                String pass = resultSet.getString(4);
                ArrayList<Coupon> coupons = (new CouponsDBDAO()).getAllCouponsWithSameCompanyID(ID);
                return new Company(ID, name, email, pass, coupons);
            }
            return null;
        } else {
            System.out.println("The Company not exists.");
            return null;
        }
    }

    public boolean checkNameIsExists(String name) throws SQLException {
        // check if the Name o is EXISTS;
        boolean checkName = false;
        final String CHECK = "SELECT * FROM `jb_project`.`companies`;";
        Map<Integer, Object> params = new HashMap<>();
        ResultSet result = DBtools.runQueryForResult(CHECK, params);
        while (result.next()) {
            String name_ = result.getNString(2);
            if (name.equals(name_))
                return true;
        }
        return false;

    }

    public boolean checkIdIsExists(int id) throws SQLException {
        // check if the ID o is EXISTS;
        boolean checkID = false;
        final String CHECK = "SELECT * FROM `jb_project`.`companies`;";
        Map<Integer, Object> params = new HashMap<>();
        ResultSet result = DBtools.runQueryForResult(CHECK, params);
        while (result.next()) {
            int id_ = result.getInt(1);
            if (id == id_)
                return true;
        }
        return false;

    }

    public boolean checkEmailIsExists(String email) throws SQLException {
        // check if the Email o is EXISTS;
        boolean checkEmail = false;
        final String CHECK = "SELECT * FROM `jb_project`.`companies`;";
        Map<Integer, Object> params = new HashMap<>();
        ResultSet result = DBtools.runQueryForResult(CHECK, params);
        while (result.next()) {
            String email_ = result.getNString(3);
            if (email.equals(email_))
                return true;
        }
        return false;

    }



}

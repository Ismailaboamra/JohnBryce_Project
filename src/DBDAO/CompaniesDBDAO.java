package DBDAO;

import CLS.*;
import DAO.*;
import JavaBeans.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompaniesDBDAO implements CompaniesDAO {

    private ConnectionPool connectionPool;


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
    public void addCompany(Company company) {
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

    @Override
    public void updateCompany(Company company) throws SQLException {

        if (isCompanyExists(company.getEmail(), company.getPassword())) {
            final String id = String.valueOf(company.getID());
            final String UPDATE = "UPDATE `jb_project`.`companies` SET `id` = ?, `NAME` = ?, `EMAIL` = ?, `PASSWORD` = ? WHERE (`id` = " + id + ");";
            Map<Integer, Object> params = new HashMap<>();
            params.put(1, company.getID());
            params.put(2, company.getName());
            params.put(3, company.getEmail());
            params.put(4, company.getPassword());
            DBtools.runQuery(UPDATE, params);
            System.out.println("The Company has been Updated successfully.");
        } else {
            System.out.println("The Company not exists.");
        }


    }

    @Override
    public void deleteCompany(int companyID) {
        final String DELETE = "DELETE FROM `jb_project`.`companies` WHERE id = " + companyID + ";";
        boolean flag = DBtools.runQuery(DELETE);
        if (flag)
            System.out.println("The Company deleted successfully.");
        else
            System.out.println("ERROR ,the company not deleted.");

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
            companies.add(new Company(ID, name, email, pass, new ArrayList<>()));
        }
        return companies;

    }

    @Override
    public Company getOneCompany(int companyID) throws SQLException {
        final String ONE_COMPANIES = "SELECT * FROM `jb_project`.`companies` WHERE id = " + companyID + ";";
        Map<Integer, Object> params = new HashMap<>();
        ResultSet resultSet = DBtools.runQueryForResult(ONE_COMPANIES, params);
        while (resultSet.next()) {

            int ID = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String email = resultSet.getString(3);
            String pass = resultSet.getString(4);
            return new Company(ID, name, email, pass, new ArrayList<>());
        }
        return null;
    }


}

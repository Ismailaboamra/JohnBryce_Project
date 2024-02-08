package DBDAO;

import CLS.*;
import DAO.*;
import JavaBeans.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompaniesDBDAO implements CompaniesDAO {

    ConnectionPool connectionPool;


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
        final String ADD_COMPANY = "INSERT INTO `jb_project`.`companies` (`id`, `NAME`, `EMAIL`, `PASSWORD`) VALUES ('?', '?', '?', '?');";
//        Map<Integer,Object> params = new HashMap<>();
//        params.put(1,company.getID());
//        params.put(2,company.getName());
//        params.put(3,company.getEmail());
//        params.put(4,company.getPassword());
//
//         DBtools.runQuery(ADD_COMPANY,params);
////        if (flag)
////            System.out.println("The Company has benn added successfully.");
////        else
////            System.out.println("ERROR :The Company not added.");

        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_COMPANY);

            preparedStatement.setInt(1, company.getID());
            preparedStatement.setString(2,company.getName());
            preparedStatement.setString(3,company.getEmail() );
            preparedStatement.setString(4,company.getPassword() );

            preparedStatement.execute();
            System.out.println("The Company has benn added successfully.");


        } catch (InterruptedException | SQLException e) {
//            throw new RuntimeException(e);
            System.out.println(e.getMessage());
            System.out.println("ERROR :The Company not added.");
        } finally {
            ConnectionPool.getInstance().restoreConnection(connection);
        }


    }

    @Override
    public void updateCompany(Company company) {

    }

    @Override
    public void deleteCompany(int companyID) {

    }

    @Override
    public ArrayList<Company> getAllCompanies() {
        return null;
    }

    @Override
    public Company getOneCompany(int companyID) {
        return null;
    }
}

package DBDAO;

import CLS.ConnectionPool;
import CLS.DBtools;
import DAO.CustomersDAO;
import JavaBeans.Company;
import JavaBeans.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomersDBDAO implements CustomersDAO{
   private ConnectionPool connectionPool;

    @Override
    public boolean isCustomerExists(String email, String password) throws SQLException {
        boolean flag = false;
        final String EXISTS = "SELECT * FROM `jb_project`.`customers`;";
        Map<Integer, Object> params = new HashMap<>();
        ResultSet result = DBtools.runQueryForResult(EXISTS, params);

        while (result.next()) {
            String email_ = result.getNString(4);
            String pass_ = result.getNString(5);

            if (email.equals(email_) && password.equals(pass_)) {
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public void addCustomer(Customer customer) {
        final String ADD_CUSTOMER = "INSERT INTO `jb_project`.`customers` (`id`, `FIRST_NAME`,`LAST_NAME`, `EMAIL`, `PASSWORD`) VALUES (?,?,?,?,?);";
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customer.getId());
        params.put(2, customer.getFirstName());
        params.put(3, customer.getLastName());
        params.put(4, customer.getEmail());
        params.put(5, customer.getPassword());

        boolean flag = DBtools.runQuery(ADD_CUSTOMER, params);
        if (flag)
            System.out.println("The Customer has been added successfully.");
        else
            System.out.println("ERROR :The Customer not added.");


    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        if (isCustomerExists(customer.getEmail(), customer.getPassword())) {
            final String id = String.valueOf(customer.getId());
            final String UPDATE = "UPDATE `jb_project`.`customers` SET `id` = ?, `FIRST_NAME` = ?,`LAST_NAME` = ?, `EMAIL` = ?, `PASSWORD` = ? WHERE (`id` = " + id + ");";
            Map<Integer, Object> params = new HashMap<>();
            params.put(1, customer.getId());
            params.put(2, customer.getFirstName());
            params.put(3, customer.getLastName());
            params.put(4, customer.getEmail());
            params.put(5, customer.getPassword());
            DBtools.runQuery(UPDATE, params);
            System.out.println("The Customer has been Updated successfully.");
        } else {
            System.out.println("The Customer not exists.");
        }


    }

    @Override
    public void deleteCustomer(int customerID) {
        final String DELETE = "DELETE FROM `jb_project`.`customers` WHERE id = " + customerID + ";";
        boolean flag = DBtools.runQuery(DELETE);
        if (flag)
            System.out.println("The Customer deleted successfully.");
        else
            System.out.println("ERROR ,the Customer not deleted.");

    }

    @Override
    public ArrayList<Customer> getAllCustomers() throws SQLException {
        final String ALL_CUSTOMERS = "SELECT * FROM `jb_project`.`customers`;";
        Map<Integer, Object> params = new HashMap<>();
        ResultSet resultSet = DBtools.runQueryForResult(ALL_CUSTOMERS, params);
        ArrayList<Customer> customers = new ArrayList<>();
        while (resultSet.next()) {
            int ID = resultSet.getInt(1);
            String first_name = resultSet.getString(2);
            String last_name = resultSet.getString(3);
            String email = resultSet.getString(4);
            String pass = resultSet.getString(5);
            customers.add(new Customer(ID, first_name,last_name, email, pass, new ArrayList<>()));
        }
        return customers;    }

    @Override
    public Customer getOneCustomers(int customerID) throws SQLException {
        final String ONE_CUSTOMER = "SELECT * FROM `jb_project`.`customers` WHERE id = " + customerID + ";";
        Map<Integer, Object> params = new HashMap<>();
        ResultSet resultSet = DBtools.runQueryForResult(ONE_CUSTOMER, params);
        while (resultSet.next()) {

            int ID = resultSet.getInt(1);
            String first_name = resultSet.getString(2);
            String last_name = resultSet.getString(3);
            String email = resultSet.getString(4);
            String pass = resultSet.getString(5);
            return new Customer(ID, first_name,last_name, email, pass, new ArrayList<>());

        }
        return null;    }
}

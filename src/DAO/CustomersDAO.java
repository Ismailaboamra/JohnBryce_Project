package DAO;

import JavaBeans.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomersDAO {
    boolean isCustomerExists(String email, String password) throws SQLException;

    void addCustomer(Customer customer) throws SQLException;

    void updateCustomer(Customer customer) throws SQLException;

    void deleteCustomer(int customerID) throws SQLException;

    ArrayList<Customer> getAllCustomers() throws SQLException;

    Customer getOneCustomers(int customerID) throws SQLException;
}

package Spring.DAO;

import Spring.JavaBeans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public interface CustomersDAO extends JpaRepository<Customer,Long> {
    boolean isCustomerExists(String email, String password) throws SQLException;

    void addCustomer(Customer customer) throws SQLException;

    void updateCustomer(Customer customer) throws SQLException;

    void deleteCustomer(int customerID) throws SQLException;

    ArrayList<Customer> getAllCustomers() throws SQLException;

    Customer getOneCustomers(int customerID) throws SQLException;
}

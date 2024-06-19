package Spring.DBDAO;

import Spring.CLS.ConnectionPool;
import Spring.CLS.DBtools;
import Spring.DAO.CustomersDAO;
import Spring.JavaBeans.Coupon;
import Spring.JavaBeans.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomersDBDAO implements CustomersDAO {
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
    public void addCustomer(Customer customer) throws SQLException {
        if (checkEmailIsExists1(customer.getEmail())) {
            final String ADD_CUSTOMER = "INSERT INTO `jb_project`.`customers` (`id`, `FIRST_NAME`,`LAST_NAME`, `EMAIL`, `PASSWORD`) VALUES (?,?,?,?,?);";
            final String ADD_COUPONS = "INSERT INTO `jb_project`.`customers_vs_coupons` (`CUSTOMER_ID`, `COUPON_ID`) VALUES (?, ?);";
            Map<Integer, Object> params1 = new HashMap<>();
            Map<Integer, Object> params2 = new HashMap<>();

            params1.put(1, customer.getId());
            params1.put(2, customer.getFirstName());
            params1.put(3, customer.getLastName());
            params1.put(4, customer.getEmail());
            params1.put(5, customer.getPassword());

            ArrayList<Coupon> coupons = customer.getCoupons();
            for (int i = 0; i < coupons.size(); i++) {
                params2.put(1, customer.getId());
                params2.put(2, coupons.get(i).getId());
            }

            boolean add_customer = DBtools.runQuery(ADD_CUSTOMER, params1);
            boolean add_coupon = DBtools.runQuery(ADD_COUPONS, params2);


            if (add_customer && add_coupon)
                System.out.println("The Customer has been added successfully.");
            else
                System.out.println("ERROR :The Customer not added.");
        } else {
            System.out.println("The Customer is exists");

        }


    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        if (checkIdIsExists(customer.getId())) {
            final String id = String.valueOf(customer.getId());
            final String UPDATE = "UPDATE `jb_project`.`customers` SET  `FIRST_NAME` = ?,`LAST_NAME` = ?, `EMAIL` = ?, `PASSWORD` = ? WHERE (`id` = " + id + ");";
            final String ADD_COUPONS = "INSERT INTO `jb_project`.`customers_vs_coupons` (`CUSTOMER_ID`, `COUPON_ID`) VALUES (?, ?);";
            Map<Integer, Object> params1 = new HashMap<>();
            Map<Integer, Object> params2 = new HashMap<>();
            params1.put(1, customer.getFirstName());
            params1.put(2, customer.getLastName());
            params1.put(3, customer.getEmail());
            params1.put(4, customer.getPassword());
            boolean update_customer = DBtools.runQuery(UPDATE, params1);

            ArrayList<Coupon> coupons = customer.getCoupons();
            for (int i = 0; i < coupons.size(); i++) {
                if (!checkCustomers_vs_coupons(customer.getId(), coupons.get(i).getId())) {
                    params2.put(1, customer.getId());
                    params2.put(2, coupons.get(i).getId());
                }
            }
            boolean update_cupon = DBtools.runQuery(ADD_COUPONS, params2);
            if (update_customer) {
                System.out.println("The Customer has been Updated successfully.");
            } else {
                System.out.println("Error :The Customer not Updated ");

            }
        } else {
            System.out.println("The Customer not exists.");
        }


    }

    @Override
    public void deleteCustomer(int customerID) throws SQLException {
        if (checkIdIsExists(customerID)) {
            final String DELETE_CUSTOMER = "DELETE FROM `jb_project`.`customers` WHERE id = " + customerID + ";";
            boolean delete_customer = DBtools.runQuery(DELETE_CUSTOMER);
            final String DELETE_Customers_vs_coupons = "DELETE FROM `jb_project`.`customers_vs_coupons` WHERE CUSTOMER_ID = " + customerID + ";";
            boolean delete_Customers_vs_coupons = DBtools.runQuery(DELETE_Customers_vs_coupons);
            if (delete_customer && delete_Customers_vs_coupons)
                System.out.println("The Customer deleted successfully.");
            else
                System.out.println("ERROR ,the Customer not deleted.");
        } else {
            System.out.println("The Customer not exists.");
        }

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
            ArrayList<Coupon> coupons = (new CouponsDBDAO()).getAllCouponsWithSameCompanyID(ID);
            customers.add(new Customer(ID, first_name, last_name, email, pass, coupons));
        }
        return customers;
    }

    @Override
    public Customer getOneCustomers(int customerID) throws SQLException {
        if (checkIdIsExists(customerID)) {
            final String ONE_CUSTOMER = "SELECT * FROM `jb_project`.`customers` WHERE id = " + customerID + ";";
            Map<Integer, Object> params = new HashMap<>();
            ResultSet resultSet = DBtools.runQueryForResult(ONE_CUSTOMER, params);
            while (resultSet.next()) {

                int ID = resultSet.getInt(1);
                String first_name = resultSet.getString(2);
                String last_name = resultSet.getString(3);
                String email = resultSet.getString(4);
                String pass = resultSet.getString(5);
                ArrayList<Coupon> coupons = (new CouponsDBDAO()).getAllCouponsWithSameCompanyID(ID);
                return new Customer(ID, first_name, last_name, email, pass, coupons);

            }
        } else {
            System.out.println("The Customer not exists.");

        }
        return null;

    }

    public boolean checkIdIsExists(int id) throws SQLException {
        // check if the ID o is EXISTS;
        boolean checkID = false;
        final String CHECK = "SELECT * FROM `jb_project`.`customers`;";
        Map<Integer, Object> params = new HashMap<>();
        ResultSet result = DBtools.runQueryForResult(CHECK, params);
        while (result.next()) {
            int id_ = result.getInt(1);
            if (id == id_)
                return true;
        }
        return false;

    }

    public boolean checkEmailIsExists1(String email) throws SQLException {
        // check if the Email o is EXISTS;
        boolean checkEmail = false;
        final String CHECK = "SELECT * FROM jb_project.customers;";
        Map<Integer, Object> params = new HashMap<>();
        ResultSet result = DBtools.runQueryForResult(CHECK, params);
        while (result.next()) {
            String email_ = result.getNString(4);
            if (email == email_)
                return true;
        }
        return false;

    }

    public boolean checkCustomers_vs_coupons(int customerID, int couponID) throws SQLException {
        boolean checkID = false;
        final String CHECK = "SELECT * FROM `jb_project`.`customers_vs_coupons`;";
        Map<Integer, Object> params = new HashMap<>();
        ResultSet result = DBtools.runQueryForResult(CHECK, params);
        while (result.next()) {
            int customerID_ = result.getInt(1);
            int cupon_ID_ = result.getInt(2);
            if (customerID == customerID_ && couponID == cupon_ID_)
                return true;
        }
        return false;
    }
    public List<Integer> getAllCouponsNumbers(int customerID) throws SQLException {
        List<Integer> couponsNums = new ArrayList<>();

        final String SELECT = "SELECT * FROM `jb_project`.`customers_vs_coupons` WHERE CUSTOMER_ID= "+customerID+";";
        Map<Integer, Object> params = new HashMap<>();
        ResultSet resultSet = DBtools.runQueryForResult(SELECT,params);
        while (resultSet.next()){
            int couponID = resultSet.getInt(2);
            couponsNums.add(couponID);
        }
        return couponsNums;
    }

    public int get_ID(String email, String password) throws SQLException {
        final String EXISTS = "SELECT * FROM `jb_project`.`customers`;";
        Map<Integer, Object> params = new HashMap<>();
        ResultSet result = DBtools.runQueryForResult(EXISTS, params);

        while (result.next()) {
            int ID = result.getInt(1);
            String email_ = result.getNString(4);
            String pass_ = result.getNString(5);

            if (email.equals(email_) && password.equals(pass_)) {
                return ID;
            }
        }
        return -1;
    }
}

package Spring.DAO;

import java.util.ArrayList;
import java.util.Optional;

import Spring.entities.Coupon;
import Spring.entities.Customer;
import Spring.exceptions.*;
import org.springframework.stereotype.Service;



@Service 
public interface CustomerDAO {
	
	
	void createCustomer(Customer customer) throws CustomerExistException;
	
	void removeCustomer(int customerId) throws CustomerNotFoundException; 
	
	void updateCustomer(String password , int customerId) throws CustomerNotFoundException;
	
	Optional<Customer> getCustomer(int customerId) throws CustomerNotFoundException;
	
	ArrayList<Customer> getAllCustoemr() throws CustomersNotFoundException; 
	
	ArrayList<Coupon> getCoupons(int customerId) throws CouponsNotFoundException , CustomerNotFoundException ;
	
	boolean login(String custName , String password);
	
	
	
	
	
	

}

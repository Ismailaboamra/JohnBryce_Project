/**
 * 
 */
package Spring.facades;

import java.util.ArrayList;
import java.util.Optional;

import Spring.DBDAO.CompanyDBDAO;
import Spring.DBDAO.CouponDBDAO;
import Spring.DBDAO.CustomerDBDAO;
import Spring.entities.Company;
import Spring.entities.Customer;
import Spring.enums.ClientType;
import Spring.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



import lombok.ToString;

/**
 * @author ismael
 * @author saber
 * @author bahaa
 *
 */
@ToString
@Component
public class AdminFacade implements CouponClientFacade {
	// Object's members
	@Autowired
	private CompanyDBDAO compdb;

	@Autowired
	private CouponDBDAO coupdb;
	
	@Autowired
	private CustomerDBDAO custdb;
	
	// Name & Password
	private final String NAME = "admin";
	private final String PASSWORD = "1234";

	public AdminFacade(String mail, String admin) {

	}

	/***
	 * Login method for ADMIN
	 */
	@Override
	public AdminFacade login(String name, String password, ClientType type) {

		// Checking type
		if (!type.equals(ClientType.ADMIN)) {
			return null;
		}

		// Checking name & password
		String que = name + password;
		String ans = NAME + PASSWORD;

		if (!que.equals(ans)) {
			return null;
		}

		// If success
		return this;

	}

	// ------Company------

	/***
	 * Creating new Company if its not exist
	 * 
	 * @param company
	 * @throws CompanyExistException
	 */
	public synchronized void createCompany(Company company) throws CompanyExistException {
		Company check = compdb.getCompanyByName(company.getCompName());

		if (check != null) {
			throw new CompanyExistException("Company " + company.getCompName() + " allready exist");
		}

		compdb.createCompany(company);
	}

	/***
	 * Removing Company
	 * 
	 * @param compId
	 * @throws CompanyNotFoundException
	 * 
	 */
	public void removeCompany(int compId) throws CompanyNotFoundException {
		Optional<Company> check = compdb.getCompany(compId);

		if (check == null) {
			throw new CompanyNotFoundException("Company with the ID:" + compId + " not exist");
		}

		coupdb.removeCompanyCoupons(compId);
		compdb.removeCompany(compId);
	}

	/***
	 * Updating Company set only password & email
	 * @param password
	 * @param email
	 * @param companyId
	 * @throws CompanyNotFoundException
	 */
	public void updateCompany(String password, String email, int companyId) throws CompanyNotFoundException {
		
		Optional<Company> check = compdb.getCompany(companyId);

		if (check == null) {
			throw new CompanyNotFoundException("Company with the ID:" + companyId + " not exist");
		}
		
		compdb.updateCompany(password, email, companyId);

	}
	
	/***
	 * Get Company by ID
	 * @param companyId
	 * @return Company
	 * @throws CompanyNotFoundException
	 */
	public Optional<Company> getCompany(int companyId) throws CompanyNotFoundException{
		
		Optional<Company> check = compdb.getCompany(companyId);

		if (check == null) {
			throw new CompanyNotFoundException("Company with the ID:" + companyId + " not exist");
		}
		
		return check;
		
	}
	
	
	/**
	 * Get all Companies from DB
	 * @return ArrayList
	 * @throws CompaniesNotFoundException
	 */
	public ArrayList<Company> getAllCompnaies() throws CompaniesNotFoundException{
		
		ArrayList<Company> allCompanies = compdb.getAllCompanies();
		
		if (allCompanies == null) {
			throw new CompaniesNotFoundException("There is no Companies in the DB yet ");
		}
		
		return allCompanies;
	}
	
	//-------Customer---------
	
	/***
	 * Creating Customer if its not exist
	 * @param customer
	 * @throws CustomerExistException
	 */
	public void createCustomer(Customer customer) throws CustomerExistException {

		Customer check = custdb.getCustomerByName(customer.getCustName());

		if (check != null) {

		throw new CustomerExistException("Customer " + customer.getCustName() + " allready exist");
		}
		custdb.createCustomer(customer);

	}
	/***
	 * Removing Customer
	 * 
	 * @param customerId
	 * @throws CustomerNotFoundException
	 * 
	 */
	public void removeCustomer(int customerId) throws CustomerNotFoundException {
		Optional<Customer> check = custdb.getCustomer(customerId);

		if(check == null)
		{
			throw new CustomerNotFoundException("Customer with the ID:" + customerId + " not exist");
		}
		
		
		custdb.removeCustomer(customerId);
		
	}
	
	/**
	 * Updating Customer set only password
	 * @param password
	 * @param customerId
	 * @throws CustomerNotFoundException
	 */
	public void updateCustomer(String password , int customerId ) throws CustomerNotFoundException {
		
		Optional<Customer> check = custdb.getCustomer(customerId);

		if(check == null)
		{
			throw new CustomerNotFoundException("Customer with the ID:" + customerId + " not exist");
		}
		
		custdb.updateCustomer(password, customerId);
	}
	/**
	 * Get Customer by Id
	 *
	 * @param customerId
	 * @return
	 * @throws CustomerNotFoundException
	 */
	public Optional<Customer> getCustomer(int customerId) throws CustomerNotFoundException {
		
		Optional<Customer> check = custdb.getCustomer(customerId);

		if(check == null)
		{
			throw new CustomerNotFoundException("Customer with the ID:" + customerId + " not exist");
		}
		
		return check;
		
	}
	
	/**
	 * Getting All Customers
	 * @return ArrayList
	 * @throws CustomersNotFoundException
	 */
	public ArrayList<Customer> getAllCustomers() throws CustomersNotFoundException{
		
		ArrayList<Customer> allCustomers = custdb.getAllCustoemr();
		
		if (allCustomers == null) {
			
			throw new CustomersNotFoundException("There is no Customers in the DB yet");
			
		}
		
		return allCustomers;
	}



}

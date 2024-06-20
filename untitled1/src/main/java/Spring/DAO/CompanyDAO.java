package Spring.DAO;

import java.util.ArrayList;
import java.util.Optional;

import Spring.entities.Company;
import Spring.entities.Coupon;
import Spring.exceptions.*;
import org.springframework.stereotype.Service;



@Service   
public interface CompanyDAO {

	void createCompany(Company company) throws CompanyExistException;
	
	void removeCompany(int companyId) throws CompanyNotFoundException;
	
	void updateCompany(String password , String email , int companyId ) throws CompanyNotFoundException;
	
	Optional<Company> getCompany(int companyId) throws CompanyNotFoundException;
	
	ArrayList<Company> getAllCompanies() throws CompaniesNotFoundException;
	
	ArrayList<Coupon> getCoupons(int companyId) throws CouponsNotFoundException , CompanyNotFoundException ;
	
	boolean login(String compName , String password );
	
	
	
	

	

}


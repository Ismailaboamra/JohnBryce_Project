package Spring.DBDAO;

import java.util.ArrayList;
import java.util.Optional;

import Spring.CompanyRepo;
import Spring.CouponRepo;
import Spring.DAO.CompanyDAO;
import Spring.entities.Company;
import Spring.entities.Coupon;
import Spring.enums.CouponType;
import Spring.exceptions.CompaniesNotFoundException;
import Spring.exceptions.CompanyExistException;
import Spring.exceptions.CompanyNotFoundException;
import Spring.exceptions.CouponsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * 
 */

/**
 * Company DBDAO connecting to DB
 *
 * @author ismael
 * @author saber
 * @author bahaa *
 */
@Service
public class CompanyDBDAO implements CompanyDAO {

	// Object's Attributes
	@Autowired
	private CompanyRepo compRepo;
	
	@Autowired
	private CouponRepo coupRepo;

	/***
	 * Creating new Company on DB
	 */
	@Override
	public void createCompany(Company company) throws CompanyExistException {
		compRepo.save(company);

	}

	/***
	 * Removing Company by the ID
	 */
	@Override
	public void removeCompany(int companyId) throws CompanyNotFoundException {
		compRepo.delete(companyId);

	}

	/***
	 * Updating Company set only password & email values
	 */
	@Override
	public void updateCompany(String password, String email, int companyId) throws CompanyNotFoundException {
		compRepo.updateCompany(password, email, companyId);
	}

	/***
	 * Getting Company by id
	 */
	@Override
	public Optional<Company> getCompany(int companyId) throws CompanyNotFoundException {
		return compRepo.findById(companyId);
	}
	
	/***
	 * Getting Company by its name
	 * @param compName
	 * @return Company
	 */
	public Company getCompanyByName(String compName)
	{
		return compRepo.findCompanyByCompName(compName);
	}
	
	/***
	 * Getting Company by its name and password
	 * @param compName
	 * @return Company
	 */
	public Company getCompanyByNameAndPassword(String compName , String password)
	{
		return compRepo.findCompanyByCompNameAndPassword(compName, password);
	}


	/***
	 * Get All Companies
	 */
	@Override
	public ArrayList<Company> getAllCompanies() throws CompaniesNotFoundException {
		ArrayList<Company> allCompanies = (ArrayList<Company>) compRepo.findAll();
		return 	allCompanies;
	}

	/***
	 * Get Company's Coupons
	 */
	@Override
	public ArrayList<Coupon> getCoupons(int companyId) throws CouponsNotFoundException, CompanyNotFoundException {
		ArrayList<Coupon> companyCoupon = coupRepo.getCompanyCoupons(companyId);
		return companyCoupon;
	}
	
	/***
	 * Get Company Coupons by type
	 * @param companyId
	 * @param type
	 * @return ArrayList<Coupon>
	 * @throws CompanyNotFoundException
	 * @throws CouponsNotFoundException
	 */
	public ArrayList<Coupon> getCouponsByType(int companyId , CouponType type)throws CompanyNotFoundException , CouponsNotFoundException
	{
		return coupRepo.findByCompanyIdAndType(companyId, type);
	}

	/***
	 * Login method
	 */
	@Override
	public boolean login(String compName, String password) {
		Company check = compRepo.findCompanyByCompNameAndPassword(compName, password);
		if(check == null)
		{
			return false;
		}
		return true;
	}

}

package Spring;



import Spring.entities.Company;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface CompanyRepo extends CrudRepository<Company, Integer> {
	
	
	/***
	 * Updating Company set only password & email
	 * @param password
	 * @param email
	 * @param id
	 */
	@Transactional
	@Modifying
	@Query("UPDATE companies comp SET comp.PASSWORD = :password , comp.EMAIL = :email WHERE comp.ID = :id")
	void updateCompany(@Param("password") String password , @Param("email") String email , @Param("id") int id);
	
	/***
	 * Getting Company by its name
	 * @param compName
	 * @return
	 */
	Company findCompanyByCompName(String compName);

	/***
	 * Getting Company by its name & password
	 * @param compName
	 * @return
	 */
	Company findCompanyByCompNameAndPassword(String compName , String password);

	@Transactional
	@Modifying
	@Query("DELETE FROM companies comp WHERE comp.COMPANY_ID= :companyId")
	void delete(@Param("companyId") int companyId);
}

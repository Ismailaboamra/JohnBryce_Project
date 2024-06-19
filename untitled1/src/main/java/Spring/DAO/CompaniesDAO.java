package Spring.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import Spring.JavaBeans.Company;
import jakarta.persistence.Lob;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompaniesDAO extends JpaRepository<Company, Long> {
    boolean isCompanyExists(String email, String password) throws SQLException;

    void addCompany(Company company) throws SQLException;

    void updateCompany(Company company) throws SQLException;

    void deleteCompany(int companyID) throws SQLException;

    ArrayList<Company> getAllCompanies() throws SQLException;

    Company getOneCompany(int companyID) throws SQLException;
}

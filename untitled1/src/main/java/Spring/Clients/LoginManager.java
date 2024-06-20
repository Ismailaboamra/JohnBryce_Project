package Spring.Clients;

;
import Spring.enums.ClientType;
import Spring.facades.AdminFacade;
import Spring.facades.CompanyFacade;
import Spring.facades.CouponClientFacade;
import Spring.facades.CustomerFacade;

import java.sql.SQLException;

public class LoginManager {
    private static LoginManager instance;

    // Private constructor to prevent instantiation from outside
    private LoginManager() {
    }

    // Static method to get the singleton instance
    public static LoginManager getInstance() {
        if (instance == null) {
            instance = new LoginManager();
        }
        return instance;
    }

    // Login function
    public CouponClientFacade login(String email, String password, ClientType clientType) throws SQLException {
        try {
            if (clientType == ClientType.ADMIN) {
                return new AdminFacade(email, password);
            } else if (clientType == ClientType.CUSTOMER) {
                return new CustomerFacade();
            } else if (clientType == ClientType.COMPANY) {
//                return new CompanyFacade(email, password);
            } else {
                return null;
            }
        } catch (IllegalArgumentException e) {
            // Catch the IllegalArgumentException thrown by AdminFacade
            // and return null if invalid email or password
            throw new IllegalArgumentException("Invalid email or password");

        }
        return null;
    }


    // Dummy methods to validate login credentials
    private boolean isValidAdminLogin(String email, String password) {
        return email.equals("admin@admin.com") && password.equals("admin");
    }



}


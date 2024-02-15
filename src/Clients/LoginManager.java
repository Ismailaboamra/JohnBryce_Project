package Clients;

import Facade.AdminFacade;
import Facade.ClientFacade;
import Facade.CompanyFacede;
import Facade.CustomerFacade;

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
    public ClientFacade login(String email, String password, ClientType clientType) throws SQLException {
        try {
            if (clientType == ClientType.Admin) {
                return new AdminFacade(email, password);
            } else if (clientType == ClientType.Customer) {
                return new CustomerFacade(email, password);
            } else if (clientType == ClientType.Company) {
                return new CompanyFacede(email, password);
            } else {
                return null;
            }
        } catch (IllegalArgumentException e) {
            // Catch the IllegalArgumentException thrown by AdminFacade
            // and return null if invalid email or password
            throw new IllegalArgumentException("Invalid email or password");

        }
    }


    // Dummy methods to validate login credentials
    private boolean isValidAdminLogin(String email, String password) {
        return email.equals("admin@admin.com") && password.equals("admin");
    }


}


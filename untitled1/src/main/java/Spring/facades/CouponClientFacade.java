
package Spring.facades;

import Spring.enums.ClientType;
import org.springframework.stereotype.Component;




@Component
public interface CouponClientFacade {
	AdminFacade login(String name, String password, ClientType type);
	/**
	 * login method for different types.
	 * @param name
	 * @param password
	 * @param type
	 * @return
	 */
//	CouponClientFacade login(String name , String password , ClientType type);
//
//	CustomerFacade login(String name, String password, ClientType type);
}

package Spring.couponsystem;

import java.util.Date;

import Spring.CouponRepo;
import Spring.enums.ClientType;
import Spring.facades.CouponClientFacade;
import Spring.factory.FacadeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;



@Component
@Scope("singleton")
public class CouponSystem {
	
	@Autowired
	private FacadeFactory factory;
	@Autowired
	private CouponRepo coupRepo;

	// Object's members
	private boolean running;
	
	private Thread thread;
	
	/***
	 * Login method for facades
	 * @param name
	 * @param password
	 * @param type
	 * @return CouponClientFacade
	 */
	public CouponClientFacade login(String name , String password , ClientType type)
	{
		return factory.login(name, password, type);
				
	}
	
	
	
	
	public synchronized void start()
	{
		if(running) return;
		running = true;
		
		thread = new Thread(new Runnable() {
			
			@Override
			public void run() {

				while(running) {
					coupRepo.removeExpiredCoupon(new Date(System.currentTimeMillis()));
					try {
						Thread.sleep(86400000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		
		thread.start();
	}
	
	
	/**
	 * Shut down System
	 */
	public synchronized void shutDown()
	{
		if(!running) return;
		
		running = false;

	}
	
	

}

package josh.dao;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibHelper {




	private static SessionFactory sessionFactory;

	private static SessionFactory buildSessionFactory() {
		System.out.println("Built new session factory");
		//java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.OFF);
		//java.util.logging.Logger.getLogger("com.mchange").setLevel(java.util.logging.Level.OFF);
		try {
			String path = System.getProperty("user.home");
			String resultFile = path + "/.NoPEProxy/requests.sqlite";
			String SQLString =  "jdbc:sqlite:" + resultFile;
			Configuration cfg = new Configuration();
			cfg.configure();
			cfg.getProperties().setProperty("hibernate.connection.url",SQLString);
			return cfg.buildSessionFactory();
		}
		catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			ex.printStackTrace();
			throw ex;
		}
	}

	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) sessionFactory = buildSessionFactory();
		return sessionFactory;
	}

	public static void renew(){
		sessionFactory = buildSessionFactory();
	}



}

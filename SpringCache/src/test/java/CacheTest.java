import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.config.AppConfig;
import com.db.CustomerRepository;
import com.db.CustomerRepositoryImpl;
import com.domain.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class})
public class CacheTest {
	
	@Autowired
	CustomerRepository repo;

	@Test
	public void test() {
		System.out.println("Write customer REPO + @Transactional + @Cachable + Cache config.");
		
		System.out.println("count: " + repo.count());
		Customer customer = new Customer();
		customer.setFirstname("John");
		customer.setLastname("Albright");
		customer.setUsername("jalbri");
		customer.setEmail("john.albright@gmail.com");
		repo.save(customer);
		
		System.out.println("count: " + repo.count());
		System.out.println("Customer get: " + repo.customGet());
		System.out.println("Customer get: " + repo.customGet());
		System.out.println("Customer get: " + repo.customGet());
		System.out.println("count: " + repo.count());
				
		Assert.assertEquals(1, CustomerRepositoryImpl.getStaticExecutions());

	}
}

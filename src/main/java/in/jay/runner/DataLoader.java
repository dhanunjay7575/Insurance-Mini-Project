package in.jay.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.jay.entity.CitizenPlan;
import in.jay.repo.CitizenPlanRepo;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private CitizenPlanRepo repo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {

		repo.deleteAll();
		
		CitizenPlan c = new CitizenPlan();
		c.setCitizenName("John");
		c.setGender("Male");
		c.setPlanName("Food");
		c.setPlanStatus("Approved");
		c.setPlanStartDate(LocalDate.now());
		c.setPlanEndDate(LocalDate.now().plusMonths(6));
		c.setBenefitAmt(5000.00);

		// Cash Plan Data
				CitizenPlan c2 = new CitizenPlan();
				c2.setCitizenName("Smith");
				c2.setGender("Male");
				c2.setPlanName("Cash");
				c2.setPlanStatus("Denied");
				c2.setDenialReason("Rental Income");
				
				CitizenPlan c3 = new CitizenPlan();
				c3.setCitizenName("Cathy");
				c3.setGender("Fe-Male");
				c3.setPlanName("Cash");
				c3.setPlanStatus("Terminated");
				c3.setPlanStartDate(LocalDate.now());
				c3.setPlanEndDate(LocalDate.now().minusMonths(4));
				c3.setBenefitAmt(5000.00);
				c3.setTerminationDate(LocalDate.now());
				c3.setTerminationRsnl("Employed");
				
				// Food Plan Data
				CitizenPlan c4 = new CitizenPlan();
				c4.setCitizenName("Orlen");
				c4.setGender("Male");
				c4.setPlanName("Food");
				c4.setPlanStatus("Approved");
				c4.setPlanStartDate(LocalDate.now());
				c4.setPlanEndDate(LocalDate.now().plusMonths(6));
				c4.setBenefitAmt(5000.00);

				
				CitizenPlan c5 = new CitizenPlan();
				c5.setCitizenName("Joseph");
				c5.setGender("Male");
				c5.setPlanName("Food");
				c5.setPlanStatus("Denied");
				c5.setDenialReason("Property Income");
				
				CitizenPlan c6 = new CitizenPlan();
				c6.setCitizenName("Kristen");
				c6.setGender("Fe-Male");
				c6.setPlanName("Food");
				c6.setPlanStatus("Terminated");
				c6.setPlanStartDate(LocalDate.now());
				c6.setPlanEndDate(LocalDate.now().minusMonths(4));
				c6.setBenefitAmt(5000.00);
				c6.setTerminationDate(LocalDate.now());
				c6.setTerminationRsnl("Employed");
				
				//Medical Plan Data
				CitizenPlan c7 = new CitizenPlan();
				c7.setCitizenName("Charles");
				c7.setGender("Male");
				c7.setPlanName("Medical");
				c7.setPlanStatus("Approved");
				c7.setPlanStartDate(LocalDate.now());
				c7.setPlanEndDate(LocalDate.now().plusMonths(6));
				c7.setBenefitAmt(5000.00);

				CitizenPlan c8 = new CitizenPlan();
				c8.setCitizenName("Harry");
				c8.setGender("Male");
				c8.setPlanName("Medical");
				c8.setPlanStatus("Denied");
				c8.setDenialReason("Property Income");
				
				CitizenPlan c9 = new CitizenPlan();
				c9.setCitizenName("Kristen");
				c9.setGender("Fe-Male");
				c9.setPlanName("Medical");
				c9.setPlanStatus("Terminated");
				c9.setPlanStartDate(LocalDate.now());
				c9.setPlanEndDate(LocalDate.now().minusMonths(4));
				c9.setBenefitAmt(5000.00);
				c9.setTerminationDate(LocalDate.now());
				c9.setTerminationRsnl("Govt-Job");
				
				//Plan Data Employment
				CitizenPlan c10 = new CitizenPlan();
				c10.setCitizenName("Mike");
				c10.setGender("Male");
				c10.setPlanName("Employment");
				c10.setPlanStatus("Approved");
				c10.setPlanStartDate(LocalDate.now());
				c10.setPlanEndDate(LocalDate.now().plusMonths(6));
				c10.setBenefitAmt(5000.00);

				
				CitizenPlan c11 = new CitizenPlan();
				c11.setCitizenName("James");
				c11.setGender("Male");
				c11.setPlanName("Employment");
				c11.setPlanStatus("Denied");
				c11.setDenialReason("Property Income");
				
				CitizenPlan c12 = new CitizenPlan();
				c12.setCitizenName("Stacy");
				c12.setGender("Fe-Male");
				c12.setPlanName("Employment");
				c12.setPlanStatus("Terminated");
				c12.setPlanStartDate(LocalDate.now());
				c12.setPlanEndDate(LocalDate.now().minusMonths(4));
				c12.setBenefitAmt(5000.00);
				c12.setTerminationDate(LocalDate.now());
				c12.setTerminationRsnl("Govt-Job");

		List<CitizenPlan> list = Arrays.asList(c,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12);
				
		repo.saveAll(list);
	
	}
	
}

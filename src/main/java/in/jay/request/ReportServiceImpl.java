package in.jay.request;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;


import in.jay.entity.CitizenPlan;
import in.jay.repo.CitizenPlanRepo;
import in.jay.service.ReportService;
import in.jay.util.EmailUtil;
import in.jay.util.ExcelGenerator;
import in.jay.util.PdfGenerator;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private CitizenPlanRepo planrepo;
	
	@Autowired
	private ExcelGenerator excelGenerator;
	
	@Autowired
	private PdfGenerator pdfGenerator;
	
	@Autowired
	private EmailUtil emailutil;
	
	@Override
	public List<String> getPlanName() {
		
		return planrepo.getPlanName();
	}

	@Override
	public List<String> getPlanStatus() {
		
		return planrepo.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		
		CitizenPlan entity = new CitizenPlan();

		if(null!=request.getPlanName() && !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}
		
		if(null!=request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		
		if(null!=request.getGender() && !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}
		if(null != request.getStartDate() && !"".equals(request.getStartDate())) {
			String startDate = request.getStartDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			//convert String to LocalDate
			LocalDate localDate = LocalDate.parse(startDate, formatter);
			entity.setPlanStartDate(localDate);
		}
		if(null != request.getEndDate() && !"".equals(request.getEndDate())) {
			String endDate = request.getEndDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			//convert String to LocalDate
			LocalDate localDate = LocalDate.parse(endDate, formatter);
			entity.setPlanEndDate(localDate);
		}
		return  planrepo.findAll(Example.of(entity));
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception {
		
		File f = new File("Plans.xls");
		
		List<CitizenPlan> plans = planrepo.findAll();
		excelGenerator.generate(response, plans,f);
		
		String subject = "Test Mail ";
		String body = "<h1>HI Text Message</h1>";
		String to = "dhanunjay7575@gmail.com";
		
		emailutil.sendEmail(subject, body, to, f);

		f.delete();
		
		return true;
		
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {

		File f = new File("Plans.pdf");
		List<CitizenPlan> plans = planrepo.findAll();
		
		pdfGenerator.generate(response,plans,f);
		String subject = "Test Mail ";
		String body = "<h1>Hi Text Message</h1>";
		String to = "dhanunjay7575@gmail.com";
		
		emailutil.sendEmail(subject, body, to, f);

		f.delete();
		
		return true;
	}

}

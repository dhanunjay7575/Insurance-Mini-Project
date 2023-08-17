package in.jay.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.jay.entity.CitizenPlan;
import in.jay.repo.CitizenPlanRepo;

@Component
public class PdfGenerator {
	
	private CitizenPlanRepo planRepo;

	public void generate(HttpServletResponse response, List<CitizenPlan> records, File file) throws Exception {
		
		Document document = new Document(PageSize.A4);
		
		PdfWriter.getInstance(document, response.getOutputStream());
		PdfWriter.getInstance(document, new FileOutputStream(file));
		
		document.open();
		
		//creating paragraph
		Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		
		Paragraph p = new Paragraph("Citizen Plan Info", fontTitle);
		
		//Aligning the paragraph
		p.setAlignment(Paragraph.ALIGN_CENTER);
		
		document.add(p);
		
		PdfPTable table = new PdfPTable(8);
		table.setSpacingAfter(5);
		
		table.addCell("ID");
		table.addCell("Citizen Name");
		table.addCell("Gender");
		table.addCell("Plan Name");
		table.addCell("Plan Status");
		table.addCell("Start Date");
		table.addCell("End Date");
		table.addCell("Benefit Amt");
		
		List<CitizenPlan> plans = planRepo.findAll();
		
		for (CitizenPlan plan : plans) {
			table.addCell(String.valueOf(plan.getCitizenId()));
			table.addCell(plan.getCitizenName());
			table.addCell(plan.getGender());
			table.addCell(plan.getPlanName());
			table.addCell(plan.getPlanStatus());
			if(null!= plan.getPlanStartDate()) {
				table.addCell(plan.getPlanStartDate()+"");
			}else {
				table.addCell("N/A");
			}
			if(null!= plan.getPlanEndDate()) {
				table.addCell(plan.getPlanStartDate()+"");
			}else {
				table.addCell("N/A");
			}
			if(null!= plan.getBenefitAmt()) {
				table.addCell(plan.getBenefitAmt()+"");
			}else {
				table.addCell("N/A");
			}
		}
		
		document.add(table);
		
		document.close();
		
	}	
}


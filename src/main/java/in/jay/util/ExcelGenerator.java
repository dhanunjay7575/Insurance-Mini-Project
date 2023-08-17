package in.jay.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.jay.entity.CitizenPlan;
import in.jay.repo.CitizenPlanRepo;


@Component
public class ExcelGenerator {
	
	@Autowired
	private CitizenPlanRepo planrepo;

	public void generate(HttpServletResponse response, List<CitizenPlan> records, File file) throws Exception{
		
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet =  workbook.createSheet("Plans-Data");
		Row headerRow = sheet.createRow(0);
		
		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("citizenId");
		headerRow.createCell(2).setCellValue("citizenName");
		headerRow.createCell(3).setCellValue("gender");
		headerRow.createCell(4).setCellValue("planName");
		headerRow.createCell(5).setCellValue("planStatus");
		headerRow.createCell(6).setCellValue("planStartDate");
		headerRow.createCell(7).setCellValue("planEndDate");
		
		List<CitizenPlan> record = planrepo.findAll();
		
		int dataRowIndex=1;
		
		for(CitizenPlan plan : record) {
			Row dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(plan.getCitizenId());
			dataRow.createCell(1).setCellValue(plan.getCitizenName());
			dataRow.createCell(2).setCellValue(plan.getGender());
			dataRow.createCell(3).setCellValue(plan.getPlanName());
			dataRow.createCell(4).setCellValue(plan.getPlanStatus());
			dataRow.createCell(5).setCellValue(plan.getPlanStartDate()+"");
			dataRow.createCell(6).setCellValue(plan.getPlanEndDate()+"");
			if(null!= plan.getBenefitAmt()) {
				dataRow.createCell(7).setCellValue(plan.getBenefitAmt());
			}else {
				dataRow.createCell(7).setCellValue("N/A");
			}
			
			
			dataRowIndex++;
		}
		
		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);
		fos.close();
		
		ServletOutputStream outputstream = response.getOutputStream();
		workbook.write(outputstream);
		workbook.close();
	}
}
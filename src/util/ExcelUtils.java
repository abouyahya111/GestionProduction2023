package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import main.ProdLauncher;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import dao.daoImplManager.FicheEmployeDAOImpl;
import dao.daoManager.FicheEmployeDAO;
import dao.entity.FicheEmploye;
import dao.entity.FicheEmployeGlobale;

public class ExcelUtils {
	
	
	public static void CreerExcelFicheGlobal(List<FicheEmployeGlobale> listFicheEmployeGlobale) throws IOException{
		
		int rownum = 0;
		Cell cell;
		Row row;
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Employees sheet");
		
		HSSFFont font = workbook.createFont();
		//font.setBold(true);
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		
		row = sheet.createRow(rownum);
		
		// EmpNo
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("Nom");
		cell.setCellStyle(style);
		// EmpName
		cell = row.createCell(1, CellType.NUMERIC);
		cell.setCellValue("Remise");
		cell.setCellStyle(style);
		// Salary
		cell = row.createCell(2, CellType.NUMERIC);
		cell.setCellValue("Nbre Absence");
		cell.setCellStyle(style);
		// Grade
		cell = row.createCell(3, CellType.NUMERIC);
		cell.setCellValue("Reduction");
		cell.setCellStyle(style);
		// Bonus
		cell = row.createCell(4, CellType.NUMERIC);
		cell.setCellValue("Total");
		cell.setCellStyle(style);
		// Bonus
		
		
		// Data
				for (FicheEmployeGlobale emp : listFicheEmployeGlobale) {
				rownum++;
				row = sheet.createRow(rownum);
				
				// EmpNo (A)
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue(emp.getEmploye().getNom());
				// EmpName (B)
				cell = row.createCell(1, CellType.NUMERIC);
				cell.setCellValue(emp.getRemise().doubleValue());
				// Salary (C)
				cell = row.createCell(2, CellType.NUMERIC);
				cell.setCellValue(emp.getCompteur());
				// Grade (D)
				cell = row.createCell(3, CellType.NUMERIC);
				cell.setCellValue(emp.getReduction().doubleValue());
				// Bonus (E)
				String formula = "B" + (rownum + 1) + "-D" + (rownum + 1);
				cell = row.createCell(4, CellType.FORMULA);
				cell.setCellFormula(formula);
				}
				
				//String nomFiche="ficheGlobal"+
				File file = new File("C:/demo/employee.xls");
				file.getParentFile().mkdirs();
				
				FileOutputStream outFile = new FileOutputStream(file);
				workbook.write(outFile);
		
		
	}
	

		
	

}

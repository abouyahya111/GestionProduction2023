package util;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.jdesktop.swingx.JXTable;

public class  ExporterTableVersExcel {
	public static Workbook workbook = new HSSFWorkbook();
	public static void tabletoexcel(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>3 && j<14)
                		if(!table.getValueAt(i, j).toString().equals(""))
                		{
                			  row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                		}else
                		{
                			 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
                			
                		}
                  
                	else
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	public static void tabletoexcelDetailOF(JXTable tableCoutMP , JXTable tableCoutEmplaoyeGenerique ,JXTable tableCoutEmployeProduction ,JXTable tableCoutEmployeEmballage , String titre , String titrefeuille ,String CodeArticle,String Libelle, String DateProduction, String Periode, String Prix,String QuantiteDemande,String QuantiteReel,String CoutTotal)
	{
		
if(tableCoutMP.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

        
       /////////////////////////////////////////////////////////////////// Information Production ////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
        
        
       
        row = spreadsheet.createRow(2);
        row.createCell(1).setCellValue( CodeArticle);
        row.createCell(0).setCellValue( "Code Article  ");
      
        row = spreadsheet.createRow(3);
        row.createCell(1).setCellValue(Libelle);
        row.createCell(0).setCellValue("Article  ");
       
        row = spreadsheet.createRow(4);
        row.createCell(1).setCellValue(DateProduction);
        row.createCell(0).setCellValue("Date Production ");
        
        
        row = spreadsheet.createRow(5);
        row.createCell(1).setCellValue(Periode);
        row.createCell(0).setCellValue("Période ");
        
        row = spreadsheet.createRow(6);
        row.createCell(1).setCellValue(Prix);
        row.createCell(0).setCellValue("Prix ");
        
        row = spreadsheet.createRow(7);
        row.createCell(1).setCellValue(QuantiteDemande);
        row.createCell(0).setCellValue("Quantite Demande ");
        
        row = spreadsheet.createRow(8);
        row.createCell(1).setCellValue(QuantiteReel);
        row.createCell(0).setCellValue("Quantite Reel ");
        
        row = spreadsheet.createRow(9);
        row.createCell(1).setCellValue(CoutTotal);
        row.createCell(0).setCellValue("Cout Total ");
        
        ////////////////////////////////////////////////////////////////////////// tableCoutMP  //////////////////////////////////////////////////////////////////////////////////////////////////////

        
        row = spreadsheet.createRow(11);
        row.createCell(0).setCellValue("Cout MP ");
        
        row = spreadsheet.createRow(12);
        
      
        for (int j = 0; j < tableCoutMP.getColumns().size(); j++) {
        
        	row.createCell(j).setCellValue(tableCoutMP.getColumnName(j).toString());
   

        }

        for (int i = 0; i < tableCoutMP.getRowCount(); i++) {
        	row = spreadsheet.createRow(i + 13);
            for (int j = 0; j < tableCoutMP.getColumns().size(); j++) {
                if (tableCoutMP.getValueAt(i, j) != null) {
                	
                	
                	if(j>1)
                	{
                		HSSFCell dataCell = (HSSFCell) row.createCell(j);
                		dataCell.setCellValue(Double.valueOf(tableCoutMP.getValueAt(i, j).toString().replaceAll(" ", "").replaceAll(",", ".")));
                		dataCell.getCellStyle().setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
                	}else
                	{
                		row.createCell(j).setCellValue(tableCoutMP.getValueAt(i, j).toString());
                	}
                		
                	
                	
                		
   
                } else {
                	row.createCell(j).setCellValue("");
                }
            }
        }
        
        
        
        row = spreadsheet.createRow( tableCoutMP.getRowCount()+13);
        row.createCell(0).setCellValue("Total Cout MP  ");   
       
        row.createCell(9).setCellFormula("SUM(J"+13+":J"+(tableCoutMP.getRowCount()+13)+")");  
        row.createCell(10).setCellFormula("SUM(K"+13+":K"+(tableCoutMP.getRowCount()+13)+")"); 
        row.createCell(11).setCellFormula("SUM(L"+13+":L"+(tableCoutMP.getRowCount()+13)+")"); 
        row.createCell(12).setCellFormula("SUM(M"+13+":M"+(tableCoutMP.getRowCount()+13)+")"); 
        row.createCell(13).setCellFormula("SUM(N"+13+":N"+(tableCoutMP.getRowCount()+13)+")"); 
        row.createCell(15).setCellFormula("SUM(J"+(tableCoutMP.getRowCount()+14)+":N"+(tableCoutMP.getRowCount()+14)+")");
        
        /*
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        */
        

        
        ////////////////////////////////////////////////////////////////////////////// tableCoutEmplaoyeGenerique    //////////////////////////////////////////////////////////////////////////////////////////////////
      
       
        row = spreadsheet.createRow(tableCoutMP.getRowCount()+15);
        row.createCell(0).setCellValue("Cout Emplaoye Generique  ");
        
        row = spreadsheet.createRow(tableCoutMP.getRowCount()+15);  
        
    

        for (int j = 0; j < tableCoutEmplaoyeGenerique.getColumns().size(); j++) {
        	row.createCell(j).setCellValue(tableCoutEmplaoyeGenerique.getColumnName(j).toString());
   

        }
      //  row = spreadsheet.createRow(tableCoutMP.getRowCount()+4); 

        for (int i = 0; i < tableCoutEmplaoyeGenerique.getRowCount(); i++) {
        	row = spreadsheet.createRow(i + tableCoutMP.getRowCount()+16);
            for (int j = 0; j < tableCoutEmplaoyeGenerique.getColumns().size(); j++) {
                if (tableCoutEmplaoyeGenerique.getValueAt(i, j) != null) {
                	
                	
                	if(j>0)
                	{
                		HSSFCell dataCell1 = (HSSFCell) row.createCell(j);
                    	dataCell1.setCellValue(Double.valueOf(tableCoutEmplaoyeGenerique.getValueAt(i, j).toString().replaceAll(" ", "").replaceAll(",", ".")));
                	 
                    	dataCell1.getCellStyle().setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
                	}else
                	{
                		row.createCell(j).setCellValue(tableCoutEmplaoyeGenerique.getValueAt(i, j).toString());
                	}
                		
   
                } else {
                	row.createCell(j).setCellValue("");
                }
            }
        } 
        
        
        
        row = spreadsheet.createRow(tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount()+16);
        row.createCell(0).setCellValue("Total Cout Emplaoye Generique  ");   
       
        row.createCell(4).setCellFormula("SUM(E"+(tableCoutMP.getRowCount() +16)+":E"+(tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount()+16)+")");  
        row.createCell(5).setCellFormula("SUM(F"+(tableCoutMP.getRowCount() +16)+":F"+(tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount()+16)+")"); 
        row.createCell(6).setCellFormula("SUM(G"+(tableCoutMP.getRowCount() +16)+":G"+(tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount()+16)+")"); 
       
        row.createCell(15).setCellFormula("SUM(E"+(tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount()+17)+":G"+(tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount()+17)+")");
        
        
        
        
        
        /*
       
        for (short i = spreadsheet.getRow(tableCoutMP.getRowCount()+4).getFirstCellNum(),
                end = spreadsheet.getRow(tableCoutMP.getRowCount()+4).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(tableCoutMP.getRowCount()+4, rowNum,
                            spreadsheet.getRow(tableCoutMP.getRowCount()+4).getFirstCellNum(),
                            spreadsheet.getRow(tableCoutMP.getRowCount()+4).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(tableCoutMP.getRowCount()+4).getFirstCellNum(),
                end = spreadsheet.getRow(tableCoutMP.getRowCount()+4).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        */
        
       
        


        ////////////////////////////////////////////////////////////////////////////// tableCoutEmployeProduction    //////////////////////////////////////////////////////////////////////////////////////////////////
    
       
        
        row = spreadsheet.createRow(tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount()+18);
        row.createCell(0).setCellValue("Cout Emplaoye Production  "); 
        
      row = spreadsheet.createRow(tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount()+19);  
        
        

        for (int j = 0; j < tableCoutEmployeProduction.getColumns().size(); j++) {
        	row.createCell(j).setCellValue(tableCoutEmployeProduction.getColumnName(j).toString());
   

        }
   //     row = spreadsheet.createRow(tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount() + 3); 

        for (int i = 0; i < tableCoutEmployeProduction.getRowCount(); i++) {
        	row = spreadsheet.createRow(i +tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount()+20);
            for (int j = 0; j < tableCoutEmployeProduction.getColumns().size(); j++) {
                if (tableCoutEmployeProduction.getValueAt(i, j) != null) {
                	
                	
                	if(j>0)
                	{
                		HSSFCell dataCell1 = (HSSFCell) row.createCell(j);
                    	
                		dataCell1.setCellValue(Double.valueOf(tableCoutEmployeProduction.getValueAt(i, j).toString().replaceAll(" ", "").replaceAll(",", ".")));
                    	dataCell1.getCellStyle().setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
                    	
                		
                	}
                		
                	else {
                		row.createCell(j).setCellValue(tableCoutEmployeProduction.getValueAt(i, j).toString());
                	}
                		
   
                } else {
                	row.createCell(j).setCellValue("");
                }
            }
        } 
        
        
        row = spreadsheet.createRow(tableCoutEmployeProduction.getRowCount() + tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount()+20);
        row.createCell(0).setCellValue("Total Cout Emplaoye Production  ");   
       
        row.createCell(4).setCellFormula("SUM(E"+(tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount()+21)+":E"+(tableCoutEmployeProduction.getRowCount() + tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount()+20)+")");  
        row.createCell(5).setCellFormula("SUM(F"+(tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount()+21)+":F"+(tableCoutEmployeProduction.getRowCount() + tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount()+20)+")"); 
        row.createCell(6).setCellFormula("SUM(G"+(tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount()+21)+":G"+(tableCoutEmployeProduction.getRowCount() + tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount()+20)+")"); 
       
        row.createCell(15).setCellFormula("SUM(E"+(tableCoutEmployeProduction.getRowCount() + tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount()+21)+":G"+(tableCoutEmployeProduction.getRowCount() + tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount()+21)+")");
 
        
        
        
        
        
        
        
        
        /*
       
        for (short i = spreadsheet.getRow(tableCoutEmplaoyeGenerique.getRowCount()+3).getFirstCellNum(),
                end = spreadsheet.getRow(tableCoutEmplaoyeGenerique.getRowCount()+3).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(tableCoutEmplaoyeGenerique.getRowCount()+3, rowNum,
                            spreadsheet.getRow(tableCoutEmplaoyeGenerique.getRowCount()+3).getFirstCellNum(),
                            spreadsheet.getRow(tableCoutEmplaoyeGenerique.getRowCount()+3).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(tableCoutEmplaoyeGenerique.getRowCount()+3).getFirstCellNum(),
                end = spreadsheet.getRow(tableCoutEmplaoyeGenerique.getRowCount()+3).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        
        */
        
     
      

        
        ////////////////////////////////////////////////////////////////////////////// tableCoutEmployeEmballage    //////////////////////////////////////////////////////////////////////////////////////////////////
     
      
  //row = spreadsheet.createRow(tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount()+tableCoutEmployeProduction.getRowCount() + 3);  
        
        row = spreadsheet.createRow(tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount()+tableCoutEmployeProduction.getRowCount()+21 );
        row.createCell(0).setCellValue("Cout Emplaoye Emballage   "); 
        
        row = spreadsheet.createRow(tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount()+tableCoutEmployeProduction.getRowCount()+22 ); 


        for (int j = 0; j < tableCoutEmployeEmballage.getColumns().size(); j++) {
        	row.createCell(j).setCellValue(tableCoutEmployeEmballage.getColumnName(j).toString());
   

        }

        for (int i = 0; i < tableCoutEmployeEmballage.getRowCount(); i++) {
        	row = spreadsheet.createRow(i +tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount()+tableCoutEmployeProduction.getRowCount() + 23);
            for (int j = 0; j < tableCoutEmployeEmballage.getColumns().size(); j++) {
                if (tableCoutEmployeEmballage.getValueAt(i, j) != null) {
                	
                	
                	if(j>0)
                	{
                		
HSSFCell dataCell1 = (HSSFCell) row.createCell(j);
                    	
                		
                		
                           dataCell1.setCellValue(Double.valueOf(tableCoutEmployeEmballage.getValueAt(i, j).toString().replaceAll(" ", "").replaceAll(",", ".")));
                    	dataCell1.getCellStyle().setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
                		
                	}
                		
                	else {
                		row.createCell(j).setCellValue(tableCoutEmployeEmballage.getValueAt(i, j).toString());
                	}
                		
   
                } else {
                	row.createCell(j).setCellValue("");
                }
            }
        } 
        
        
        
        
        
        row = spreadsheet.createRow(tableCoutEmployeProduction.getRowCount() + tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount() + tableCoutEmployeEmballage.getRowCount() + 23);
        row.createCell(0).setCellValue("Total Cout Emplaoye Emballage  ");   
       
        row.createCell(4).setCellFormula("SUM(E"+(tableCoutEmployeProduction.getRowCount() + tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount() + 23)+":E"+(tableCoutEmployeProduction.getRowCount() + tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount() + tableCoutEmployeEmballage.getRowCount() + 23)+")");  
        row.createCell(5).setCellFormula("SUM(F"+(tableCoutEmployeProduction.getRowCount() + tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount() + 23)+":F"+(tableCoutEmployeProduction.getRowCount() + tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount() + tableCoutEmployeEmballage.getRowCount() + 23)+")"); 
        row.createCell(6).setCellFormula("SUM(G"+(tableCoutEmployeProduction.getRowCount() + tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount() + 23)+":G"+(tableCoutEmployeProduction.getRowCount() + tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount() + tableCoutEmployeEmballage.getRowCount() + 23)+")"); 
       
        row.createCell(15).setCellFormula("SUM(E"+(tableCoutEmployeProduction.getRowCount() + tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount() + tableCoutEmployeEmballage.getRowCount() + 24)+":G"+(tableCoutEmployeProduction.getRowCount() + tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount() + tableCoutEmployeEmballage.getRowCount() + 24)+")");

      //////////////////////////////////////////////////////////////////////// Cout Total /////////////////////////////////////////////////////////////////////////////////  
        
        row = spreadsheet.createRow(tableCoutEmployeProduction.getRowCount() + tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount() + tableCoutEmployeEmballage.getRowCount() + 26);
        row.createCell(0).setCellValue("Cout Total ");  
       row.createCell(6).setCellFormula("SUM(P"+(tableCoutMP.getRowCount()+14)+":P"+(tableCoutEmployeProduction.getRowCount() + tableCoutMP.getRowCount() + tableCoutEmplaoyeGenerique.getRowCount() + tableCoutEmployeEmballage.getRowCount() + 26)+")"); 

        
      /*
        for (short i = spreadsheet.getRow(tableCoutEmployeProduction.getRowCount()+3).getFirstCellNum(),
                end = spreadsheet.getRow( tableCoutEmployeProduction.getRowCount()+3).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress( tableCoutEmployeProduction.getRowCount()+3, rowNum,
                            spreadsheet.getRow( tableCoutEmployeProduction.getRowCount()+3).getFirstCellNum(),
                            spreadsheet.getRow( tableCoutEmployeProduction.getRowCount()+3).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow( tableCoutEmployeProduction.getRowCount()+3).getFirstCellNum(),
                end = spreadsheet.getRow( tableCoutEmployeProduction.getRowCount()+3).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        
      */
        
        
  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////      
        
        
        

        
        
        
        
        
        
        
        
 
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}	
	
	
	
	
	public static void tabletoexcelMP(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>0)
                    row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	else
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	
	
	
	
	public static void tabletoexcelEtatStockPF(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>0)
                    row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	else
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	public static void tabletoexcelFicheGlobaleDesVersements(JTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumnCount() ; j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumnCount(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>1)
                	{
                		
                        HSSFCell dataCell1 = (HSSFCell) row.createCell(j);
                    	
                        dataCell1.setCellValue(Double.valueOf(table.getValueAt(i, j).toString().replaceAll(" ", "").replaceAll(",", ".")));
                	
                    	dataCell1.getCellStyle().setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
                		 
                	}
                		
                	else
                	{
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
                	}
                		
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        
        row = spreadsheet.createRow(table.getRowCount()+2);
        row.createCell(0).setCellValue("Total");   
       
        row.createCell(2).setCellFormula("SUM(C"+(2)+":C"+(table.getRowCount()+2)+")");  
        row.createCell(3).setCellFormula("SUM(D"+(2)+":D"+(table.getRowCount()+2)+")"); 
        row.createCell(4).setCellFormula("SUM(E"+(2)+":E"+(table.getRowCount()+2)+")"); 
        row.createCell(5).setCellFormula("SUM(F"+(2)+":F"+(table.getRowCount()+2)+")"); 
        row.createCell(6).setCellFormula("SUM(G"+(2)+":G"+(table.getRowCount()+2)+")"); 
        row.createCell(7).setCellFormula("SUM(H"+(2)+":H"+(table.getRowCount()+2)+")"); 
        row.createCell(8).setCellFormula("SUM(I"+(2)+":I"+(table.getRowCount()+2)+")"); 
       
 
        
        
        
        
        
        
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	public static void tabletoexcelEtatStockMPParRegion(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>1)
                	{
                		
                        HSSFCell dataCell1 = (HSSFCell) row.createCell(j);
                    	
                        dataCell1.setCellValue(Double.valueOf(table.getValueAt(i, j).toString().replaceAll(" ", "").replaceAll(",", ".")));
                	
                    	dataCell1.getCellStyle().setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
                		 
                	}
                		
                	else
                	{
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
                	}
                		
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        
        row = spreadsheet.createRow(table.getRowCount()+2);
        row.createCell(0).setCellValue("Total");   
       
        row.createCell(2).setCellFormula("SUM(C"+(2)+":C"+(table.getRowCount()+2)+")");  
        row.createCell(3).setCellFormula("SUM(D"+(2)+":D"+(table.getRowCount()+2)+")"); 
        row.createCell(4).setCellFormula("SUM(E"+(2)+":E"+(table.getRowCount()+2)+")"); 
        row.createCell(5).setCellFormula("SUM(F"+(2)+":F"+(table.getRowCount()+2)+")"); 
        row.createCell(6).setCellFormula("SUM(G"+(2)+":G"+(table.getRowCount()+2)+")"); 
        row.createCell(7).setCellFormula("SUM(H"+(2)+":H"+(table.getRowCount()+2)+")"); 
        row.createCell(8).setCellFormula("SUM(I"+(2)+":I"+(table.getRowCount()+2)+")"); 
       
 
        
        
        
        
        
        
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	public static void tabletoexcelStatistiqueEnVracConsomme(JXTable table , String titre , String titrefeuille,BigDecimal TotalPourcentage)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }
        
        

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>=3 && j<=9)
                	{
                		
                        HSSFCell dataCell1 = (HSSFCell) row.createCell(j);
                    	
                        dataCell1.setCellValue(Double.valueOf(table.getValueAt(i, j).toString().replaceAll(" ", "").replaceAll(",", ".")));
                	
                    	dataCell1.getCellStyle().setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
                		 
                	}
                		
                	else
                	{
                		
                		
                		if(j==10)
                		{
                			 row.createCell(j).setCellValue(table.getValueAt(i, j).toString().replace(".", ","));
                			  
                			
                			
                		}else
                		{
                			 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
                		}
                		
                	}
                		
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        
        row = spreadsheet.createRow(table.getRowCount()+2);
        row.createCell(0).setCellValue("Total");   
       
        row.createCell(3).setCellFormula("SUM(D"+(2)+":D"+(table.getRowCount()+2)+")"); 
        row.createCell(4).setCellFormula("SUM(E"+(2)+":E"+(table.getRowCount()+2)+")"); 
        row.createCell(5).setCellFormula("SUM(F"+(2)+":F"+(table.getRowCount()+2)+")"); 
        row.createCell(6).setCellFormula("SUM(G"+(2)+":G"+(table.getRowCount()+2)+")");  
        row.createCell(7).setCellFormula("SUM(H"+(2)+":H"+(table.getRowCount()+2)+")");  
        row.createCell(8).setCellFormula("SUM(I"+(2)+":I"+(table.getRowCount()+2)+")");  
        row.createCell(9).setCellFormula("SUM(J"+(2)+":J"+(table.getRowCount()+2)+")");  
        row.createCell(10).setCellValue(Double.valueOf(TotalPourcentage.toString())+"%"); 
      
        
        
        
        
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	public static void tabletoexcelListDetailOrdreFabrication(JXTable table , String titre , String titrefeuille )
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }
        
        

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>3)
                	{
                		
                        HSSFCell dataCell1 = (HSSFCell) row.createCell(j);
                    	
                        dataCell1.setCellValue(Double.valueOf(table.getValueAt(i, j).toString().replaceAll(" ", "").replaceAll(",", ".")));
                	
                    	dataCell1.getCellStyle().setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
                		 
                	}
                		
                	else
                	{
                		
                		
                		 
                			 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
                		 
                		
                	}
                		
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        
        row = spreadsheet.createRow(table.getRowCount()+2);
        row.createCell(0).setCellValue("Total");   
       
        row.createCell(4).setCellFormula("SUM(E"+(2)+":E"+(table.getRowCount()+2)+")");  
        row.createCell(5).setCellFormula("SUM(F"+(2)+":F"+(table.getRowCount()+2)+")");  
        row.createCell(6).setCellFormula("SUM(G"+(2)+":G"+(table.getRowCount()+2)+")"); 
        row.createCell(7).setCellFormula("SUM(H"+(2)+":H"+(table.getRowCount()+2)+")"); 
        row.createCell(8).setCellFormula("SUM(I"+(2)+":I"+(table.getRowCount()+2)+")"); 
        row.createCell(9).setCellFormula("SUM(J"+(2)+":J"+(table.getRowCount()+2)+")"); 
        row.createCell(10).setCellFormula("SUM(I"+(2)+":I"+(table.getRowCount()+2)+") / SUM(J"+(2)+":J"+(table.getRowCount()+2)+")"); 
        
        
        
        
        
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	public static void tabletoexcelStatistiqueFinanciereMP(JXTable table , String titre , String titrefeuille )
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }
        
        

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>1)
                	{
                		
                        HSSFCell dataCell1 = (HSSFCell) row.createCell(j);
                    	
                        dataCell1.setCellValue(Double.valueOf(table.getValueAt(i, j).toString().replaceAll(" ", "").replaceAll(",", ".")));
                	
                    	dataCell1.getCellStyle().setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
                		 
                	}
                		
                	else
                	{
                		
                		
                		 
                			 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
                		 
                		
                	}
                		
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        
        row = spreadsheet.createRow(table.getRowCount()+2);
        
        
        
        
        
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	
	public static void tabletoexcelCoutProductionParArticle(JXTable table , String titre , String titrefeuille )
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }
        
        

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>1)
                	{
                		
                        HSSFCell dataCell1 = (HSSFCell) row.createCell(j);
                    	
                        dataCell1.setCellValue(Double.valueOf(table.getValueAt(i, j).toString().replaceAll(" ", "").replaceAll(",", ".")));
                	
                    	dataCell1.getCellStyle().setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
                		 
                	}
                		
                	else
                	{
                		
                		
                		 
                			 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
                		 
                		
                	}
                		
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        
        row = spreadsheet.createRow(table.getRowCount()+2);
        row.createCell(0).setCellValue("Total");   
       
        row.createCell(2).setCellFormula("SUM(C"+(2)+":C"+(table.getRowCount()+2)+")");  
        row.createCell(3).setCellFormula("SUM(D"+(2)+":D"+(table.getRowCount()+2)+")");  
        row.createCell(4).setCellFormula("SUM(E"+(2)+":E"+(table.getRowCount()+2)+")"); 
        row.createCell(5).setCellFormula("SUM(F"+(2)+":F"+(table.getRowCount()+2)+")"); 
        row.createCell(6).setCellFormula("SUM(G"+(2)+":G"+(table.getRowCount()+2)+")"); 
        row.createCell(7).setCellFormula("SUM(H"+(2)+":H"+(table.getRowCount()+2)+")"); 
        row.createCell(8).setCellFormula("SUM(G"+(2)+":G"+(table.getRowCount()+2)+") / SUM(H"+(2)+":H"+(table.getRowCount()+2)+")"); 
        
        
        
        
        
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	public static void tabletoexcelSituationMPConsommeParMois(JTable table , String titre , String titrefeuille )
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumnCount(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }
        
        

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumnCount(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>1)
                	{
                		
                        HSSFCell dataCell1 = (HSSFCell) row.createCell(j);
                    	
                        dataCell1.setCellValue(Double.valueOf(table.getValueAt(i, j).toString().replaceAll(" ", "").replaceAll(",", ".")));
                	
                    	dataCell1.getCellStyle().setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
                		 
                	}
                		
                	else
                	{
                		
                	 
                			 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
                	 
                		
                	}
                		
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        
        row = spreadsheet.createRow(table.getRowCount()+2);
         
      
        
 
        
        
        
        
        
        
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	public static void tabletoexcelSituationProductionTotalParArticle(JXTable table , String titre , String titrefeuille,BigDecimal TotalPourcentage)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }
        
        

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j==3)
                	{
                		
                        HSSFCell dataCell1 = (HSSFCell) row.createCell(j);
                    	
                        dataCell1.setCellValue(Double.valueOf(table.getValueAt(i, j).toString().replaceAll(" ", "").replaceAll(",", ".")));
                	
                    	dataCell1.getCellStyle().setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
                		 
                	}
                		
                	else
                	{
                		
                		
                		if(j==4)
                		{
                			 row.createCell(j).setCellValue(table.getValueAt(i, j).toString().replace(".", ","));
                			  
                			
                			
                		}else
                		{
                			 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
                		}
                		
                	}
                		
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        
        row = spreadsheet.createRow(table.getRowCount()+2);
        row.createCell(0).setCellValue("Total");   
       
        row.createCell(3).setCellFormula("SUM(D"+(2)+":D"+(table.getRowCount()+2)+")");  
        row.createCell(4).setCellValue(Double.valueOf(TotalPourcentage.setScale(2, RoundingMode.HALF_UP).toString())+"%"); 
      
        
 
        
        
        
        
        
        
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	public static void tabletoexcelSituationPFParAnnee(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }
        
        

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j!=0 && j!=3)
                	{
                		
                        HSSFCell dataCell1 = (HSSFCell) row.createCell(j);
                    	
                        dataCell1.setCellValue(Double.valueOf(table.getValueAt(i, j).toString().replaceAll(" ", "").replaceAll(",", ".")));
                	
                    	dataCell1.getCellStyle().setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
                		 
                	}
                		
                	else
                	{
                		
                	 
                			 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
                	 
                		
                	}
                		
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
       
       
      
        
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}	
	
	public static void tabletoexcelTonnageproductionParJour(JXTable table , String titre , String titrefeuille,BigDecimal TotalTonnage)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }
        
        

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j==1)
                	{
                		
                        HSSFCell dataCell1 = (HSSFCell) row.createCell(j);
                    	
                        dataCell1.setCellValue(Double.valueOf(table.getValueAt(i, j).toString().replaceAll(" ", "").replaceAll(",", ".")));
                	
                    	dataCell1.getCellStyle().setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
                		 
                	}
                		
                	else
                	{
                		
                	 
                			 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
                	 
                		
                	}
                		
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        
        row = spreadsheet.createRow(table.getRowCount()+2);
        row.createCell(0).setCellValue("Total Fabriquee ");   
       
        row.createCell(1).setCellFormula("SUM(B"+(1)+":B"+(table.getRowCount()+1)+")");  
       
      
        
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}	
	
	
	
	
	
	public static void tabletoexcelManqueImportation(JXTable table , String titre , String titrefeuille )
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }
        
        

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j==6)
                	{
                		
                        HSSFCell dataCell1 = (HSSFCell) row.createCell(j);
                    	
                        dataCell1.setCellValue(Double.valueOf(table.getValueAt(i, j).toString().replaceAll(" ", "").replaceAll(",", ".")));
                	
                    	dataCell1.getCellStyle().setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
                		 
                	}
                		
                	else
                	{
                		
                		
                		if(j==6)
                		{
                			 row.createCell(j).setCellValue(table.getValueAt(i, j).toString().replace(".", ","));
                			  
                			
                			
                		}else
                		{
                			 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
                		}
                		
                	}
                		
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        
        row = spreadsheet.createRow(table.getRowCount()+2);
        row.createCell(0).setCellValue("Total");   
       
        row.createCell(6).setCellFormula("SUM(G"+(1)+":G"+(table.getRowCount()+1)+")");  
        
      
        
 
        
        
        
        
        
        
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}




public static void tabletoexcelCreerMP(JXTable table , String titre , String titrefeuille)
{
	
if(table.getRowCount()==0)
{
JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
return;
}
	

    int number = workbook.getNumberOfSheets();
    if (number == 1) {
        workbook.removeSheetAt(0);
    }
    org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

    org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

/*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
    ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
    ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
    ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

    Font font1 = (Font) workbook.createFont();
    ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
    ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
    ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
  
    for (int j = 0; j < table.getColumns().size(); j++) {
        row.createCell(j).setCellValue(table.getColumnName(j).toString());


    }

    for (int i = 0; i < table.getRowCount(); i++) {
        row = spreadsheet.createRow(i + 1);
        for (int j = 0; j < table.getColumns().size(); j++) {
            if (table.getValueAt(i, j) != null) {
            	
            	
            	if(j>3)
                row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
            	else
            		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());

            } else {
                row.createCell(j).setCellValue("");
            }
        }
    }
    int rowNum = 0;
    for (short i = spreadsheet.getRow(0).getFirstCellNum(),
            end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
        CellRangeAddress ca
                = new CellRangeAddress(0, rowNum,
                        spreadsheet.getRow(0).getFirstCellNum(),
                        spreadsheet.getRow(0).getLastCellNum());

        rowNum++;

    }
    for (short i = spreadsheet.getRow(0).getFirstCellNum(),
            end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
        spreadsheet.autoSizeColumn(i);
    }
    try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
    	
        workbook.write(fileOut);
        fileOut.flush();
        fileOut.close();
        Desktop.getDesktop().open(new File(titre+".xls"));

    } catch (Exception e) {
		// TODO: handle exception
	}
		
	
}


public static void tabletoexcelStockMP(JXTable table , String titre , String titrefeuille )
{
	
if(table.getRowCount()==0)
{
JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
return;
}
	

    int number = workbook.getNumberOfSheets();
    if (number == 1) {
        workbook.removeSheetAt(0);
    }
    org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

    org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

/*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
    ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
    ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
    ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

    Font font1 = (Font) workbook.createFont();
    ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
    ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
    ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
  
    for (int j = 0; j < table.getColumns().size(); j++) {
        row.createCell(j).setCellValue(table.getColumnName(j).toString());


    }

    for (int i = 0; i < table.getRowCount(); i++) {
        row = spreadsheet.createRow(i + 1);
        for (int j = 0; j < table.getColumns().size(); j++) {
            if (table.getValueAt(i, j) != null) {
            	
            	
            	if(j==2)
                row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString().replaceAll(" ", "").replaceAll(",", ".")));
            	else
            		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());

            } else {
                row.createCell(j).setCellValue("");
            }
        }
    }
    int rowNum = 0;
    for (short i = spreadsheet.getRow(0).getFirstCellNum(),
            end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
        CellRangeAddress ca
                = new CellRangeAddress(0, rowNum,
                        spreadsheet.getRow(0).getFirstCellNum(),
                        spreadsheet.getRow(0).getLastCellNum());

        rowNum++;

    }
    for (short i = spreadsheet.getRow(0).getFirstCellNum(),
            end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
        spreadsheet.autoSizeColumn(i);
    }
    try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
    	
        workbook.write(fileOut);
        fileOut.flush();
        fileOut.close();
        Desktop.getDesktop().open(new File(titre+".xls"));

    } catch (Exception e) {
		// TODO: handle exception
	}
		
	
}




public static void tabletoexcelListeDesArticles(JXTable table , String titre , String titrefeuille )
{
	
if(table.getRowCount()==0)
{
JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
return;
}
	

    int number = workbook.getNumberOfSheets();
    if (number == 1) {
        workbook.removeSheetAt(0);
    }
    org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

    org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

/*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
    ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
    ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
    ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

    Font font1 = (Font) workbook.createFont();
    ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
    ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
    ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
  
    for (int j = 0; j < table.getColumns().size(); j++) {
        row.createCell(j).setCellValue(table.getColumnName(j).toString());


    }

    for (int i = 0; i < table.getRowCount(); i++) {
        row = spreadsheet.createRow(i + 1);
        for (int j = 0; j < table.getColumns().size(); j++) {
            if (table.getValueAt(i, j) != null) {
       
            		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());

            } else {
                row.createCell(j).setCellValue("");
            }
        }
    }
    int rowNum = 0;
    for (short i = spreadsheet.getRow(0).getFirstCellNum(),
            end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
        CellRangeAddress ca
                = new CellRangeAddress(0, rowNum,
                        spreadsheet.getRow(0).getFirstCellNum(),
                        spreadsheet.getRow(0).getLastCellNum());

        rowNum++;

    }
    for (short i = spreadsheet.getRow(0).getFirstCellNum(),
            end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
        spreadsheet.autoSizeColumn(i);
    }
    try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
    	
        workbook.write(fileOut);
        fileOut.flush();
        fileOut.close();
        Desktop.getDesktop().open(new File(titre+".xls"));

    } catch (Exception e) {
		// TODO: handle exception
	}
		
	
}


public static void tabletoexcelMPNonUtiliser(JXTable table , String titre , String titrefeuille )
{
	
if(table.getRowCount()==0)
{
JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
return;
}
	

    int number = workbook.getNumberOfSheets();
    if (number == 1) {
        workbook.removeSheetAt(0);
    }
    org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

    org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

/*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
    ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
    ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
    ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

    Font font1 = (Font) workbook.createFont();
    ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
    ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
    ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
  
    for (int j = 0; j < table.getColumns().size(); j++) {
        row.createCell(j).setCellValue(table.getColumnName(j).toString());


    }

    for (int i = 0; i < table.getRowCount(); i++) {
        row = spreadsheet.createRow(i + 1);
        for (int j = 0; j < table.getColumns().size(); j++) {
            if (table.getValueAt(i, j) != null) {
            	
            	
            	if(j>=2)
                row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString().replaceAll(" ", "").replaceAll(",", ".")));
            	else
            		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());

            } else {
                row.createCell(j).setCellValue("");
            }
        }
    }
    
    row = spreadsheet.createRow(table.getRowCount()+2);
    row.createCell(0).setCellValue("Montant Total");   
   
    row.createCell(4).setCellFormula("SUM(E"+(1)+":E"+(table.getRowCount()+1)+")");  
  
    
    
    int rowNum = 0;
    for (short i = spreadsheet.getRow(0).getFirstCellNum(),
            end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
        CellRangeAddress ca
                = new CellRangeAddress(0, rowNum,
                        spreadsheet.getRow(0).getFirstCellNum(),
                        spreadsheet.getRow(0).getLastCellNum());

        rowNum++;

    }
    for (short i = spreadsheet.getRow(0).getFirstCellNum(),
            end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
        spreadsheet.autoSizeColumn(i);
    }
    try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
    	
        workbook.write(fileOut);
        fileOut.flush();
        fileOut.close();
        Desktop.getDesktop().open(new File(titre+".xls"));

    } catch (Exception e) {
		// TODO: handle exception
	}
		
	
}


public static void tabletoexcelEtatStockMP(JXTable table , String titre , String titrefeuille )
{
	
if(table.getRowCount()==0)
{
JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
return;
}
	

    int number = workbook.getNumberOfSheets();
    if (number == 1) {
        workbook.removeSheetAt(0);
    }
    org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

    org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

/*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
    ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
    ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
    ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

    Font font1 = (Font) workbook.createFont();
    ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
    ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
    ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
  
    for (int j = 0; j < table.getColumns().size(); j++) {
        row.createCell(j).setCellValue(table.getColumnName(j).toString());


    }

    for (int i = 0; i < table.getRowCount(); i++) {
        row = spreadsheet.createRow(i + 1);
        for (int j = 0; j < table.getColumns().size(); j++) {
            if (table.getValueAt(i, j) != null) {
            	
            	
            	if(j>1)
                row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString().replaceAll(" ", "").replaceAll(",", ".")));
            	else
            		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());

            } else {
                row.createCell(j).setCellValue("");
            }
        }
    }
    int rowNum = 0;
    for (short i = spreadsheet.getRow(0).getFirstCellNum(),
            end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
        CellRangeAddress ca
                = new CellRangeAddress(0, rowNum,
                        spreadsheet.getRow(0).getFirstCellNum(),
                        spreadsheet.getRow(0).getLastCellNum());

        rowNum++;

    }
    for (short i = spreadsheet.getRow(0).getFirstCellNum(),
            end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
        spreadsheet.autoSizeColumn(i);
    }
    try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
    	
        workbook.write(fileOut);
        fileOut.flush();
        fileOut.close();
        Desktop.getDesktop().open(new File(titre+".xls"));

    } catch (Exception e) {
		// TODO: handle exception
	}
		
	
}


public static void tabletoexcelListProduitFiniNonFabrique(JXTable table , String titre , String titrefeuille )
{
	
if(table.getRowCount()==0)
{
JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
return;
}
	

    int number = workbook.getNumberOfSheets();
    if (number == 1) {
        workbook.removeSheetAt(0);
    }
    org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

    org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

/*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
    ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
    ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
    ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

    Font font1 = (Font) workbook.createFont();
    ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
    ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
    ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
  
    
   
    
   
    for (int j = 0; j < table.getColumns().size(); j++) {
        row.createCell(j).setCellValue(table.getColumnName(j).toString());


    }

    for (int i = 0; i < table.getRowCount(); i++) {
        row = spreadsheet.createRow(i + 1);
        for (int j = 0; j < table.getColumns().size(); j++) {
            if (table.getValueAt(i, j) != null) {
            	
            	
            	if(j>1)
            	{
            		
            		 HSSFCell dataCell1 = (HSSFCell) row.createCell(j);
                  	
                     dataCell1.setCellValue(Double.valueOf(table.getValueAt(i, j).toString().replaceAll(" ", "").replaceAll(",", ".")));
             	
                 	dataCell1.getCellStyle().setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
            		
            	}
                
            	else
            	{
            		row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
            	}
            		 

            } else {
                row.createCell(j).setCellValue("");
            }
        }
    }
    
    
    row = spreadsheet.createRow(table.getRowCount()+2);
    row.createCell(0).setCellValue("Total");   
   
    row.createCell(2).setCellFormula("SUM(C"+(2)+":C"+(table.getRowCount()+2)+")");  
    row.createCell(3).setCellFormula("SUM(D"+(2)+":D"+(table.getRowCount()+2)+")");  
    row.createCell(4).setCellFormula("SUM(E"+(2)+":E"+(table.getRowCount()+2)+")"); 
    row.createCell(5).setCellFormula("SUM(F"+(2)+":F"+(table.getRowCount()+2)+")"); 
    row.createCell(6).setCellFormula("SUM(G"+(2)+":G"+(table.getRowCount()+2)+")"); 
    
    
    
    
    
    
    
    int rowNum = 0;
    for (short i = spreadsheet.getRow(0).getFirstCellNum(),
            end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
        CellRangeAddress ca
                = new CellRangeAddress(0, rowNum,
                        spreadsheet.getRow(0).getFirstCellNum(),
                        spreadsheet.getRow(0).getLastCellNum());

        rowNum++;

    }
    for (short i = spreadsheet.getRow(0).getFirstCellNum(),
            end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
        spreadsheet.autoSizeColumn(i);
    }
    try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
    	
        workbook.write(fileOut);
        fileOut.flush();
        fileOut.close();
        Desktop.getDesktop().open(new File(titre+".xls"));

    } catch (Exception e) {
		// TODO: handle exception
	}
		
	
}



public static void tabletoexcelListDetailProduitFiniNonFabrique(JXTable table , String titre , String titrefeuille , String nomArticle)
{
	
if(table.getRowCount()==0)
{
JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
return;
}
	

    int number = workbook.getNumberOfSheets();
    if (number == 1) {
        workbook.removeSheetAt(0);
    }
    org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

    org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

/*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
    ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
    ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
    ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

    Font font1 = (Font) workbook.createFont();
    ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
    ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
    ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
    row = spreadsheet.createRow(2);
    row.createCell(0).setCellValue(nomArticle);
    row = spreadsheet.createRow(4);
    for (int j = 0; j < table.getColumns().size(); j++) {
        row.createCell(j).setCellValue(table.getColumnName(j).toString());


    }

    for (int i = 0; i < table.getRowCount(); i++) {
        row = spreadsheet.createRow(i + 5);
        for (int j = 0; j < table.getColumns().size(); j++) {
            if (table.getValueAt(i, j) != null) {
            	
            	
            	if(j>1)
            	{
            		
            		 HSSFCell dataCell1 = (HSSFCell) row.createCell(j);
                 	
                     dataCell1.setCellValue(Double.valueOf(table.getValueAt(i, j).toString().replaceAll(" ", "").replaceAll(",", ".")));
             	
                 	dataCell1.getCellStyle().setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
            		
            	}else
            	{
            		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
            	}
            		
            		
               
            		

            } else {
                row.createCell(j).setCellValue("");
            }
        }
    }
    
    row = spreadsheet.createRow(table.getRowCount()+5);
    row.createCell(0).setCellValue("Total");   
   
    row.createCell(2).setCellFormula("SUM(C"+(5)+":C"+(table.getRowCount()+5)+")");  
   
    row.createCell(4).setCellFormula("SUM(E"+(5)+":E"+(table.getRowCount()+5)+")"); 
   
    
    
    
    int rowNum = 0;
    for (short i = spreadsheet.getRow(0).getFirstCellNum(),
            end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
        CellRangeAddress ca
                = new CellRangeAddress(0, rowNum,
                        spreadsheet.getRow(0).getFirstCellNum(),
                        spreadsheet.getRow(0).getLastCellNum());

        rowNum++;

    }
    for (short i = spreadsheet.getRow(0).getFirstCellNum(),
            end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
        spreadsheet.autoSizeColumn(i);
    }
    try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
    	
        workbook.write(fileOut);
        fileOut.flush();
        fileOut.close();
        Desktop.getDesktop().open(new File(titre+".xls"));

    } catch (Exception e) {
		// TODO: handle exception
	}
		
	
}

public static void tabletoexcelSituationManqueEtDechetEmballage(JXTable table , String titre , String titrefeuille )
{
	
if(table.getRowCount()==0)
{
JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
return;
}
	

    int number = workbook.getNumberOfSheets();
    if (number == 1) {
        workbook.removeSheetAt(0);
    }
    org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

    org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

/*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
    ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
    ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
    ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

    Font font1 = (Font) workbook.createFont();
    ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
    ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
    ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
  
    for (int j = 0; j < table.getColumns().size(); j++) {
        row.createCell(j).setCellValue(table.getColumnName(j).toString());


    }

    for (int i = 0; i < table.getRowCount(); i++) {
        row = spreadsheet.createRow(i + 1);
        for (int j = 0; j < table.getColumns().size(); j++) {
            if (table.getValueAt(i, j) != null) {
            	
            	
            	if(j>6)
                row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString().replaceAll(" ", "").replaceAll(",", ".")));
            	else
            		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());

            } else {
                row.createCell(j).setCellValue("");
            }
        }
    }
    int rowNum = 0;
    for (short i = spreadsheet.getRow(0).getFirstCellNum(),
            end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
        CellRangeAddress ca
                = new CellRangeAddress(0, rowNum,
                        spreadsheet.getRow(0).getFirstCellNum(),
                        spreadsheet.getRow(0).getLastCellNum());

        rowNum++;

    }
    for (short i = spreadsheet.getRow(0).getFirstCellNum(),
            end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
        spreadsheet.autoSizeColumn(i);
    }
    try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
    	
        workbook.write(fileOut);
        fileOut.flush();
        fileOut.close();
        Desktop.getDesktop().open(new File(titre+".xls"));

    } catch (Exception e) {
		// TODO: handle exception
	}
		
	
}


public static void tabletoexcelEtatStockMPParFournisseur(JXTable table , String titre , String titrefeuille )
{
	
if(table.getRowCount()==0)
{
JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
return;
}
	

    int number = workbook.getNumberOfSheets();
    if (number == 1) {
        workbook.removeSheetAt(0);
    }
    org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

    org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

/*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
    ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
    ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
    ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

    Font font1 = (Font) workbook.createFont();
    ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
    ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
    ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
  
    for (int j = 0; j < table.getColumns().size(); j++) {
        row.createCell(j).setCellValue(table.getColumnName(j).toString());


    }

    for (int i = 0; i < table.getRowCount(); i++) {
        row = spreadsheet.createRow(i + 1);
        for (int j = 0; j < table.getColumns().size(); j++) {
            if (table.getValueAt(i, j) != null) {
            	
            	
            	if(j>2)
                row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString().replaceAll(" ", "").replaceAll(",", ".")));
            	else
            		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());

            } else {
                row.createCell(j).setCellValue("");
            }
        }
    }
    int rowNum = 0;
    for (short i = spreadsheet.getRow(0).getFirstCellNum(),
            end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
        CellRangeAddress ca
                = new CellRangeAddress(0, rowNum,
                        spreadsheet.getRow(0).getFirstCellNum(),
                        spreadsheet.getRow(0).getLastCellNum());

        rowNum++;

    }
    for (short i = spreadsheet.getRow(0).getFirstCellNum(),
            end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
        spreadsheet.autoSizeColumn(i);
    }
    try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
    	
        workbook.write(fileOut);
        fileOut.flush();
        fileOut.close();
        Desktop.getDesktop().open(new File(titre+".xls"));

    } catch (Exception e) {
		// TODO: handle exception
	}
		
	
}



public static void tabletoexcelListeMMPr(JXTable table , String titre , String titrefeuille )
{
	
if(table.getRowCount()==0)
{
JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
return;
}
	

    int number = workbook.getNumberOfSheets();
    if (number == 1) {
        workbook.removeSheetAt(0);
    }
    org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

    org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

/*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
    ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
    ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
    ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

    Font font1 = (Font) workbook.createFont();
    ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
    ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
    ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
  
    for (int j = 0; j < table.getColumns().size(); j++) {
        row.createCell(j).setCellValue(table.getColumnName(j).toString());


    }

    for (int i = 0; i < table.getRowCount(); i++) {
        row = spreadsheet.createRow(i + 1);
        for (int j = 0; j < table.getColumns().size(); j++) {
            if (table.getValueAt(i, j) != null) {
            	
            	
            	if(j==5)
                row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString().replaceAll(" ", "").replaceAll(",", ".")));
            	else
            		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());

            } else {
                row.createCell(j).setCellValue("");
            }
        }
    }
    int rowNum = 0;
    for (short i = spreadsheet.getRow(0).getFirstCellNum(),
            end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
        CellRangeAddress ca
                = new CellRangeAddress(0, rowNum,
                        spreadsheet.getRow(0).getFirstCellNum(),
                        spreadsheet.getRow(0).getLastCellNum());

        rowNum++;

    }
    for (short i = spreadsheet.getRow(0).getFirstCellNum(),
            end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
        spreadsheet.autoSizeColumn(i);
    }
    try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
    	
        workbook.write(fileOut);
        fileOut.flush();
        fileOut.close();
        Desktop.getDesktop().open(new File(titre+".xls"));

    } catch (Exception e) {
		// TODO: handle exception
	}
		
	
}


public static void tabletoexcelStatistiqueEnVracUtiliserLorsDeLaProduction(JXTable table , String titre , String titrefeuille)
{
	
if(table.getRowCount()==0)
{
JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
return;
}
	

    int number = workbook.getNumberOfSheets();
    if (number == 1) {
        workbook.removeSheetAt(0);
    }
    org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

    org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

/*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
    ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
    ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
    ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

    Font font1 = (Font) workbook.createFont();
    ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
    ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
    ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
  
    for (int j = 0; j < table.getColumns().size(); j++) {
        row.createCell(j).setCellValue(table.getColumnName(j).toString());


    }

    for (int i = 0; i < table.getRowCount(); i++) {
        row = spreadsheet.createRow(i + 1);
        for (int j = 0; j < table.getColumns().size(); j++) {
            if (table.getValueAt(i, j) != null) {
            	
            	/*
            	if(j>0)
            		if(!table.getValueAt(i, j).toString().equals("-"))
            		{
            			 row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
            		}else
            		{
            			row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
            		}
               
            	else*/
            		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());

            } else {
                row.createCell(j).setCellValue("");
            }
        }
    }
    int rowNum = 0;
    for (short i = spreadsheet.getRow(0).getFirstCellNum(),
            end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
        CellRangeAddress ca
                = new CellRangeAddress(0, rowNum,
                        spreadsheet.getRow(0).getFirstCellNum(),
                        spreadsheet.getRow(0).getLastCellNum());

        rowNum++;

    }
    for (short i = spreadsheet.getRow(0).getFirstCellNum(),
            end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
        spreadsheet.autoSizeColumn(i);
    }
    try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
    	
        workbook.write(fileOut);
        fileOut.flush();
        fileOut.close();
        Desktop.getDesktop().open(new File(titre+".xls"));

    } catch (Exception e) {
		// TODO: handle exception
	}
		
	
}





}





package com.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public static FileInputStream fin;
	public static FileOutputStream fout;
	public static Workbook wb;
	public static Sheet sh;
	public static Cell cell;
	
	
	public int getRowCount(String xlfile, String sheetName) throws IOException {
		
		fin = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fin);
		//wb = WorkbookFactory.create(xlfile);
		sh = wb.getSheet(sheetName);
		int rowCount = sh.getLastRowNum();
		wb.close();
		fin.close();
		return rowCount;
				
	}
	
	public int getCellCount(String xlfile,String sheetName,int rownum) throws IOException {
		
		fin = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fin);
		sh = wb.getSheet(sheetName);
		int cellCount = sh.getRow(rownum).getLastCellNum();
		wb.close();
		fin.close();
		return cellCount;
	}
	
    public static Object getCell(String xlfile,String sheetName,int rownum,int colNum) throws IOException {
    	Object cellContent=null;
    	fin = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fin);
		sh = wb.getSheet(sheetName);
		
		CellType type = sh.getRow(rownum).getCell(colNum).getCellType();
		
		if(type==CellType.NUMERIC) {
			 
			 cellContent = sh.getRow(rownum).getCell(colNum).getNumericCellValue();
			
		}
		
		 if(type==CellType.STRING) {
			 
			 cellContent = sh.getRow(rownum).getCell(colNum).getStringCellValue();
			
		}
		
		 
		wb.close();
		fin.close();
		
		return cellContent;
		
    	
	}
 
    public void setCell(String xlfile,String sheetName,int rownum,int colNum, String Val) throws IOException {
		
    	fin = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fin);
		sh = wb.getSheet(sheetName);
		sh.getRow(rownum).createCell(colNum).setCellValue(Val);
		fout = new FileOutputStream(xlfile);
		wb.write(fout);
		wb.close();
		fin.close();
		fout.close();
	}
}

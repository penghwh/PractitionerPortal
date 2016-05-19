package com.bailian.ta.util;

import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bailian.ta.log.LogUtil;


public class ExcelUtil {
	
	/*************************************************************************************************
	 * @author Ink
	 * @date 2015/11/12
	 * @param String path
	 * @return XSSFWorkbook book
	 * 
	 *    The method need the path of the excel file, then will load the excel from disk and 
	 *    return a XSSFWorkbook object.
	 * 
	************************************************************************************************/
	public static XSSFWorkbook loadExcel(String path){
		XSSFWorkbook book = null;
		try {
			FileInputStream ExcelFile = new FileInputStream(path);
			book = new XSSFWorkbook(ExcelFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}
	
	
	
	/*************************************************************************************************
	 * @author Ink
	 * @date 2015/11/12
	 * @param String sheetName, XSSFWorkbook book
	 * @return XSSFSheet sheet
	 * 
	 *    The method need a XSSFSheet object and sheet name as parameter, then will return the
	 *    sheet as a XSSFSheet object.
	 * 
	************************************************************************************************/
	public static XSSFSheet loadSheet(String sheetName, XSSFWorkbook book){
		XSSFSheet sheet = null;
		try{
			sheet = book.getSheet(sheetName);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return sheet;
	}
	
	
	
	/*************************************************************************************************
	 * @author Ink
	 * @date 2015/11/12
	 * @param int row, int col, XSSFSheet sheet
	 * @return XSSFSheet sheet
	 * 
	 *    The method need a XSSFSheet object, row number and col number, then will return the
	 *    appointed cell's data as string. Need to pay attention to the data type of the cell
	 *    must be string.
	 * 
	************************************************************************************************/
	public static String getCellData(int row, int col, XSSFSheet sheet){
		row -= 1;
		col -= 1;
		String data = null;
		try{
			XSSFCell cell = sheet.getRow(row).getCell(col);
			data = cell.getStringCellValue();
		}
		catch(java.lang.NullPointerException e){
			LogUtil.warn("No Information in Sheet: " + sheet.getSheetName() + ", Row: " + (row + 1) + ", Col: " + (col + 1) );
			data = "No Information";
		}
		return data;
	}
	
	
	/*************************************************************************************************
	 * @author Ink
	 * @date 2015/11/12
	 * @param int row, int col, XSSFSheet sheet
	 * @return XSSFSheet sheet
	 * 
	 *    The method need a XSSFSheet object, row number and col number, then will return the
	 *    appointed cell's data as string. Need to pay attention to the data type of the cell
	 *    must be string.
	 * 
	************************************************************************************************/
	public static int getLastRow(XSSFSheet sheet){
		int lastRow = 0;
		try{
			lastRow = sheet.getLastRowNum();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return lastRow + 1;
	}
	
	
	/*************************************************************************************************
	 * @author Ink
	 * @date 2015/11/12
	 * @param XSSFRow row
	 * @return int lastCell
	 * 
	 *    The method need a XSSFRow as parameter, and will return the row's last cell. What need to 
	 *    pay attention is the last cell number is same as Microsoft Excel, the index begin with 1, not
	 *    0.
	 * 
	************************************************************************************************/
	public static int getLastCell(XSSFRow row){
		int lastCell = 0;
		try{
			lastCell = row.getLastCellNum();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return lastCell + 1;
	}
	
	public static XSSFWorkbook getBook(){
		XSSFWorkbook book = new XSSFWorkbook();
		return book;
	}
	
	public static XSSFSheet getSheet(String sheetName, XSSFWorkbook book){
		XSSFSheet sheet = book.createSheet(sheetName);
		return sheet;
	}
	
	public static XSSFRow getRow(int rowNum, XSSFSheet sheet){
		XSSFRow row = sheet.createRow(rowNum);
		return row;
	}
	
	public static XSSFCell getCell(int cellNum, XSSFRow row){
		XSSFCell cell = row.createCell(cellNum);
		return cell;
	}
	

}

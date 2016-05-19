package com.bailian.ta.report.excel;

import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bailian.ta.config.FrameConfig;
import com.bailian.ta.report.NameUtil;
import com.bailian.ta.util.FileUtil;



public class ExcelReportUtil {
	
	public static XSSFWorkbook book;
	public static XSSFSheet esheet;
	public static XSSFSheet osheet;
	public static XSSFRow eRow;
	public static XSSFRow oRow;
	public static XSSFCell eCell;
	public static XSSFCell oCell;
	public static int rowNum;
	public static int colNum;
	

	
	public static void initExcel(){
		book = new XSSFWorkbook();
		osheet = book.createSheet("Overview");
		esheet = book.createSheet("ExecuteReport");
	}
	
	public static void createRow(int rownum){
		eRow = esheet.createRow(rownum);
	}
	
	public static void createCell(int cellnum){
		eCell = eRow.createCell(cellnum);
	}
	
	public static void createOverRow(int rownum){
		oRow = osheet.createRow(rownum);
	}
	
	public static void createOverCell(int cellnum){
		oCell = oRow.createCell(cellnum);
	}
	
	public static void initOverView(){
		for(int i = 0; i < 30; i++){
			createOverRow(i);
			for(int j = 0; j < 20; j++){
				createOverCell(j);
			}
		}
	}
	
	public static void initTable(int rownum, int colnum){
		rownum -= 1;
		colnum -= 1;
		for(int i = 0; i <= rownum; i++){
			createRow(i);
			for(int j = 0; j <= colnum; j++){
				createCell(j);
			}
		}
		
	}
	
	public static void setTopBorder(int rownum, int scell, int ecell){
		rownum -= 1;
		scell -= 1;
		ecell -= 1;
		XSSFCellStyle style = book.createCellStyle();
		style.setBorderTop(XSSFCellStyle.BORDER_THIN);
		style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		oRow = osheet.getRow(rownum);
		for(int i = scell; i <= ecell; i++){
			oCell = oRow.getCell(i);
			oCell.setCellStyle(style);
		}
	}
	
	public static void setBottomBorder(int rownum, int scell, int ecell){
		rownum -= 1;
		scell -= 1;
		ecell -= 1;
		XSSFCellStyle style = book.createCellStyle();
		style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		oRow = osheet.getRow(rownum);
		for(int i = scell; i <= ecell; i++){
			oCell = oRow.getCell(i);
			oCell.setCellStyle(style);
		}
	}
	
	public static void setLeftBorder(int rownum, int scell, int ecell){
		rownum -= 1;
		scell -= 1;
		ecell -= 1;
		XSSFCellStyle style = book.createCellStyle();
		style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		oRow = osheet.getRow(rownum);
		for(int i = scell; i <= ecell; i++){
			oCell = oRow.getCell(i);
			oCell.setCellStyle(style);
		}
	}
	
	public static void setRightBorder(int rownum, int scell, int ecell){
		rownum -= 1;
		scell -= 1;
		ecell -= 1;
		XSSFCellStyle style = book.createCellStyle();
		style.setBorderRight(XSSFCellStyle.BORDER_THIN);
		style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		oRow = osheet.getRow(rownum);
		for(int i = scell; i <= ecell; i++){
			oCell = oRow.getCell(i);
			oCell.setCellStyle(style);
		}
	}
	
	public static void setColor(int rownum, int cell, short cellcolor){
		rownum -= 1;
		cell -= 1;
		XSSFCellStyle style = book.createCellStyle();
		style.setBorderRight(XSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		style.setBorderTop(XSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(cellcolor);
		style.setTopBorderColor(cellcolor);
		style.setLeftBorderColor(cellcolor);
		style.setRightBorderColor(cellcolor);
		style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(cellcolor);
		oRow = osheet.getRow(rownum);
		oCell = oRow.getCell(cell);
		oCell.setCellStyle(style);
		
	}
	
	public static void rightBottomBorder(int rownum, int cell){
		rownum -= 1;
		cell -= 1;
		XSSFCellStyle style = book.createCellStyle();
		style.setBorderRight(XSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		oRow = osheet.getRow(rownum);
		oCell = oRow.getCell(cell);
		oCell.setCellStyle(style);
	}
	
	public static void rightTopBorder(int rownum, int cell){
		rownum -= 1;
		cell -= 1;
		XSSFCellStyle style = book.createCellStyle();
		style.setBorderRight(XSSFCellStyle.BORDER_THIN);
		style.setBorderTop(XSSFCellStyle.BORDER_THIN);
		style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		oRow = osheet.getRow(rownum);
		oCell = oRow.getCell(cell);
		oCell.setCellStyle(style);
	}
	
	public static void setOverRowStyle(short font,boolean bold,int rownum, int startcell, int endcell, short align){
		rownum -= 1;
		startcell -= 1;
		endcell -= 1;
		XSSFCellStyle style = book.createCellStyle();
		Font rowFont = book.createFont();
		rowFont.setFontHeight(font);
		rowFont.setBold(bold);
		style.setAlignment(align);
		style.setFont(rowFont);
		style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		oRow = osheet.getRow(rownum);
		for(int i = startcell; i <= endcell; i++){
			oCell = oRow.getCell(i);
			oCell.setCellStyle(style);
		}
	}
	
	public static void setRowStyle(short font,boolean bold,int rownum, int startcell, int endcell, short align){
		rownum -= 1;
		startcell -= 1;
		endcell -= 1;
		XSSFCellStyle style = book.createCellStyle();
		Font rowFont = book.createFont();
		rowFont.setFontHeight(font);
		rowFont.setBold(bold);
		style.setAlignment(align);
		style.setFont(rowFont);
		eRow = esheet.getRow(rownum);
		for(int i = startcell; i <= endcell; i++){
			eCell = eRow.getCell(i);
			eCell.setCellStyle(style);
		}
	}
	
	public static void setRowStyle(short fontcolor, boolean bold,int rownum, int startcell, int endcell){
		rownum -= 1;
		startcell -= 1;
		endcell -= 1;
		XSSFCellStyle style = book.createCellStyle();
		Font rowFont = book.createFont();
		rowFont.setColor(fontcolor);
		rowFont.setBold(bold);
		style.setFont(rowFont);
		eRow = esheet.getRow(rownum);
		for(int i = startcell; i <= endcell; i++){
			eCell = eRow.getCell(i);
			eCell.setCellStyle(style);
		}
	}
	
	
	
	
	public static void setRowStyle(short font, short cellcolor, short fontcolor, boolean bold,int rownum, int startcell, int endcell, short align){
		rownum -= 1;
		startcell -= 1;
		endcell -= 1;
		XSSFCellStyle style = book.createCellStyle();
		Font rowFont = book.createFont();
		rowFont.setFontHeight(font);
		rowFont.setColor(fontcolor);
		rowFont.setBold(bold);
		style.setAlignment(align);
		style.setFont(rowFont);
		style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(cellcolor);
		eRow = esheet.getRow(rownum);
		for(int i = startcell; i <= endcell; i++){
			eCell = eRow.getCell(i);
			eCell.setCellStyle(style);
		}
	}
	
	public static void setHyperLink(String value, String url, int rownum, int colunm){
		rownum -= 1;
		colunm -= 1;
		eRow = esheet.getRow(rownum);
		eCell = eRow.getCell(colunm);
		eCell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		eCell.setCellFormula("HYPERLINK(\"" + url + "\",\"" + value + "\")");
	}

	
	public static void setValue(String value, int rownum, int colnum){
		rownum -= 1;
		colnum -= 1;
		eRow = esheet.getRow(rownum);
		eCell = eRow.getCell(colnum);
		eCell.setCellValue(value);
	}
	
	public static void setOverview(String value, int rownum, int colnum){
		rownum -= 1;
		colnum -= 1;
		oRow = osheet.getRow(rownum);
		oCell = oRow.getCell(colnum);
		oCell.setCellValue(value);
	}
	
	public static void saveFile() {
		String fileName = NameUtil.getERepotrName() + ".xlsx";
		FileUtil.createPath(FrameConfig.getInstance().getConfig("ReportPath"));
		String filePath = FileUtil.getAbsolutelyPath(FrameConfig.getInstance().getConfig("ReportPath"), fileName);
		try {
			FileOutputStream fout = new FileOutputStream(filePath);
			book.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public static void setColumnWidth(int col, int width){
		width = (width > 255 ? 255 : width);
		esheet.setColumnWidth(col - 1, width * 256);
	}
	
	public static void setOverviewColumnWidth(int col, int width){
		width = (width > 255 ? 255 : width);
		osheet.setColumnWidth(col - 1, width * 256);
	}

}

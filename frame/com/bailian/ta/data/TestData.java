package com.bailian.ta.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bailian.ta.util.ExcelUtil;
import com.bailian.ta.util.FileUtil;


public class TestData {
	private XSSFSheet sheet;
	private XSSFWorkbook book;
	
	public void loadData(String fileName, String sheetName){
		String path = FileUtil.getAbsolutelyPath("test-data/" + fileName);
		this.book = ExcelUtil.loadExcel(path);
		this.sheet = book.getSheet(sheetName);
	}
	
	public String getData(String caseName, String field){
		String rs = null;
		for(int i = 2; i <= ExcelUtil.getLastRow(this.sheet); i++){
			if(ExcelUtil.getCellData(i, 1, this.sheet).equals(caseName) &&
					ExcelUtil.getCellData(i, 2, this.sheet).equals(field)){
				rs = ExcelUtil.getCellData(i, 3, this.sheet);
			}
		}
		return rs;
	}
	
	
	public String getData(int rowNum, int colNum){
		String rs  = ExcelUtil.getCellData(rowNum, colNum, this.sheet);
		return rs;
	}
	
	public List<String> getRowData(int row){
		List<String> list = new ArrayList<String>();
		for(int i = 1; i <= ExcelUtil.getLastCell(this.sheet.getRow(row)); i++){
			String rs = null;
			if(ExcelUtil.getCellData(row, i, this.sheet).equals("")){
				break;
			}
			else{
				rs = ExcelUtil.getCellData(i, row, this.sheet);
				list.add(rs);
			}
		}
		return list;
	}
	
	public List<String> getColData(int col){
		List<String> list = new ArrayList<String>();
		for(int i = 1; i <= ExcelUtil.getLastRow(this.sheet); i++){
			String rs = null;
			if(ExcelUtil.getCellData(i, col, this.sheet).equals("")){
				break;
			}
			else{
				rs = ExcelUtil.getCellData(i, col, this.sheet);
				list.add(rs);
			}
		}
		return list;
	}
}

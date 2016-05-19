package com.bailian.ta.report.excel;

import java.util.List;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import com.bailian.ta.config.FrameConfig;
import com.bailian.ta.log.LogUtil;
import com.bailian.ta.report.OverviewGenerator;
import com.bailian.ta.report.model.CaseModel;
import com.bailian.ta.report.model.OverviewModel;
import com.bailian.ta.report.model.TestResult;
import com.bailian.ta.report.model.TestStatus;
import com.bailian.ta.util.TimeUtil;

public class ExcelReportGenerator {
	
	private List<CaseModel> caseList;
	private OverviewModel overview;
	private OverviewGenerator overviewGenerator;
	private int rowNum = 1;
	
	public ExcelReportGenerator(List<CaseModel> list){
		this.caseList = list;
		this.overviewGenerator = new OverviewGenerator(caseList);
		this.overview = overviewGenerator.getOverview();
	}
	
	public void createExcelReport(){
		try{
			initDetailSheet();
			printDetailSheet();
			initOverviewSheet();
			printOverviewSheet();
			saveExcel();
			LogUtil.frameLog("Save Excel Report Succeed");
		}
		catch(Exception e){
			LogUtil.error("Save Excel Report Failed");
			e.printStackTrace();
		}
		
	}
	
	
	private void saveExcel(){
		ExcelReportUtil.saveFile();
	}
	
	private void initOverviewSheet(){
		ExcelReportUtil.initOverView();
		ExcelReportUtil.setOverviewColumnWidth(3, 17);
		ExcelReportUtil.setOverviewColumnWidth(6, 13);
		ExcelReportUtil.setOverviewColumnWidth(9, 17);
		ExcelReportUtil.setOverviewColumnWidth(12, 13);
		ExcelReportUtil.setOverviewColumnWidth(15, 10);
		ExcelReportUtil.setTopBorder(2, 2, 17);
		ExcelReportUtil.setBottomBorder(12, 2, 17);
		
		for(int i = 2; i < 13; i++){
			ExcelReportUtil.setRightBorder(i, 17, 17);
		}
		
		ExcelReportUtil.rightBottomBorder(12, 17);
		ExcelReportUtil.rightTopBorder(2, 17);
		
	}
	
	private void printOverviewSheet(){
		ExcelReportUtil.setOverview(FrameConfig.getInstance().getConfig("Report_title"), 3, 3);
		ExcelReportUtil.setOverRowStyle((short)400, false, 3, 3, 16, XSSFCellStyle.ALIGN_LEFT);
		
		ExcelReportUtil.setOverview("计算机名:", 6, 3);
		ExcelReportUtil.setOverview(overview.getPcName(), 6, 4);
		ExcelReportUtil.setOverview("计算机IP:", 6, 6);
		ExcelReportUtil.setOverview(overview.getPcIp(), 6, 7);
		ExcelReportUtil.setOverview("操作系统:", 6, 9);
		ExcelReportUtil.setOverview(overview.getPcOs(), 6, 10);
		ExcelReportUtil.setOverview("生成时间:", 6, 12);
		ExcelReportUtil.setOverview(overview.getTime(), 6, 13);
		
		ExcelReportUtil.setOverview("用例总数:", 8, 3);
		ExcelReportUtil.setOverview(overview.getCaseNum(), 8, 4);
		ExcelReportUtil.setOverview("用例通过:", 8, 6);
		ExcelReportUtil.setOverview(overview.getCasePassNum(), 8, 7);
		ExcelReportUtil.setOverview("用例失败:", 8, 9);
		ExcelReportUtil.setOverview(overview.getCaseFailNum(), 8, 10);
		ExcelReportUtil.setOverview("用例跳过:", 8, 12);
		ExcelReportUtil.setOverview(overview.getCaseSkipNum(), 8, 13);
		ExcelReportUtil.setOverview("通过率:", 8, 15);
		ExcelReportUtil.setOverview(overview.getCasePassRate(), 8, 16);
		
		ExcelReportUtil.setOverview("Generate by Automation Test Framework", 17, 13);
		for(int  k = 4; k < 12; k++){
			ExcelReportUtil.setOverRowStyle((short)210, false, k, 3, 16, XSSFCellStyle.ALIGN_LEFT);
		}
		
		for(int j = 2; j < 13; j++){
			if(overview.getStatus().toString().equals(TestStatus.Prefect.toString())){
				ExcelReportUtil.setColor(j, 2, IndexedColors.GREEN.getIndex());
			}
			if(overview.getStatus().toString().equals(TestStatus.Good.toString())){
				ExcelReportUtil.setColor(j, 2, IndexedColors.ORANGE.getIndex());
			}
			if(overview.getStatus().toString().equals(TestStatus.Bad.toString())){
				ExcelReportUtil.setColor(j, 2, IndexedColors.RED.getIndex());
			}
			
		}
		
	}
	
	private void initDetailSheet(){
		ExcelReportUtil.initExcel();
		ExcelReportUtil.initTable(overviewGenerator.getRowNum(), 8);
		ExcelReportUtil.setColumnWidth(1, 12);
		ExcelReportUtil.setColumnWidth(2, 20);
		ExcelReportUtil.setColumnWidth(3, 30);
		ExcelReportUtil.setColumnWidth(4, 15);
		ExcelReportUtil.setColumnWidth(5, 13);
		ExcelReportUtil.setColumnWidth(6, 25);
		ExcelReportUtil.setColumnWidth(7, 15);
	}
	
	private void printDetailSheet(){
		for(int i = 0; i < caseList.size(); i ++){
			ExcelReportUtil.setValue("Case Name", rowNum, 1);
			ExcelReportUtil.setValue(caseList.get(i).getCaseName(), rowNum, 2);
			ExcelReportUtil.setRowStyle((short)210, IndexedColors.GREY_25_PERCENT.getIndex(), 
					IndexedColors.BLACK.getIndex(), false, rowNum, 1, 1, XSSFCellStyle.ALIGN_LEFT );
			rowNum += 1;
			ExcelReportUtil.setValue(caseList.get(i).getResult().toString(), rowNum, 1);
			ExcelReportUtil.setValue("Operation", rowNum, 2);
			ExcelReportUtil.setValue("Result", rowNum, 5);
			ExcelReportUtil.setValue("Execute Time", rowNum, 6);
			ExcelReportUtil.setValue("Screen Shot", rowNum, 7);
			ExcelReportUtil.setValue("Detail", rowNum, 8);
			ExcelReportUtil.setRowStyle((short)210, IndexedColors.GREY_25_PERCENT.getIndex(),
					IndexedColors.BLACK.getIndex(), false, rowNum, 2, 8, XSSFCellStyle.ALIGN_LEFT );
			if(caseList.get(i).getResult().toString().equals(TestResult.Passed.toString())){
				ExcelReportUtil.setRowStyle((short)210, IndexedColors.GREEN.getIndex(),
						IndexedColors.BLACK.getIndex(), true, rowNum, 1, 1, XSSFCellStyle.ALIGN_CENTER);
			}
			else if(caseList.get(i).getResult().toString().equals(TestResult.Skipped.toString())){
				ExcelReportUtil.setRowStyle((short)210, IndexedColors.YELLOW.getIndex(), 
						IndexedColors.BLACK.getIndex(), true, rowNum, 1, 1, XSSFCellStyle.ALIGN_CENTER);
			}
			else{
				ExcelReportUtil.setRowStyle((short)210, IndexedColors.RED.getIndex(), 
						IndexedColors.BLACK.getIndex(), true, rowNum, 1, 1, XSSFCellStyle.ALIGN_CENTER);
			}
			rowNum += 1;
			int maxLength = 50;
			for(int j = 0; j < caseList.get(i).getSteps().size(); j++){
				ExcelReportUtil.setValue(Integer.toString(j + 1), rowNum, 1);
				ExcelReportUtil.setRowStyle((short)210 , false, rowNum, 1, 1, XSSFCellStyle.ALIGN_RIGHT);
				ExcelReportUtil.setValue(caseList.get(i).getSteps().get(j).getOperation(), rowNum, 2);
				ExcelReportUtil.setValue(caseList.get(i).getSteps().get(j).getResult().toString(), rowNum, 5);
				ExcelReportUtil.setValue(caseList.get(i).getSteps().get(j).getTime(), rowNum, 6);
				ExcelReportUtil.setValue(caseList.get(i).getSteps().get(j).getDetail(), rowNum, 8);
				if(caseList.get(i).getSteps().get(j).getResult().toString().equals(TestResult.Failed.toString())){
					ExcelReportUtil.setHyperLink("Screen Shot", "snapshot/" + TimeUtil.getDate("yyyyMMdd") + "/" + caseList.get(i).getImage(), rowNum, 7);
					ExcelReportUtil.setRowStyle(IndexedColors.RED.getIndex(), false, rowNum, 2, 8);
				}
				if(caseList.get(i).getSteps().get(j).getDetail().length() > maxLength){
					maxLength = caseList.get(i).getSteps().get(j).getDetail().length();
				}
				rowNum += 1;
			}
			ExcelReportUtil.setColumnWidth(8, maxLength);
		}
		
	}

}

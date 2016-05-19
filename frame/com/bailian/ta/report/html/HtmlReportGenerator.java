package com.bailian.ta.report.html;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.bailian.ta.config.FrameConfig;
import com.bailian.ta.report.NameUtil;
import com.bailian.ta.report.OverviewGenerator;
import com.bailian.ta.report.model.CaseModel;
import com.bailian.ta.report.model.OverviewModel;
import com.bailian.ta.util.FileUtil;

public class HtmlReportGenerator {
	private PrintStream printStream;
	private List<CaseModel> caseList;
	private OverviewModel overview;
	private OverviewGenerator overviewGenerator;
	private StringBuilder sb;
	
	
	public HtmlReportGenerator(List<CaseModel> list){
		this.caseList = list;
		this.overview = new OverviewModel();
		this.overviewGenerator = new OverviewGenerator(list);
		overview = overviewGenerator.getOverview();
	}
	
	public void createHtmlReport(){
		createHtml(getPath());
		this.sb = new HtmlReportUtil(caseList, overview).writeHtml();
		saveHtml();
		new CopyResource().createCss();
	}
	
	
	private String getPath(){
		FileUtil.createPath(FrameConfig.getInstance().getConfig("ReportPath"));
		return FileUtil.getAbsolutelyPath(FrameConfig.getInstance().getConfig("ReportPath"), NameUtil.getERepotrName() + ".html");
	}
	

	private void createHtml(String path){
		try {
			this.printStream = new PrintStream(new FileOutputStream(path), true, "utf-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	private void saveHtml(){
		this.printStream.println(sb.toString());
	}
}

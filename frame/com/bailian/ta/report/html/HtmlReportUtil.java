package com.bailian.ta.report.html;

import java.util.ArrayList;
import java.util.List;

import com.bailian.ta.report.NameUtil;
import com.bailian.ta.report.model.CaseModel;
import com.bailian.ta.report.model.OverviewModel;
import com.bailian.ta.report.model.TestResult;
import com.bailian.ta.util.TimeUtil;

public class HtmlReportUtil {
	
	private OverviewModel overview;
	private List<CaseModel> caseList;
	StringBuilder sb = new StringBuilder();
	private List<CaseModel> successCase = new ArrayList<CaseModel>();
	private List<CaseModel> failCase = new ArrayList<CaseModel>();
	private List<CaseModel> skipCase = new ArrayList<CaseModel>();
	
	public HtmlReportUtil(List<CaseModel> list, OverviewModel overview){
		this.overview = overview;
		this.caseList = list;
		classifyCase();
	}
	
	
	public StringBuilder writeHtml(){
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\"/>");
		sb.append("<link rel=\"stylesheet\" href=\"css/report.css\" type=\"text/css\" />");
		sb.append(" <script type=\"text/javascript\">"
				+ "function display(id){"
				+ "var traget=document.getElementById(id);"
				  + "if(traget.style.display==\"none\"){"
				 		+ "traget.style.display=\"\";"
			   	  + "}else{"
						+ "traget.style.display=\"none\";"
				  + "}"
				+ "}"
				+ "function showImage(image){"
				+ " window.open (image, 'newwindow',"
				+ " 'height=600, width=800, top=0, left=0, toolbar=no, menubar=no, "
				+ "scrollbars=yes, resizable=no,location=no, status=no')"
				+ "}"
				+ "</script>");
		sb.append("<title>Test Automation Report</title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<div id=\"header\">");
		sb.append("Test Automation Report");
		sb.append("</div>");
		
		sb.append("<div id=\"testinfo\">");
		sb.append("<table id=\"tinfo\">");
		sb.append("<tr id=\"infotitle\">");
		sb.append("<td colspan=\"10\">");
		sb.append("Test Information Overview");
		sb.append("</td>");
		sb.append("</tr>");
		
		sb.append("<tr>");
		sb.append("<td>");
		sb.append("Computer Name: ");
		sb.append(overview.getPcName());
		sb.append("</td>");
		
		sb.append("<td>");
		sb.append("IP Address: ");
		sb.append(overview.getPcIp());
		sb.append("</td>");
		
		sb.append("<td>");
		sb.append("OS: ");
		sb.append(overview.getPcOs());
		sb.append("</td>");
		
		sb.append("<td>");
		sb.append("Generate Time: ");
		sb.append(overview.getTime());
		sb.append("</td>");
		
		sb.append("<td>");
		sb.append("Status: ");
		sb.append(overview.getStatus().toString());
		sb.append("</td>");
		sb.append("</tr>");
		
		sb.append("<tr>");
		sb.append("<td>");
		sb.append("Total Cases: ");
		sb.append(overview.getCaseNum());
		sb.append("</td>");
		
		sb.append("<td>");
		sb.append("Passed: ");
		sb.append(overview.getCasePassNum());
		sb.append("</td>");
		
		sb.append("<td>");
		sb.append("Failed: ");
		sb.append(overview.getCaseFailNum());
		sb.append("</td>");
		
		sb.append("<td>");
		sb.append("Skipped: ");
		sb.append(overview.getCaseSkipNum());
		sb.append("</td>");
		
		sb.append("<td>");
		sb.append("Pass rate: ");
		sb.append(overview.getCasePassRate());
		sb.append("</td>");
		sb.append("</tr>");
		sb.append("</table>");
		sb.append("</div>");
		createPTable(sb);
		createFTable(sb);
		createSTable(sb);
		sb.append("</body>");
		sb.append("</html>");
		return this.sb;
	}
	
	private void createPTable(StringBuilder sb){
		sb.append("<div id=\"pdiv\">");
		sb.append("<table id=\"ptable\" cellspacing=\"0\">");
		sb.append("<tr id=\"pttitle\">");
		sb.append("<td colspan=\"7\">");
		sb.append("Passed Test Cases");
		sb.append("</td>");
		sb.append("</tr>");
		for(int i = 0; i < successCase.size(); i++){
			String classname = "pinkRow";
			sb.append("<tr class=\""+ classname +"\" onclick=\"display( 'tps"+ i+"' )\" style=\"cursor: pointer\" >");
			sb.append("<td width='10px'>");
			sb.append(i + 1 + ". ");
			sb.append("</td>");
			sb.append("<td width='400px'>");
			sb.append(successCase.get(i).getCaseName());
			sb.append("</td>");
			sb.append("<td width='70px'>");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("</td>");
			sb.append("<td width='70px'>");
			sb.append("Duration");
			sb.append("</td>");
			sb.append("<td width='100px'>");
			sb.append(TimeUtil.getDuration(successCase.get(i).getStartTime(), successCase.get(i).getEndTime()));
			sb.append("</td>");
			sb.append("</tr>");
			
			
			sb.append("<tr>");
			sb.append("<td colspan=\"7\">");
			sb.append("<table id=\"tps"+i+"\" class=\"tsteps\" style=\"display: none;\" border='0px'>");
			for(int j = 0; j < successCase.get(i).getSteps().size(); j ++){
				sb.append("<tr>");
				sb.append("<td style=\"text-align:right;\" >");
				sb.append((j + 1) + ". ");
				sb.append("</td>");
				sb.append("<td width='400px'>");
				sb.append( successCase.get(i).getSteps().get(j).getOperation());
				sb.append("</td>");
				sb.append("<td width='30px'>");
				sb.append("<img src=\"./image/" + successCase.get(i).getSteps().get(j).getResult() +".png\" class=\"img\"  alt=\"Passed\" />");
				sb.append("</td>");
				sb.append("</td>");
				sb.append("<td width='150px'>");
				sb.append( successCase.get(i).getSteps().get(j).getTime());
				sb.append("</td>");
				sb.append("<td colspan=\"5\">");
				sb.append( successCase.get(i).getSteps().get(j).getDetail());
				sb.append("</td>");
				sb.append("</tr>");
			}
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			
		}
		sb.append("</table>");
		sb.append("</div>");
	}
	
	private void createFTable(StringBuilder sb){
		sb.append("<div id=\"fdiv\">");
		sb.append("<table id=\"ftable\">");
		sb.append("<tr id=\"fttitle\">");
		sb.append("<td colspan=\"7\">");
		sb.append("Failed Test Cases");
		sb.append("</td>");
		sb.append("</tr>");
		for(int i = 0; i < failCase.size(); i++){
			String classname = "pinkRow";
			sb.append("<tr class=\""+ classname +"\" onclick=\"display( 'fps"+ i+"' )\" style=\"cursor: pointer\">");
			sb.append("<td width='10px'>");
			sb.append(i + 1 + ". ");
			sb.append("</td>");
			sb.append("<td width='400px'>");
			sb.append(failCase.get(i).getCaseName());
			sb.append("</td>");
			sb.append("<td width='70px'>");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("</td>");
			sb.append("<td width='70px'>");
			sb.append("Duration");
			sb.append("</td>");
			sb.append("<td width='100px'>");
			sb.append(TimeUtil.getDuration(failCase.get(i).getStartTime(), failCase.get(i).getEndTime()));
			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("<tr>");
			sb.append("<td colspan=\"7\">");
			sb.append("<table id=\"fps"+i+"\" class=\"tsteps\" style=\"display: none;\" >");
			for(int j = 0; j < failCase.get(i).getSteps().size(); j ++){
				String click = "";
				if(failCase.get(i).getSteps().get(j).getResult().toString().equals(TestResult.Failed.toString())){
					click = "onclick=\"showImage('./snapshot/"+ NameUtil.getFolderName() + "/" + failCase.get(i).getImage()+"')\"  style=\"cursor: pointer\"; border; '0px'"
							+ " onMouseOut=\"this.style.backgroundColor=''\" onMouseOver=\"this.style.backgroundColor='#efefef'\"" ;
				}
				sb.append("<tr " + click + ">");
				sb.append("<td style=\"text-align:right;\" >");
				sb.append((j + 1) + ". ");
				sb.append("</td>");
				sb.append("<td width='400px'>");
				sb.append( failCase.get(i).getSteps().get(j).getOperation());
				sb.append("</td>");
				sb.append("<td width='30px'>");
				sb.append( "<img src=\"./image/" + failCase.get(i).getSteps().get(j).getResult() +".png\" class=\"img\"   alt=\"Failed\" />");
				sb.append("</td>");
				sb.append("</td>");
				sb.append("<td width='150px'>");
				sb.append( failCase.get(i).getSteps().get(j).getTime());
				sb.append("</td>");
				sb.append("<td  colspan=\"5\">");
				sb.append( failCase.get(i).getSteps().get(j).getDetail());
				sb.append("</td>");
				sb.append("</tr>");
			}
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			
		}
		sb.append("</table>");
		sb.append("</div>");
	}
	
	private void createSTable(StringBuilder sb){
		sb.append("<div id=\"sdiv\">");
		sb.append("<table id=\"stable\">");
		sb.append("<tr id=\"sttitle\">");
		sb.append("<td colspan=\"5\">");
		sb.append("Skipped Test Cases");
		sb.append("</td>");
		sb.append("</tr>");
		for(int i = 0; i < skipCase.size(); i++){
			String classname = "pinkRow";
			sb.append("<tr class=\""+ classname +"\">");
			sb.append("<td width='10px'>");
			sb.append(i + 1 + ". ");
			sb.append("</td>");
			sb.append("<td width='500px'>");
			sb.append(skipCase.get(i).getCaseName());
			sb.append("</td>");
			sb.append("<td width='150px'>");
			sb.append("</td>");
			sb.append("</td>");
			sb.append("<td >");
			sb.append("</td>");
			sb.append("</tr>");
		}
		sb.append("</table>");
		sb.append("</div>");
	}
	
	private void classifyCase(){
		for(int i = 0; i < caseList.size(); i ++){
			if(caseList.get(i).getResult().toString().equals(TestResult.Passed.toString())){
				successCase.add(caseList.get(i));
			}
			else if(caseList.get(i).getResult().toString().equals(TestResult.Failed.toString())){
				failCase.add(caseList.get(i));
			}
			else{
				skipCase.add(caseList.get(i));
			}
		}
	}

}

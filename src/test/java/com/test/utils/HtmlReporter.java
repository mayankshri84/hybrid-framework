package com.test.utils;

import java.io.FileWriter;
import java.io.IOException;

public class HtmlReporter {

	public void appendReport(String line) {
		try {
			String filename = System.getProperty("user.dir") + "/report.html";
			FileWriter fw = new FileWriter(filename, true); // the true will
															// append the new
															// data
			fw.write(line);// appends the string to the file
			fw.close();
		} catch (IOException ioe) {
			System.err.println("IOException: " + ioe.getMessage());
		}
	}

	public String reportHead(String reportHead) {
		return "<html>" + "<head>" + "<title>" + "TestReport" + "</title>"
				+ "<style type=\"text/css\">" + ".test-result-table{"
				+ "border:1px solid black;" + "width:800px;" + "}"
				+ ".test-result-table-header-cell{"
				+ "border-bottom:1px solid black;" + "background-color:silver;"
				+ "}" + ".test-result-step-command-cell{"
				+ "border-bottom:1px solid gray;" + "}"
				+ ".test-result-step-description-cell{"
				+ "border-bottom:1px solid gray;" + "}"
				+ ".test-result-step-result-cell-ok{"
				+ "border-bottom:1px solid gray;" + "background-color:green;"
				+ "}" + ".test-result-step-result-cell-failure{"
				+ "border-bottom:1px solid gray;" + "background-color:red;"
				+ "}" + ".test-result-step-result-cell-notperformed{"
				+ "border-bottom:1px solid gray;" + "background-color:white;"
				+ "}" + ".test-result-describe-cell{" + "background-color:tan;"
				+ "font-style:italic;" + "}" + ".test-cast-status-box-ok{"
				+ "border:1px solid black;" + "float:left;"
				+ "margin-right:10px;" + "width:45px;" + "height:25px;"
				+ "background-color:green;" + "}" + "</style>" + "</head>"
				+ "<body>" + "<h1 class=\"test-results-header\">"
				+ ""+reportHead+"" + "</h1>"
				+ "<table class=\"test-result-table\"cellspacing=\"0\">"
				+ "<thead>" + "<tr>"
				+ "<td class=\"test-result-table-header-cell\">" + "TestStep"
				+ "</td>" + "<td class=\"test-result-table-header-cell\">"
				+ "Description" + "</td>"
				+ "<td class=\"test-result-table-header-cell\">" + "Result"
				+ "</td>" + "</tr>" + "</thead><tbody>";
	}

	public String reportBody(boolean status, String step, String log) {
		if (status) {
			return "<tr class=\"test-result-step-rowtest-result-step-row-altone\">"
					+ "<td class=\"test-result-step-command-cell\">"
					+ ""
					+ step
					+ ""
					+ "</td>"
					+ "<td class=\"test-result-step-description-cell\">"
					+ ""
					+ log
					+ ""
					+ "</td>"
					+ "<td class=\"test-result-step-result-cell-ok\">"
					+ "Pass"
					+ "</td>" + "</tr>";
		}

		else {
			return "<tr class=\"test-result-step-rowtest-result-step-row-alttwo\">"
					+ "<td class=\"test-result-step-command-cell\">"
					+ ""
					+ step
					+ ""
					+ "</td>"
					+ "<td class=\"test-result-step-description-cell\">"
					+ ""
					+ log
					+ ""
					+ "</td>"
					+ "<td class=\"test-result-step-result-cell-failure\">"
					+ "Fail" + "</td>" + "</tr>";
		}
	}

	public String reportFoot() {
		return "</tbody>" + "</table>" + "</body>" + "</html>";
	}

}

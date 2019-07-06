package com.test.utils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

public class ObjectRepositoryHelper {

	private WebDriver driver;

	public void getObjects(String url) {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		List<WebElement> list = driver.findElements(By.tagName("*"));
		for (WebElement ele : list) {
			try {
				// if(ele.getAttribute("type")!=null&&(!ele.getAttribute("type").isEmpty())){
				// System.out.println(ele.getTagName()+ ":"
				// +ele.getAttribute("type")+ ":" +getCss(ele));

				/*
				 * if(!ele.getTagName().contains("html")&&!ele.getTagName().contains
				 * (
				 * "script")&&!ele.getTagName().contains("head")&&!ele.getTagName
				 * ().contains("meta")&&!ele.getTagName().contains("link")){
				 * WebElement
				 * ele1=driver.findElement(By.cssSelector(getCss(ele)));
				 * highlightEle(ele1); System.out.println("css="+getCss(ele)); }
				 */
				// }
				if (!ele.getTagName().equals("html")
						&& !ele.getTagName().equals("body")
						&& !ele.getTagName().equals("head")
						&& !ele.getTagName().equals("style")
						&& !ele.getTagName().equals("iframe")
						&& !ele.getTagName().equals("title")
						&& !ele.getTagName().equals("script")
						&& !ele.getTagName().equals("link")
						&& !ele.getTagName().equals("meta")
						&& !ele.getTagName().equals("noscript")) {
					WebElement ele1 = driver.findElement(By
							.cssSelector(getCss(ele)));
					highlightEle(ele1);
					System.out.println("css=" + getCss(ele));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(
				"var currentNode, "
						+ " ni = document.createNodeIterator(document.documentElement, NodeFilter.SHOW_ELEMENT);"
						+ "while(currentNode = ni.nextNode()) {"
						+ "console.log(currentNode.nodeName);" + "}", 1);

	}

	public void testMethod() {
		getObjects("https://www.wikihow.com/Define-a-JavaScript-Function-in-HTML");
		analyzeLog();
	}

	public void analyzeLog() {
		LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
		for (LogEntry entry : logEntries) {
			System.out.println(new Date(entry.getTimestamp()) + " "
					+ entry.getLevel() + " " + entry.getMessage());
			// do something useful with the data
		}
	}

	public String getAbsoluteXPath(WebElement element) {
		return (String) ((JavascriptExecutor) driver)
				.executeScript(
						"function absoluteXPath(element) {"
								+ "var comp, comps = [];"
								+ "var parent = null;"
								+ "var xpath = '';"
								+ "var getPos = function(element) {"
								+ "var position = 1, curNode;"
								+ "if (element.nodeType == Node.ATTRIBUTE_NODE) {"
								+ "return null;"
								+ "}"
								+ "for (curNode = element.previousSibling; curNode; curNode = curNode.previousSibling) {"
								+ "if (curNode.nodeName == element.nodeName) {"
								+ "++position;"
								+ "}"
								+ "}"
								+ "return position;"
								+ "};"
								+

								"if (element instanceof Document) {"
								+ "return '/';"
								+ "}"
								+

								"for (; element && !(element instanceof Document); element = element.nodeType == Node.ATTRIBUTE_NODE ? element.ownerElement : element.parentNode) {"
								+ "comp = comps[comps.length] = {};"
								+ "switch (element.nodeType) {"
								+ "case Node.TEXT_NODE:"
								+ "comp.name = 'text()';" + "break;"
								+ "case Node.ATTRIBUTE_NODE:"
								+ "comp.name = '@' + element.nodeName;"
								+ "break;"
								+ "case Node.PROCESSING_INSTRUCTION_NODE:"
								+ "comp.name = 'processing-instruction()';"
								+ "break;" + "case Node.COMMENT_NODE:"
								+ "comp.name = 'comment()';" + "break;"
								+ "case Node.ELEMENT_NODE:"
								+ "comp.name = element.nodeName;" + "break;"
								+ "}" + "comp.position = getPos(element);"
								+ "}" +

								"for (var i = comps.length - 1; i >= 0; i--) {"
								+ "comp = comps[i];"
								+ "xpath += '/' + comp.name.toLowerCase();"
								+ "if (comp.position !== null) {"
								+ "xpath += '[' + comp.position + ']';" + "}"
								+ "}" +

								"return xpath;" +

								"} return absoluteXPath(arguments[0]);",
						element);
	}

	public String getCss(WebElement element) {
		final String JS_BUILD_CSS_SELECTOR = "for(var e=arguments[0],n=[],i=function(e,n){if(!e||!n)return 0;f"
				+ "or(var i=0,a=e.length;a>i;i++)if(-1==n.indexOf(e[i]))return 0;re"
				+ "turn 1};e&&1==e.nodeType&&'HTML'!=e.nodeName;e=e.parentNode){if("
				+ "e.id){n.unshift('#'+e.id);break}for(var a=1,r=1,o=e.localName,l="
				+ "e.className&&e.className.trim().split(/[\\s,]+/g),t=e.previousSi"
				+ "bling;t;t=t.previousSibling)10!=t.nodeType&&t.nodeName==e.nodeNa"
				+ "me&&(i(l,t.className)&&(l=null),r=0,++a);for(var t=e.nextSibling"
				+ ";t;t=t.nextSibling)t.nodeName==e.nodeName&&(i(l,t.className)&&(l"
				+ "=null),r=0);n.unshift(r?o:o+(l?'.'+l.join('.'):':nth-child('+a+'"
				+ ")'))}return n.join(' > ');";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String selector = (String) js.executeScript(JS_BUILD_CSS_SELECTOR,
				element);
		return selector;
	}

	public void highlightEle(WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(
				"arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
				ele);
	}

}

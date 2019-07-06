package com.test.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebDriverUtils {

	final WebDriver driver;

	public WebDriverUtils(WebDriver driver) {
		this.driver = driver;
	}

	public By getBy(String element) {
		String[] type = element.split("=");
		if (type[0].equalsIgnoreCase("XPATH"))
			return By.xpath(type[1]);
		else if (type[0].equalsIgnoreCase("CSS"))
			return By.cssSelector(type[1]);
		else if (type[0].equalsIgnoreCase("ID"))
			return By.id(type[1]);
		else if (type[0].equalsIgnoreCase("TAGNAME"))
			return By.tagName(type[1]);
		else if (type[0].equalsIgnoreCase("LINKTEXT"))
			return By.linkText(type[1]);
		else if (type[0].equalsIgnoreCase("NAME"))
			return By.name(type[1]);
		else
			return null;
	}

}

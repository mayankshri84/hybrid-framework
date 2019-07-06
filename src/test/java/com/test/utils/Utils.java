package com.test.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.test.utils.ElementIsNotInObjectRepositoryException;
import com.test.utils.Utils;

public class Utils {

	public static void main(String[] args) {
		// System.out.println(new Utils().xmlParser("home","username"));
		Utils utils = new Utils();
		utils.dataReader("dataref1", "username");
	}

	public String xmlParser(String name, String screen) {
		String object = "";
		try {
			String xmlPath = System.getProperty("user.dir")
					+ "/src/test/java/files/object_repository.xml";
			File fXmlFile = new File(xmlPath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList list = doc.getElementsByTagName("screen");
			for (int i = 0; i < list.getLength(); i++) {
				Node n = list.item(i);
				if (n.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) n;
					NodeList objects = element.getElementsByTagName("object");
					if (element.getAttribute("id").equals(screen)) {
						for (int j = 0; j < objects.getLength(); j++) {
							Node n1 = objects.item(j);
							if (n1.getNodeType() == Node.ELEMENT_NODE) {
								Element ele2 = (Element) n1;
								if (ele2.getAttribute("name").equals(name)) {
									object = element
											.getElementsByTagName("object")
											.item(j).getTextContent();
								}
							}
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (object.trim().equals(""))
			try {
				throw new ElementIsNotInObjectRepositoryException(
						"element not present in repository");
			} catch (ElementIsNotInObjectRepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return object;
	}

	public String readFile(String filePath) {
		BufferedReader br = null;
		StringBuilder str = new StringBuilder();
		try {
			br = new BufferedReader(new FileReader(new File(filePath)));
			String currentLine = null;
			while ((currentLine = br.readLine()) != null) {
				// System.out.println(currentLine);
				str.append(currentLine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str.toString();
	}

	public void dataReader(String dataRef, String key) {
		try
		{
			String json = readFile("src/test/java/files/data.json");
			JSONObject object = new JSONObject(json);
			JSONArray array = (JSONArray) object.get(dataRef);
			JSONObject obj = (JSONObject) array.get(0);
			System.out.println(obj.get(key));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class ElementIsNotInObjectRepositoryException extends Exception {

	public ElementIsNotInObjectRepositoryException(String s) {
		super(s);
	}

}

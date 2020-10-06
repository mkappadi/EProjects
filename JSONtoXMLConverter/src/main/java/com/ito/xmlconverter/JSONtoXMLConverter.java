/**
 * 
 */
package com.ito.xmlconverter;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

/**
 * @author Mohan Kappadi
 *
 */
public class JSONtoXMLConverter {

	   public static void main(String[] args) throws JSONException {
		      String json = "{\r\n" + 
		      		" \"author\":\"Amish Tripati\",\r\n" + 
		      		" \"location\":\"Orissa\",\r\n" + 
		      		" \"books\": [\r\n" + 
		      		"           {\r\n" + 
		      		"            \"name\":\"Meluha\",\r\n" + 
		      		"            \"year\":\"2010\"\r\n" + 
		      		"           },\r\n" + 
		      		"           {\r\n" + 
		      		"            \"name\":\"Nagas\",\r\n" + 
		      		"            \"year\":\"2012\"\r\n" + 
		      		"           }\r\n" + 
		      		"          ]\r\n" + 
		      		"}";
		      System.out.println("Input json ==>\n"+json);
		      String xml = convert(json, "root"); 
		      System.out.println("\n\nOutput===>\n"+xml);
		   }
		   public static String convert(String json, String root) throws JSONException {
		      JSONObject jsonObject = new JSONObject(json);
		      return prettyFormat(XML.toString(jsonObject,"library"),"");
		   }
		   
		   public static String prettyFormat(String input, String indent) {
				Source xmlInput = new StreamSource(new StringReader(input));
				StringWriter stringWriter = new StringWriter();
				try {
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					transformer.setOutputProperty(OutputKeys.INDENT, "yes");
					transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
					transformer.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", indent);
					transformer.transform(xmlInput, new StreamResult(stringWriter));

					return stringWriter.toString().trim();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}

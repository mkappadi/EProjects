/**
 * 
 */
package reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.xml.XMLConstants;

import org.milyn.cdr.annotation.ConfigParam;
import org.milyn.container.ExecutionContext;
import org.milyn.xml.SmooksXMLReader;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Mohan Kappadi
 *
 */
public class CustomFileReader implements SmooksXMLReader {

    private ContentHandler contentHandler;
    private static char[] INDENT_LF = new char[] {'\n'};
    private static char[] INDENTCHARS = new char[] {'\t', '\t'};
    private static char SQUARE_BRACKET_OPEN = '[';
    private static char SQUARE_BRACKET_CLOSE = ']';
    
    
    @ConfigParam
    private String[] header; //
    @ConfigParam
    private String[] detail; //
    @ConfigParam
	private String[] tags;
    @ConfigParam(defaultVal="false")
    private boolean indent;
    @ConfigParam
    private String[] additionaldetail;
    
    
    /**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void setContentHandler(ContentHandler contentHandler) {
		this.contentHandler = contentHandler;
	}
	
	public void parse(InputSource flatfile) throws IOException, SAXException {

		BufferedReader ffRecordReader = new BufferedReader(flatfile.getCharacterStream());
  
        contentHandler.startDocument();
        contentHandler.startElement(XMLConstants.NULL_NS_URI, "XML", "", new AttributesImpl());
        if(indent) {
            contentHandler.characters(INDENT_LF, 0, 1);
            contentHandler.characters(INDENTCHARS, 0, 1);
        }
              
        ArrayList<Fields> fldList = extractFieldNameLengthAndValue(header,ffRecordReader.readLine());
        
        contentHandler.startElement(XMLConstants.NULL_NS_URI, tags[0], "", new AttributesImpl()); //Adding Header Element
               
        for(int i = 0; i < fldList.size(); i++) {
            if(indent) {
                contentHandler.characters(INDENT_LF, 0, 1);
                contentHandler.characters(INDENTCHARS, 0, 2);
            }
            contentHandler.startElement(XMLConstants.NULL_NS_URI, fldList.get(i).getName(), "", new AttributesImpl());
            contentHandler.characters(fldList.get(i).getValue().toCharArray(), 0, fldList.get(i).getLength());
            contentHandler.endElement(XMLConstants.NULL_NS_URI, fldList.get(i).getName(), "");
        }
        
        if(indent) {
            contentHandler.characters(INDENT_LF, 0, 1);
            contentHandler.characters(INDENTCHARS, 0, 1);
        }
        
        contentHandler.endElement(XMLConstants.NULL_NS_URI, tags[0], "");  //Processing Header Completed
        
        if(indent) {
            contentHandler.characters(INDENT_LF, 0, 1);
            contentHandler.characters(INDENTCHARS, 0, 1);
        }
        
        String detailRecord = ffRecordReader.readLine();
        while(detailRecord != null) {
	        fldList = extractFieldNameLengthAndValue(detail,detailRecord);
	        contentHandler.startElement(XMLConstants.NULL_NS_URI, tags[1], "", new AttributesImpl());     //Adding 1st detail Element
	        
	        for(int i = 0; i < fldList.size(); i++) {
	            if(indent) {
	                contentHandler.characters(INDENT_LF, 0, 1);
	                contentHandler.characters(INDENTCHARS, 0, 2);
	            }
	            contentHandler.startElement(XMLConstants.NULL_NS_URI, fldList.get(i).getName(), "", new AttributesImpl());
	            contentHandler.characters(fldList.get(i).getValue().toCharArray(), 0, fldList.get(i).getLength());
	            contentHandler.endElement(XMLConstants.NULL_NS_URI, fldList.get(i).getName(), "");
	        }
	        
	        if(indent) {
	            contentHandler.characters(INDENT_LF, 0, 1);
	            contentHandler.characters(INDENTCHARS, 0, 1);
	        }
	        
	        contentHandler.endElement(XMLConstants.NULL_NS_URI, tags[1], "");  //Processing 1st Details Completed
	        if(indent) {
	            contentHandler.characters(INDENT_LF, 0, 1);
	            contentHandler.characters(INDENTCHARS, 0, 1);
	        }
	        
	        if(additionaldetail!=null) {                                          // check to see if there is an additional detail if so add that
	        	String adetailRecord = ffRecordReader.readLine();
	        	fldList = extractFieldNameLengthAndValue(additionaldetail,adetailRecord);
	        	contentHandler.startElement(XMLConstants.NULL_NS_URI, tags[2], "", new AttributesImpl()); 
		        for(int i = 0; i < fldList.size(); i++) {
		            if(indent) {
		                contentHandler.characters(INDENT_LF, 0, 1);
		                contentHandler.characters(INDENTCHARS, 0, 2);
		            }
		            contentHandler.startElement(XMLConstants.NULL_NS_URI, fldList.get(i).getName(), "", new AttributesImpl());
		            contentHandler.characters(fldList.get(i).getValue().toCharArray(), 0, fldList.get(i).getLength());
		            contentHandler.endElement(XMLConstants.NULL_NS_URI, fldList.get(i).getName(), "");
		        }
		        if(indent) {
		            contentHandler.characters(INDENT_LF, 0, 1);
		            contentHandler.characters(INDENTCHARS, 0, 1);
		        }
		        
		        contentHandler.endElement(XMLConstants.NULL_NS_URI, tags[2], "");  //Processing 2nd Details Completed
		        if(indent) {
		            contentHandler.characters(INDENT_LF, 0, 1);
		            contentHandler.characters(INDENTCHARS, 0, 1);
		        }
	        }
	        detailRecord = ffRecordReader.readLine();
        }
        if(indent) {
            contentHandler.characters(INDENT_LF, 0, 1);
        }
        

        // Send the end of message events to the handler...
        contentHandler.endElement(XMLConstants.NULL_NS_URI, "XML", "");
        contentHandler.endDocument();
	}
	
	
	private ArrayList<Fields> extractFieldNameLengthAndValue(String[] parm, String lineFromFlatFile) {
		ArrayList<Fields> fl = new ArrayList<Fields>();
		Fields f = null;
		int counter = 0;
		for(String str: parm) {
			f = new Fields();
			f.setName(extractFieldName(str));
			int len = Integer.parseInt(extractFieldLength(str)) ;
			f.setLength(len);
			f.setValue(extractFieldValue(lineFromFlatFile,counter,len));
			fl.add(f);
			counter+=(len);
		}
		return fl;
	}

	private String extractFieldValue(String lineFromFlatFile, int counter, int len) {
		if(lineFromFlatFile!=null && lineFromFlatFile.length() > 0) {
			return lineFromFlatFile.substring(counter,counter+len);
		}
		return null;
	}

	private String extractFieldName(String str) {
		if(str!=null && str.length() > 0)
			return str.substring(0, str.indexOf(SQUARE_BRACKET_OPEN));
		else 
			return "";
	}
	
	private String extractFieldLength(String str) {
		if(str!=null && str.length() > 0)
			return str.substring(str.indexOf(SQUARE_BRACKET_OPEN)+1,str.indexOf(SQUARE_BRACKET_CLOSE));
		else 
			return "";
	}	
	
	
	/* None of the below methods needs to be implemented for this requirement */

	public ContentHandler getContentHandler() {
		// TODO Auto-generated method stub
		return null;
	}
	public DTDHandler getDTDHandler() {
		// TODO Auto-generated method stub
		return null;
	}
	public EntityResolver getEntityResolver() {
		// TODO Auto-generated method stub
		return null;
	}
	public ErrorHandler getErrorHandler() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean getFeature(String arg0) throws SAXNotRecognizedException, SAXNotSupportedException {
		// TODO Auto-generated method stub
		return false;
	}
	public Object getProperty(String arg0) throws SAXNotRecognizedException, SAXNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void parse(String arg0) throws IOException, SAXException {
		// TODO Auto-generated method stub
		
	}

	public void setDTDHandler(DTDHandler arg0) {
		// TODO Auto-generated method stub
		
	}
	public void setEntityResolver(EntityResolver arg0) {
		// TODO Auto-generated method stub
		
	}
	public void setErrorHandler(ErrorHandler arg0) {
		// TODO Auto-generated method stub
		
	}
	public void setFeature(String arg0, boolean arg1) throws SAXNotRecognizedException, SAXNotSupportedException {
		// TODO Auto-generated method stub
		
	}
	public void setProperty(String arg0, Object arg1) throws SAXNotRecognizedException, SAXNotSupportedException {
		// TODO Auto-generated method stub
		
	}
	public void setExecutionContext(ExecutionContext executionContext) {
		// TODO Auto-generated method stub
		
	}

}
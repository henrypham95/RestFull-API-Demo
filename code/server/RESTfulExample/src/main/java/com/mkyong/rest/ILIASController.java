package com.mkyong.rest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.NodeList;

public class ILIASController {
	public static void main(String []args){
		
	}
	
	static String login(String clientname,String username,String password) throws Exception{
			String value;
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            String url = "http://localhost/ilias/webservice/soap/server.php";
            SOAPMessage soapResponse = soapConnection.call(	createSOAPLoginRequest(clientname,username,password), url);
            soapResponse.writeTo(System.out);
            System.out.println();
            // Process the SOAP Response
           // printSOAPResponse(soapResponse);
            
            SOAPBody body = soapResponse.getSOAPBody();
            java.util.Iterator updates = body.getChildElements();
            while (updates.hasNext()) {
               
                SOAPElement update = (SOAPElement)updates.next();
                java.util.Iterator i = update.getChildElements();
                while( i.hasNext() ){
                    SOAPElement e = (SOAPElement)i.next();
                    String name = e.getLocalName();
                     value = e.getValue();
                    if(value!=null){
                    	return value;
                    }
                    break;
                }
            }
           
            soapConnection.close();
			return null;
        
	
	}
	static String GetIdCourse(String sid,String targetid,String crsxml) throws Exception{
		String value;
        // Create SOAP Connection
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        // Send SOAP Message to SOAP Server
        String url = "http://localhost/ilias/webservice/soap/server.php";
        SOAPMessage soapResponse = soapConnection.call(	createCourseSoapRequest(sid, targetid, crsxml), url);
        
        // Process the SOAP Response
       // printSOAPResponse(soapResponse);
        soapResponse.writeTo(System.out);
        System.out.println();
        SOAPBody body = soapResponse.getSOAPBody();
        java.util.Iterator updates = body.getChildElements();
        while (updates.hasNext()) {
           
            SOAPElement update = (SOAPElement)updates.next();
            java.util.Iterator i = update.getChildElements();
            while( i.hasNext() ){
                SOAPElement e = (SOAPElement)i.next();
                String name = e.getLocalName();
                 value = e.getValue();
                if(value!=null){
                	return value;
                }
                break;
            }
        }
       
        soapConnection.close();
		return null;
    

}
	 static SOAPMessage createSOAPLoginRequest(String clientname,String username,String password) throws Exception {
	        MessageFactory messageFactory = MessageFactory.newInstance();
	        SOAPMessage soapMessage = messageFactory.createMessage();
	        SOAPPart soapPart = soapMessage.getSOAPPart();

	        String serverURI = "urn:ilUserAdministration";

	        // SOAP Envelope
	        SOAPEnvelope envelope = soapPart.getEnvelope();
	        envelope.addNamespaceDeclaration("urn", serverURI);

	        /*
	        Constructed SOAP Request Message:
	        <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:example="http://ws.cdyne.com/">
	            <SOAP-ENV:Header/>
	            <SOAP-ENV:Body>
	                <example:VerifyEmail>
	                    <example:email>mutantninja@gmail.com</example:email>
	                    <example:LicenseKey>123</example:LicenseKey>
	                </example:VerifyEmail>
	            </SOAP-ENV:Body>
	        </SOAP-ENV:Envelope>
	         */

	        // SOAP Body
	        SOAPBody soapBody = envelope.getBody();
	        SOAPElement soapBodyElem = soapBody.addChildElement("login", "urn");
	        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("client", "urn");
	        soapBodyElem1.addTextNode(clientname);
	        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("username", "urn");
	        soapBodyElem2.addTextNode(username);
	        SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("password", "urn");
	        soapBodyElem3.addTextNode(password);

	        MimeHeaders headers = soapMessage.getMimeHeaders();
	        headers.addHeader("SOAPAction", serverURI  + "login");

	        soapMessage.saveChanges();

	        /* Print the request message */
	        System.out.print("Request SOAP Message = ");
	        soapMessage.writeTo(System.out);
	        System.out.println();

	        return soapMessage;
	    }
	 static SOAPMessage createCourseSoapRequest(String sid,String targetid,String crsxml) throws Exception {
	        MessageFactory messageFactory = MessageFactory.newInstance();
	        SOAPMessage soapMessage = messageFactory.createMessage();
	        SOAPPart soapPart = soapMessage.getSOAPPart();

	        String serverURI = "urn:ilUserAdministration";

	        // SOAP Envelope
	        SOAPEnvelope envelope = soapPart.getEnvelope();
	        envelope.addNamespaceDeclaration("urn", serverURI);

	        /*
	        Constructed SOAP Request Message:
	        <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:example="http://ws.cdyne.com/">
	            <SOAP-ENV:Header/>
	            <SOAP-ENV:Body>
	                <example:VerifyEmail>
	                    <example:email>mutantninja@gmail.com</example:email>
	                    <example:LicenseKey>123</example:LicenseKey>
	                </example:VerifyEmail>
	            </SOAP-ENV:Body>
	        </SOAP-ENV:Envelope>
	         */

	        // SOAP Body
	        SOAPBody soapBody = envelope.getBody();
	        SOAPElement soapBodyElem = soapBody.addChildElement("addCourse", "urn");
	        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("sid", "urn");
	        soapBodyElem1.addTextNode(sid);
	        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("target_id", "urn");
	        soapBodyElem2.addTextNode(targetid);
	        SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("crs_xml", "urn");
	        soapBodyElem3.addTextNode(crsxml);

	        MimeHeaders headers = soapMessage.getMimeHeaders();
	        headers.addHeader("SOAPAction", serverURI  + "addCourse");

	        soapMessage.saveChanges();

	        /* Print the request message */
	        System.out.print("Request SOAP Message = ");
	        soapMessage.writeTo(System.out);
	        System.out.println();
	        	
	        return soapMessage;
	    }
}

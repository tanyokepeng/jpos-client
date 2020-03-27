package com.jpos.beans;

//import org.jpos.iso.FilteredChannel;
import org.jpos.iso.ISOChannel;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOField;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.channel.ASCIIChannel;
//import org.jpos.iso.packager.ISO87APackager;
import org.jpos.util.LogSource;
import org.jpos.util.Logger;
import org.jpos.util.SimpleLogListener;
import java.io.IOException;
import com.jpos.beans.RequestMessage;
//import com.jpos.beans.ResponseMessage;
import org.jpos.iso.packager.GenericPackager;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
//import java.util.HashMap;
import org.json.simple.JSONObject;
import com.ibm.json.java.OrderedJSONObject;

public class ProcessRequest {

	public JSONObject postMessage (RequestMessage inMsg) {

		//ResponseMessage response = new ResponseMessage();
		JSONObject response = new JSONObject();

		Logger logger = new Logger();
        logger.addListener(new SimpleLogListener(System.out));
         
        Properties prop = new Properties();
        InputStream inputStream = null;
       
        try {        	
            GenericPackager packager = new GenericPackager("epfisobase.xml");
            inputStream = new FileInputStream("application.properties");
            // load a properties file
            prop.load(inputStream);
            // get the property value 
            String hostname = prop.getProperty("jpos.channel.host");
            int port = Integer.parseInt(prop.getProperty("jpos.channel.port"));

            ISOChannel channel = new ASCIIChannel(hostname,port,packager);
            ((LogSource) channel).setLogger(logger, "client-logger");

            channel.connect();
            ISOMsg m = new ISOMsg ();
            m.setMTI ("0200");
            m.set (11, inMsg.refId);
            m.set (7, inMsg.reqTime);
            m.set("71.3", inMsg.idNo);
            channel.send(m);
            //Get incoming message
            ISOMsg incoming = channel.receive();
            System.out.println(incoming.pack());

            /* set MTI and header */
            //response.setMTI(incoming.getMTI());
            //HashMap field = new HashMap<String,String>();
            //field.put("MTI",incoming.getMTI());
            //response.getFields().add(field);
            response.put("MTI",incoming.getMTI());
/*            if (incoming.getHeader() != null) {
                response.setISOHeader(new String(incoming.getHeader()));
            }*/
            
            /* add ISOMsg fields and subfields to response */
            for (int i = 1; i <= incoming.getMaxField(); i++) {
                if (incoming.hasField(i)) {
                	boolean fieldOnly = incoming.getComponent(i) instanceof ISOField;
                	if (fieldOnly) {
                		System.out.printf("Field (%s) = %s%n", i, incoming.getString(i));
	                	//response.getFields().put("field"+i, incoming.getString(i));
	                	//field = new HashMap<String,String>();
	                	//field.put(i + "", incoming.getString(i));
	                	//response.getFields().add(field);
                		response.put("field"+i, incoming.getString(i));
	                } else {
	            		ISOMsg subfield = (ISOMsg)incoming.getComponent(i);
	                    for (int j = 1; j <= subfield.getMaxField(); j++) {
	                       if (subfield.hasField(j)) {
	                    	   System.out.println("Parent field: (" +i + ") subField: (" +j + ") value:" +subfield.getString(j));
	                    	   //response.getFields().put("field"+i+"_"+j, subfield.getString(j));
	                    	   //field = new HashMap<String,String>();
	                    	   //field.put(i+"."+j, subfield.getString(j));
	                    	   //response.getFields().add(field);
	                    	   response.put("field"+i+"_"+j, subfield.getString(j));
	                       }
	                    }
	                }
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ISOException e) {
            e.printStackTrace();
		} finally {
	        if (inputStream != null) {
	            try {
	                inputStream.close();
	            } catch(IOException e) {
	                e.printStackTrace();
	            }
	        }
		}
        return response;

    }

}

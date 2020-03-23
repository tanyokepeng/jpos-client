package com.jpos.beans;

import org.jpos.iso.FilteredChannel;
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
import com.jpos.beans.ResponseMessage;
import org.jpos.iso.packager.GenericPackager;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.HashMap;


public class ProcessRequest {

	public ResponseMessage postMessage (RequestMessage inMsg) {

		ResponseMessage response = new ResponseMessage();

		Logger logger = new Logger();
        logger.addListener(new SimpleLogListener(System.out));
         
        Properties prop = new Properties();
        InputStream inputStream = null;
       
        try {        	
            GenericPackager packager = new GenericPackager("src/main/resources/basic.xml");
            inputStream = new FileInputStream("src/main/resources/application.properties");
            // load a properties file
            prop.load(inputStream);
            // get the property value 
            String hostname = prop.getProperty("jpos.channel.host");
            int port = Integer.parseInt(prop.getProperty("jpos.channel.port"));

            ISOChannel channel = new ASCIIChannel(hostname,port,packager);
            ((LogSource) channel).setLogger(logger, "client-logger");

            channel.connect();
            ISOMsg m = new ISOMsg ();
            m.setMTI (inMsg.MTI);
            m.set (3, inMsg.field3);
            m.set (34,inMsg.field34);
            m.set (36,inMsg.field36);
            m.set (41,inMsg.field41);
            m.set (70,inMsg.field70);
            m.set("71.2", inMsg.field71_2);
            channel.send(m);
            //Get incoming message
            ISOMsg incoming = channel.receive();
            System.out.println(incoming.pack());

            /* set MTI and header */
            //response.setMTI(incoming.getMTI());
            HashMap field = new HashMap<String,String>();
            field.put("MTI",incoming.getMTI());
            response.getFields().add(field);
/*            if (incoming.getHeader() != null) {
                response.setISOHeader(new String(incoming.getHeader()));
            }*/
            
            /* add ISOMsg fields and subfields to response */
            for (int i = 1; i <= incoming.getMaxField(); i++) {
                if (incoming.hasField(i)) {
                	boolean fieldOnly = incoming.getComponent(i) instanceof ISOField;
                	if (fieldOnly) {
                		System.out.printf("Field (%s) = %s%n", i, incoming.getString(i));
	                	//response.getFields().put(i + "", incoming.getString(i));
	                	field = new HashMap<String,String>();
	                	field.put(i + "", incoming.getString(i));
	                	response.getFields().add(field);
	                } else {
	            		ISOMsg subfield = (ISOMsg)incoming.getComponent(i);
	                    for (int j = 1; j <= subfield.getMaxField(); j++) {
	                       if (subfield.hasField(j)) {
	                    	   System.out.println("Parent field: (" +i + ") subField: (" +j + ") value:" +subfield.getString(j));
	                    	   //response.getFields().put(i+"."+j, subfield.getString(j));
	                    	   field = new HashMap<String,String>();
	                    	   field.put(i+"."+j, subfield.getString(j));
	                    	   response.getFields().add(field);
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

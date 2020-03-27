package com.jpos.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jpos.beans.*;
import org.json.simple.JSONObject;
import com.ibm.json.java.OrderedJSONObject;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Stack;
//import java.util.List;


@Controller
public class JposClientController {

	@RequestMapping(method = RequestMethod.POST, value="/jpos/client")

	@ResponseBody	  
	public JSONObject PostMessage (@RequestBody RequestMessage msg) {

		ProcessRequest testClient = new ProcessRequest();					
		JSONObject outMsg = testClient.postMessage(msg);
							
		return outMsg;

	}	
}

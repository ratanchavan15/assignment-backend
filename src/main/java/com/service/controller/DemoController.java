package com.service.controller;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.service.Service;

@Controller
@RequestMapping("/")
public class DemoController {
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String sayHello(ModelMap model) {
		return "status : success,Server running successfully!!!";
	}

	@RequestMapping(value="/getData", method = RequestMethod.GET)
	public @ResponseBody String getData(HttpServletRequest request) {
		Map<String,Object> response = new HashMap();
		try {
			 JSONParser jsonParser = new JSONParser();
        	InputStream is = getClass().getResourceAsStream("data.json");
        	Reader reader = new InputStreamReader(is);
            Object obj = jsonParser.parse(reader);
            JSONArray data = (JSONArray) obj;
            if(data != null) {
            	//response.put("status", "SUCCESS");
            	response.put("data", data.toString());
            }
            return data.toString();
		} catch (Exception e) {
			e.printStackTrace();
			response.put("status", "FAILURE");
			response.put("message", "System experiencing some problem,Please try again later.");
			return response.toString();
		}
	}

}

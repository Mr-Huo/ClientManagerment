package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Client;
import daoimpl.ClientDAOImpl;
import net.sf.json.JSONObject;

public class RecommendServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	String acceptjson = "";
	    	Client client = null;
	    	String name = null;
	    	String recommend = null;
	    	try {
	    		BufferedReader br = new BufferedReader(new InputStreamReader( (ServletInputStream) request.getInputStream(), "utf-8"));  
	    	    StringBuffer sb = new StringBuffer("");  
	    	    String temp;  
	    	    while ((temp = br.readLine()) != null) {  
	    	        sb.append(temp);  
	    	    }  
	    	    br.close();  
	    	    acceptjson = sb.toString();  
				JSONObject json = JSONObject.fromObject(acceptjson);
				client = (Client)JSONObject.toBean(json,Client.class);
	    	} catch (Exception e) {  
	    	    e.printStackTrace();    
	    	}
	    	name = client.getCp_name();
			recommend = client.getRecommend();
	    	
	    	ClientDAOImpl clientImpl = new ClientDAOImpl();
	    	boolean result = false;
	    	try {
				result = clientImpl.recommendClient(name, recommend);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
	    	response.setContentType("text/html");  
	        response.setCharacterEncoding("utf-8");  
	        System.out.println(result);
			response.getWriter().print(result); 
	    }

	    /**
	     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	     */
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // TODO Auto-generated method stub
	        doGet(request, response);
	    }

}

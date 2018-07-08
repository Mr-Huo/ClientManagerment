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

public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	this.doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	Client client = new Client();  
    	String acceptjson = "";
    	try {
    		BufferedReader br = new BufferedReader(new InputStreamReader( (ServletInputStream) request.getInputStream(), "utf-8"));  
    	    StringBuffer sb = new StringBuffer("");  
    	    String temp;  
    	    while ((temp = br.readLine()) != null) {  
    	        sb.append(temp);  
    	    }  
    	    br.close();  
    	    acceptjson = sb.toString();  
    	    //System.out.println(acceptjson);
			JSONObject json = JSONObject.fromObject(acceptjson);
			//System.out.println(json);
    	    client = (Client)JSONObject.toBean(json,Client.class);
    	    System.out.println(client);
    	} catch (Exception e) {  
    	    e.printStackTrace();    
    	}

    	
        ClientDAOImpl clientImpl = new ClientDAOImpl();  
    	boolean result = false;  
    	try {  
			result = clientImpl.addClient(client);
		} catch (Exception e) {  
			e.printStackTrace();  
		}
    	response.setContentType("text/html");  
		response.getWriter().print(result);
    }
}

package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Client;
import daoimpl.ClientDAOImpl;
import net.sf.json.JSONObject;

public class SelectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String acceptjson = "";
    	Client client = null;
    	String name = null;
    	String size = null;
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
		size = client.getSize();
    	StringBuffer url = request.getRequestURL();
    	String urls = url.toString();
    	String[] args = urls.split("/");
    	int page = Integer.valueOf(args[args.length-1]);
    	
    	ClientDAOImpl clientImpl = new ClientDAOImpl();
    	ArrayList<Client> clientList = null;
    	try {
    		//System.out.println("Servlet:"+name+size+page);
			clientList = (ArrayList<Client>) clientImpl.selectClient(name,size,page);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	HashMap<String,Object> hash = new HashMap<>();
    	hash.put("clientInfos", clientList);
    	int page2 = clientImpl.getPage();
    	System.out.println("Page2:"+page2);
    	hash.put("page",page2);
    	JSONObject result = JSONObject.fromObject(hash);
        
    	response.setContentType("text/html");  
        response.setCharacterEncoding("utf-8");  
        System.out.println(result.toString());
		response.getWriter().print(result.toString()); 
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}

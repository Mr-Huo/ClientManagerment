package daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import bean.Client;
import dao.ClientDAO;

public class ClientDAOImpl implements ClientDAO {

	Connection conn = null; 
	List<Client> clientList = new ArrayList<Client>(); 
	private int page2; 
	
	@Override
	public List<Client> selectClient(String name,String size,int page) throws Exception {
		//1、加载数据库驱动
		Class.forName("com.mysql.jdbc.Driver");
		
		try {
			//2、链接数据库
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/client_mg2", "root", "123456");
			//3、定义SQL语句
			Statement pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			//4、执行sql
			ResultSet rs = null;
			if(name.equals("") && size.equals("")) {
				rs = pstmt.executeQuery("select * from client_infor where del = 0 ");
			}else if(!name.equals("") && size.equals("")) {
				rs = pstmt.executeQuery("select * from client_infor where del = 0 and cp_name like '%" + name+"%' ");
			}else if(name.equals("") && !size.equals("")) { 
				rs = pstmt.executeQuery("select * from client_infor where del = 0 and size like '%" + size + "%' ");
			}else{
				rs = pstmt.executeQuery("select * from client_infor where del = 0 and cp_name like '%" + name + "%' and size like '%" + size+"%' ");
			}
			int rowcount =0;
			rs.last();      //直接执行跳到结果集的最后一行

			rowcount = rs.getRow();   //这一句就能得到结果集的行数
			rs.beforeFirst();   //重新执行到第一行的前一行，以便查询结果集的集体内容
			page2 = (rowcount-1)/10 + 1;
			System.out.println("rowcount:"+rowcount+"page:"+page2);
			
			int k=0;
			//5、处理数据
			Client client = null;
			while(rs.next()) {
				if(k>=(page-1)*10&&k<=page*10) {
					client = new Client();
					client.setId(rs.getInt("id"));
					client.setCp_name(rs.getString("cp_name"));
					client.setCl_name(rs.getString("cl_name"));
					client.setPhone(rs.getString("phone"));
					client.setEmail(rs.getString("email"));
					client.setAddress(rs.getString("address"));
					client.setCaptial(rs.getString("captial"));
					client.setCheck_date(rs.getString("check_date"));
					client.setRecommend(rs.getString("recommend"));
					client.setRegister_date(rs.getString("register_date"));
					client.setSize(rs.getString("size"));
					
					clientList.add(client);
				}
				k++;
			}
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				}catch(SQLException e) {}
			}
		}
	    return clientList;
	}

	@Override
	public boolean addClient(Client client) throws Exception {
		//1、加载数据库驱动
		Class.forName("com.mysql.jdbc.Driver");
		
		try {
			//2、链接数据库
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/client_mg2", "root", "123456");
			//3、定义预处理语句
			PreparedStatement pstmt = conn.prepareStatement("insert into client_infor" +
			    "(cp_name,cl_name,phone,email,address,captial,check_date,recommend,register_date,size) values(?,?,?,?,?,?,?,?,?,?)");
			
			//4、执行sql
			//4.1 设置参数
			pstmt.setString(1, client.getCp_name()); 
			pstmt.setString(2, client.getCl_name()); 
			pstmt.setString(3, client.getPhone()); 
			if(client.getEmail() == null) {
				pstmt.setNull(4, Types.VARCHAR);
			}else {
				pstmt.setString(4, client.getEmail()); 
			}
			pstmt.setString(5, client.getAddress());
			pstmt.setString(6, client.getCaptial()); 
			pstmt.setString(7, client.getCheck_date()); 
			if(client.getRecommend() == null) {
				pstmt.setNull(8, Types.VARCHAR);
			}else {
				pstmt.setString(8, client.getRecommend());
			}
			pstmt.setString(9, client.getRegister_date()); 
			if(client.getSize() == null) { 
				pstmt.setNull(10, Types.VARCHAR);
			}else {
				pstmt.setString(10, client.getSize()); 
			}
			
			//4.2执行sql
			int n = pstmt.executeUpdate();
			
			//5、返回数据
			return n==1;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				}catch(SQLException e) {}
			}
		}
	}

	@Override
	public boolean deleteClient(String cp_name) throws Exception {
		//1、加载数据库驱动
		Class.forName("com.mysql.jdbc.Driver");
		
		try {
			//2、链接数据库
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/client_mg2", "root", "123456");
			//3、设置数据库语句
			Statement pstmt = conn.createStatement();
					
			//4、执行sql语句
			int rs = 0;
			rs = pstmt.executeUpdate("update client_infor set del = 1 where cp_name = '"+cp_name+"'");
			
			return rs==1;
					
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				}catch(SQLException e) {}
			}
		}
	}

	@Override
	public boolean updateClient(Client client) throws Exception {
		System.out.println(client);
		//1、加载数据库驱动
		Class.forName("com.mysql.jdbc.Driver");
				
		try {
			//2、链接数据库
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/client_mg2", "root", "123456");
			//3定义sql语句
			PreparedStatement pstmt = conn.prepareStatement("update client_infor set " +
			        " cl_name=?,phone=?,email=?,address=?,captial=?,check_date=?,recommend=?,register_date=?,size=? " +
					" where cp_name ='" + client.getCp_name()+"'");
					
			//4、执行sql
			//4.1 设置参数
			pstmt.setString(1, client.getCl_name());
			pstmt.setString(2, client.getPhone()); 
			if(client.getEmail() == null) {
				pstmt.setNull(3, Types.VARCHAR);
			}else {
				pstmt.setString(3, client.getEmail()); 
			}
			pstmt.setString(4, client.getAddress()); //ַ
			pstmt.setString(5, client.getCaptial()); 
			pstmt.setString(6, client.getCheck_date()); 
			if(client.getRecommend() == null) {
				pstmt.setNull(7, Types.VARCHAR);
			}else {
				pstmt.setString(7, client.getRecommend());
			}
			pstmt.setString(8, client.getRegister_date());
			if(client.getSize() == null) {
				pstmt.setNull(9, Types.VARCHAR);
			}else {
				pstmt.setString(9, client.getSize()); 
			}
			System.out.println(pstmt);
			//4.2ִ执行语句
			int n = pstmt.executeUpdate();
					
			//5、返回结果
			return n==1;
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				}catch(SQLException e) {}
			}
		}
	}

	public int getPage() {
		System.out.println("return:"+this.page2);
		return this.page2;
	}

	public boolean recommendClient(String cp_name,String recommend) throws Exception {
		//1、加载数据库驱动
		Class.forName("com.mysql.jdbc.Driver");
		
		try {
			//2、链接数据库
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/client_mg2", "root", "123456");
			if(!cp_name.equals("") && !recommend.equals("")||cp_name!=null&&recommend!=null) {
				//3、定义预处理语句
				PreparedStatement pstmt = conn.prepareStatement("update client_infor set " +
				        " recommend=? " +
						" where cp_name ='" + cp_name +"'");
				
				//4、执行sql
				//4.1 设置参数
				pstmt.setString(1, recommend);
				//4.2ִ执行语句
				int n = pstmt.executeUpdate();
				//5、返回结果
				return n==1;
				
			}else {
				return false;
			}		
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				}catch(SQLException e) {}
			}
		}
		
	}

}

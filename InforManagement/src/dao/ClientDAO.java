package dao;

import java.util.List;
import bean.Client;

public interface ClientDAO {
	public List<Client> selectClient(String name, String range,int num) throws Exception;
	public boolean addClient(Client client) throws Exception;
	public boolean deleteClient(String name) throws Exception;
	public boolean updateClient(Client client) throws Exception;
	public boolean recommendClient(String cp_name,String recommend) throws Exception;
}

package bean;

public class Client {
	private int id;
	private String cp_name;
	private String cl_name;
	private String phone;
	private String email;
	private String address;
	private String captial;
	private String check_date;
	private String recommend;
	private String register_date;
	private String size;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCp_name() {
		return cp_name;
	}
	public void setCp_name(String cp_name) {
		this.cp_name = cp_name;
	}
	public String getCl_name() {
		return cl_name;
	}
	public void setCl_name(String cl_name) {
		this.cl_name = cl_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCaptial() {
		return captial;
	}
	public void setCaptial(String captial) {
		this.captial = captial;
	}
	public String getCheck_date() {
		return check_date;
	}
	public void setCheck_date(String check_date) {
		this.check_date = check_date;
	}
	public String getRecommend() {
		return recommend;
	}
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	public String getRegister_date() {
		return register_date;
	}
	public void setRegister_date(String register_date) {
		this.register_date = register_date;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "Client [id=" + id + ", cp_name=" + cp_name + ", cl_name=" + cl_name + ", phone=" + phone + ", email="
				+ email + ", address=" + address + ", captial=" + captial + ", check_date=" + check_date
				+ ", recommend=" + recommend + ", register_date=" + register_date + ", size=" + size + "]";
	}
	
}

package brand;
public class brand 
{
	private String unique_id;
	private String name;
	private String password;	
	private String address;
	private String join_date;
	private String lp_id;
	

	public String getLp_id() {
		return lp_id;
	}
	public void setLp_id(String lp_id) {
		this.lp_id = lp_id;
	}
	public String getUnique_id(){
		return unique_id;
	}
	public void setUnique_id(String unique_id) {
		this.unique_id = unique_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getJoin_date() {
		return join_date;
	}
	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}

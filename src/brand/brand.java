package brand;
public class brand 
{
	private String unique_id;
	private String name;
	private String address;
	private String join_date;
	private String lp_name;
	
	public String getlp_name() {
		return lp_name;
	}
	public void setlp_name(String lp_name) {
		this.lp_name = lp_name;
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
}
